package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.ConnectionWithDB;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class MainController extends Application {
    static List<String> catalogue;

    @FXML
    public ScrollPane scrollPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectionWithDB.connect();
        ConnectionWithDB.mainController = this;
        WindowCreator.getInstance().createMainWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void initialize() {
        updateInterface();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ConnectionWithDB.disconnect();
    }

    void updateInterface() {
        //получаем каталог и обрабатываем ситуацию если вдруг каталога не существует
        try {
            catalogue = ConnectionWithDB.getCatalogue();
        } catch (SQLException e) {
            System.out.println("Вот беда, каталога нет, но без паники, сейчас мы его сделаем");
            ConnectionWithDB.createCatalogue();
            ConnectionWithDB.addList("First word list");
            ConnectionWithDB.addWord("First word list", "cat", "кот");
            ConnectionWithDB.addWord("First word list", "dog", "пес");
            ConnectionWithDB.addWord("First word list", "bird", "птица");
            try {
                catalogue = ConnectionWithDB.getCatalogue();
            } catch (SQLException ex) {
                System.out.println("блин, ну вот не создался каталог. на этом мои полнмочия все");
            }
        }

        //нстраиваем гридпэйн в который будем засовывать список листов
        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        scrollPane.setContent(gridPane);
        int rowIndex = 0;
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(440);
        gridPane.getColumnConstraints().add(columnConstraints);

        //заполняем гридпэйн
        for (String listName : catalogue) {
            //подготавливаем имиджвью с крестиком для кнопки удаления
            Image image = new Image("/res/red_cross.png");
            ImageView imageViewWithRedCross = new ImageView(image);
            imageViewWithRedCross.setFitHeight(15);
            imageViewWithRedCross.setFitWidth(15);

            //создаем, инициализируем и добавляем на экран лэйбл и кнопку удаления для каждого листа
            Label label = new Label(listName);
            Button deleteBtn = new Button("", imageViewWithRedCross);
            deleteBtn.setTooltip(new Tooltip("delete list"));
            label.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                WordListController.nameOfList = listName;
                WindowCreator.getInstance().createModalWindow(
                        "word_list_sample.fxml", listName, 1330, 900, true);
            });
            deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                RemoveListController.mainController = this;
                RemoveListController.listName = listName;
                WindowCreator.getInstance().createModalWindow(
                        "remove_list_sample.fxml", "Delete word list", 450, 200, false);
            });

            gridPane.add(label, 0, rowIndex);
            gridPane.add(deleteBtn, 1, rowIndex);
            rowIndex++;

        }
    }

    public void addWordList(MouseEvent mouseEvent) {
        AddListController.mainController = this;
        WindowCreator.getInstance().createModalWindow(
                "add_list_sample.fxml", "Add list", 410, 150, false);
    }

    public void learnWords(MouseEvent mouseEvent) {
        ChooseListsController.catalogue = catalogue;
        WindowCreator.getInstance().createModalWindow(
                "choose_lists_sample.fxml", "Choose lists to learn", 450, 450, false);
    }

    public void searchWord(MouseEvent mouseEvent) {
        WindowCreator.getInstance().createModalWindow(
                "word_search_sample.fxml", "Word Search", 820, 500, true);
    }

    public void about(MouseEvent mouseEvent) {
        WindowCreator.getInstance().createModalWindow(
                "about_sample.fxml", "About", 570, 300, false);
    }
}
