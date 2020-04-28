package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.model.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class WordSearchController {
    @FXML
    public TextField tf;
    @FXML
    public TableView<Entry> table;
    @FXML
    public TableColumn<Entry, String> wordColumn;
    @FXML
    public TableColumn<Entry, String> translationColumn;
    @FXML
    public TableColumn<Entry, Integer> levelColumn;
    @FXML
    public TableColumn<Entry, String> listNameColumn;


    @FXML
    public void initialize() {
        tf.textProperty().addListener((observable, oldValue, newValue) -> fillTable(tf.getText()));
    }

    private void fillTable(String inputString) {
        List<Entry> list = DataBase.getInstance().getPairsForSearch(inputString);
        ObservableList<Entry> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        listNameColumn.setCellValueFactory(new PropertyValueFactory<>("listName"));

        table.setItems(observableList);
    }
}
