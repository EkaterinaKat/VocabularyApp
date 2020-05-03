package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static com.katyshevtseva.vocabularyapp.utils.Constants.IMAGES_PATH;
import static com.katyshevtseva.vocabularyapp.utils.Constants.QUESTION_IMAGE_NAME;

public class DeleteWordController {
    static WordListController wordListController;
    static Entry entry;
    @FXML
    public Label label;
    @FXML
    public ImageView imageView;

    @FXML
    public void initialize() {
        Image image = new Image(IMAGES_PATH + QUESTION_IMAGE_NAME);
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
