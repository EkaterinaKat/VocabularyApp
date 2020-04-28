package com.katyshevtseva.vocabularyapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MessageController {
    static boolean warningIconNeeded = true;
    static String message;
    public Label messageLabel;
    @FXML
    public ImageView imageView;

    @FXML
    public void initialize() {
        //пременная warningIconNeeded почти всегда true. Она становится false только когда запускается опопвещение learning is completed
        //тогда блок вставляющий картинку не страбатывает, но после этого прерменной снова присваиается true
        if (warningIconNeeded) {
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            Image image = new Image("/res/warning.png");
            imageView.setImage(image);
        }
        warningIconNeeded = true;
        messageLabel.setText(message);
    }

    public void ok() {
        Stage stage = (Stage) messageLabel.getScene().getWindow();
        stage.close();
    }
}
