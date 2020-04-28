package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.DataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RemoveListController {
    @FXML
    public Label label;
    @FXML
    public ImageView imageView;
    static MainController mainController;
    static String listName;

    @FXML
    public void initialize() {
        Image image = new Image("/res/question.png");
        imageView.setImage(image);
    }

    public void delete() {
        DataBase.getInstance().removeList(listName);
        mainController.updateInterface();
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    public void cancel() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
