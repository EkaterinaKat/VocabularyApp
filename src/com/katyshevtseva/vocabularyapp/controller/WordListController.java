package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.DataBase;
import com.katyshevtseva.vocabularyapp.model.Pair;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class WordListController {
    static String nameOfList;

    @FXML
    public TableView<Pair> table;
    @FXML
    public TableColumn<Pair, String> wordColumn;
    @FXML
    public TableColumn<Pair, String> translationColumn;
    @FXML
    public TableColumn<Pair, Number> countColumn;
    @FXML
    public TableColumn<Pair, Integer> levelColumn;
    @FXML
    public TableColumn<Pair, Integer> helpColumn;
    @FXML
    public Button addWordButton;

    @FXML
    private void initialize() {
        //в аргументе PropertyValueFactory должно быть имя поля того класса который засовываешь в таблицу
        countColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(column.getValue()) + 1));
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        helpColumn.setCellValueFactory(new PropertyValueFactory<>("help"));
        updateTable();


        //назначаем слушателя двойного целчка по строчке таблицы
        table.setRowFactory(tv -> {
            TableRow<Pair> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Pair chosenPair = row.getItem();
                    createClickOnWordWindow(chosenPair);
                }
            });
            return row;
        });

        //добавляем полюсик на кнопочку
        Image image = new Image("/res/plus.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        addWordButton.graphicProperty().setValue(imageView);
    }

    void updateTable() {
        List<Pair> list = DataBase.getInstance().getListOfPairs(nameOfList);
        ObservableList<Pair> words = FXCollections.observableArrayList();
        words.addAll(list);
        table.setItems(words);
    }

    public void pressAddWordButton() {
        AddWordController.wordListController = this;
        WindowCreator.getInstance().createModalWindow(
                "add_word_sample.fxml", "Add word", 350, 200, false);
    }

    private void createClickOnWordWindow(Pair pair) {
        ClickOnWordController.pair = pair;
        ClickOnWordController.wordListController = this;
        WindowCreator.getInstance().createModalWindow(
                "click_on_word_sample.fxml", pair.getWord(), 350, 220, false);
    }

}
