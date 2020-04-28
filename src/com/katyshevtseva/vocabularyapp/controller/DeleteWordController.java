package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.model.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DeleteWordController {
    static WordListController wordListController;
    static Entry entry;
    @FXML
    public Label label;
    @FXML
    public ImageView imageView;

    @FXML
    public void initialize() {
        Image image = new Image("/res/question.png");
        imageView.setImage(image);
    }

    public void delete() {
        DataBase.getInstance().deleteWord(entry);
        wordListController.updateTable();
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
