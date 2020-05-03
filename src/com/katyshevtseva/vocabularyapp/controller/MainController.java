package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
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

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class MainController extends Application {
    private static List<String> catalogue;
    @FXML
    public ScrollPane scrollPane;

    @Override
    public void start(Stage primaryStage) {
        DataBase.getInstance().connect();
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
        DataBase.getInstance().disconnect();
    }

    void updateInterface() {
        /* Получаем каталог из БД и обрабатываем ситуацию, когда он еще не создан */
        try {
            catalogue = DataBase.getInstance().getCatalogue();
        } catch (SQLException e) {
            createExampleWordList();
        }

        /* Создаем и настраиваем таблицу, которая будет содержать имена списков слов */
        GridPane listsTable = createAndTuneListsTable();

        /* Заполняем таблицу */
        for (int listIndex = 0; listIndex < catalogue.size(); listIndex++) {
            Label label = new Label(catalogue.get(listIndex));
            Button deleteBtn = new Button("", getListDeletionIcon());
            deleteBtn.setTooltip(new Tooltip("delete list"));
            int finalListIndex = listIndex;
            label.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                openWordListWindow(finalListIndex);
            });
            deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                openListDeletionWindow(finalListIndex);
            });
            listsTable.add(label, 0, listIndex);
            listsTable.add(deleteBtn, 1, listIndex);
        }
    }

    private void createExampleWordList() {
        DataBase.getInstance().createCatalogue();
        DataBase.getInstance().addList("Example word list");
        DataBase.getInstance().addWord("Example word list", "cat", "кот");
        DataBase.getInstance().addWord("Example word list", "dog", "пес");
        DataBase.getInstance().addWord("Example word list", "bird", "птица");
        try {
            catalogue = DataBase.getInstance().getCatalogue();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private GridPane createAndTuneListsTable() {
        GridPane listsTable = new GridPane();
        listsTable.setVgap(LISTS_TABLE_V_GAP);
        scrollPane.setContent(listsTable);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(LISTS_TABLE_WIDTH);
        listsTable.getColumnConstraints().add(columnConstraints);
        return listsTable;
    }

    private ImageView getListDeletionIcon() {
        Image image = new Image(IMAGES_PATH + LIST_DELETION_IMAGE_NAME);
        ImageView imageViewWithRedCross = new ImageView(image);
        imageViewWithRedCross.setFitHeight(LIST_DELETION_ICON_SIZE);
        imageViewWithRedCross.setFitWidth(LIST_DELETION_ICON_SIZE);
        return imageViewWithRedCross;
    }

    private void openListDeletionWindow(int listIndex) {
        RemoveListController.mainController = this;
        RemoveListController.listName = catalogue.get(listIndex);
        WindowCreator.getInstance().createModalWindow("remove_list_sample.fxml",
                LIST_DELETION_WINDOW_TITLE, LIST_DELETION_WINDOW_WIDTH, LIST_DELETION_WINDOW_HEIGHT, false);
    }

    private void openWordListWindow(int listIndex) {
        WordListController.nameOfList = catalogue.get(listIndex);
        WindowCreator.getInstance().createModalWindow("word_list_sample.fxml",
                catalogue.get(listIndex), WORD_LIST_WINDOW_WIDTH, WORD_LIST_WINDOW_HEIGHT, true);
    }

    public void createWordList() {
        AddListController.mainController = this;
        WindowCreator.getInstance().createModalWindow("add_list_sample.fxml",
                LIST_CREATION_WINDOW_TITLE, LIST_CREATION_WINDOW_WIDTH, LIST_CREATION_WINDOW_HEIGHT, false);
    }

    public void learnWords() {
        ChooseListsController.catalogue = catalogue;
        WindowCreator.getInstance().createModalWindow("lists_choice_sample.fxml",
                LIST_CHOICE_WINDOW_TITLE, LIST_CHOICE_WINDOW_WIDTH, LIST_CHOICE_WINDOW_HEIGHT, false);
    }

    public void searchWord() {
        WindowCreator.getInstance().createModalWindow("word_search_sample.fxml",
                WORD_SEARCH_WINDOW_TITLE, WORD_SEARCH_WINDOW_WIDTH, WORD_SEARCH_WINDOW_HEIGHT, true);
    }

    public void about() {
        WindowCreator.getInstance().createModalWindow(
                "about_sample.fxml", ABOUT_WINDOW_TITLE, ABOUT_WINDOW_WIDTH, ABOUT_WINDOW_HEIGHT, false);
    }
}
