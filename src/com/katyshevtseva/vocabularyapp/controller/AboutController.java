package com.katyshevtseva.vocabularyapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutController {
    @FXML
    public ImageView imageView;
    @FXML
    public Label label;

    @FXML
    public void initialize() {
        Image image = new Image("/res/logo.png");
        imageView.setImage(image);
        imageView.setFitHeight(130);
        imageView.setFitWidth(130);

        String s = " Developer: Katyshevtseva Ekaterina\n Programming language: Java SE 8" +
                " \n IDE: IntelliJ IDEA 2019.1 (Community Edition) \n Version: 1.0 \n Built on August 28, 2019";
        label.setText(s);
    }
}
