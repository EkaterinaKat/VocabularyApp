package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.DataBase;
import com.katyshevtseva.vocabularyapp.model.Pair;
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
    public TableView<Pair> table;
    @FXML
    public TableColumn<Pair, String> wordColumn;
    @FXML
    public TableColumn<Pair, String> translationColumn;
    @FXML
    public TableColumn<Pair, Integer> levelColumn;
    @FXML
    public TableColumn<Pair, String> listNameColumn;


    @FXML
    public void initialize() {
        tf.textProperty().addListener((observable, oldValue, newValue) -> fillTable(tf.getText()));
    }

    private void fillTable(String inputString) {
        List<Pair> list = DataBase.getInstance().getPairsForSearch(inputString);
        ObservableList<Pair> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        listNameColumn.setCellValueFactory(new PropertyValueFactory<>("listName"));

        table.setItems(observableList);
    }
}
