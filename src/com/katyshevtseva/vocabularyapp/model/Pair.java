package com.katyshevtseva.vocabularyapp.model;

public class Pair {
    private String word;
    private String translation;
    private Integer level;
    private String listName;
    private String help;


    public Pair(String word, String translation, Integer level, String listName, String help) {
        this.word = word;
        this.translation = translation;
        this.level = level;
        this.listName = listName;
        this.help = help;
    }

    @Override
    public String toString(){
        return String.format("<%s, %s, %d>", word, translation, level);
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getListName() {
        return listName;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }
}
