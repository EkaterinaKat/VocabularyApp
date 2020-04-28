package com.katyshevtseva.vocabularyapp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static DataBase instance;
    private Connection connection;
    private Statement stmt;

    public static DataBase getInstance(){
        if (instance==null){
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Vocabulary_DB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableNameByListName(String listName) {
        String tableName = "";
        String sql1 = String.format("SELECT tableName FROM catalogue\n" +
                "WHERE listName = \"%s\"", listName);
        try {
            ResultSet rs = stmt.executeQuery(sql1);
            rs.next();
            tableName = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableName;
    }

    public List<String> getCatalogue() throws SQLException {
        List<String> catalogue = new ArrayList<>();
        String sql = "SELECT * FROM catalogue";
        ResultSet rs = null;
        rs = stmt.executeQuery(sql);
        while (rs.next()) { //листаем строчки таблички
            String s = rs.getString(1);
            catalogue.add(s);
        }
        return catalogue;
    }

    public List<Pair> getListOfPairs(String listName) {
        String tableName = getTableNameByListName(listName);
        List<Pair> listOfPairs = new ArrayList<>();
        String sql2 = String.format("SELECT word, translation, level, help FROM %s", tableName);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql2);
            while (rs.next()) { //листаем строчки таблички
                String n = rs.getString(1);
                String t = rs.getString(2);
                Integer l = rs.getInt(3);
                String h = rs.getString(4);
                listOfPairs.add(new Pair(n, t, l, listName, h));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPairs;
    }

    public void addWord(String listName, String word, String translation) {
        String tableName = getTableNameByListName(listName);
        String sql = String.format("INSERT INTO %s (word, translation, level)\n" +
                "VALUES (\"%s\", \"%s\", 0)", tableName, word, translation);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addList(String listName) {
        //добавляем в каталог строчку с именем списка
        String sql1 = String.format("INSERT INTO catalogue (listName)\n" +
                "VALUES(\"%s\")", listName);
        try {
            stmt.executeUpdate(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //достаем из каталога автосгенерированный айди
        String sql2 = String.format("SELECT id FROM catalogue\n" +
                "WHERE listName = \"%s\"", listName);
        ResultSet rs = null;
        int id = 0;
        try {
            rs = stmt.executeQuery(sql2);
            rs.next();
            id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //добавляем в строку с именем нового списка имя таблицы, в которой этот список будет храниться, ипользуя айди
        String sql3 = String.format("UPDATE catalogue \n" +
                "\t   SET tableName = \"table%d\" \n" +
                "\t   WHERE id = \"%d\"", id, id);
        try {
            stmt.executeUpdate(sql3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //создаем таблицу
        String sql4 = String.format("CREATE TABLE table%d (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "word STRING,\n" +
                "translation STRING,\n" +
                "level INTEGER, \n" +
                "help STRING \n" +
                ");\n", id);
        try {
            stmt.executeUpdate(sql4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeList(String listName) {
        String tableName = getTableNameByListName(listName);
        String sql1 = String.format("DROP TABLE %s", tableName); //удаление таблицы
        String sql2 = String.format("DELETE FROM catalogue\n" +
                "WHERE listName=\"%s\"", listName); //удаление строки из каталога
        try {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeLevel(Pair pair, int newLevel) {
        String tableName = getTableNameByListName(pair.getListName());
        String sql = String.format("UPDATE %s \n" +
                "\t   SET level = \"%d\" \n" +
                "\t   WHERE word = " +
                "\"%s\" ", tableName, newLevel, pair.getWord());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pair> getPairsForSearch(String inputString) {
        List<Pair> resultList = new ArrayList<>();

        //достаем из каталога имена списков
        List<String> listNames = null;
        try {
            listNames = getCatalogue();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //для каждого списка находим имя таблицы и если в ней есть слово начинающееся с входящей строки то кладем это слово в лист
        for (String listName : listNames) {
            String tableName = getTableNameByListName(listName);
            String sql = String.format("SELECT word, translation, level, help FROM %s", tableName);
            try {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    if (rs.getString(1).startsWith(inputString)) {
                        String word = rs.getString(1);
                        String translation = rs.getString(2);
                        Integer level = rs.getInt(3);
                        String help = rs.getString(4);
                        resultList.add(new Pair(word, translation, level, listName, help));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

    public void editWord(Pair pair, String newWord, String newTranslation) {
        String tableName = getTableNameByListName(pair.getListName());
        String sql = String.format("UPDATE %s \n" +
                "\t   SET word = \"%s\", translation = \"%s\" \n" +
                "\t   WHERE word = \"%s\"", tableName, newWord, newTranslation, pair.getWord());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(Pair pair) {
        String tableName = getTableNameByListName(pair.getListName());
        String sql = String.format("DELETE FROM %s WHERE word=\"%s\" AND translation = \"%s\" " +
                "AND level = \"%d\"", tableName, pair.getWord(), pair.getTranslation(), pair.getLevel());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addHelp(Pair pair, String newHelp) {
        String tableName = getTableNameByListName(pair.getListName());
        String sql = String.format("UPDATE %s \n" +
                "\t   SET help = \"%s\" \n" +
                "\t   WHERE word = \"%s\"", tableName, newHelp, pair.getWord());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCatalogue() {
        String sql = String.format("CREATE TABLE catalogue (\n" +
                "listName STRING,\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "tableName STRING\n" +
                ");\n");
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
