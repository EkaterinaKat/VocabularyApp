package com.katyshevtseva.vocabularyapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class AboutController {
    @FXML
    public ImageView imageView;
    @FXML
    public Label label;

    @FXML
    public void initialize() {
        Image image = new Image(IMAGES_PATH + LOGO_IMAGE_NAME);
        imageView.setImage(image);
        imageView.setFitHeight(BIG_LOGO_SIZE);
        imageView.setFitWidth(BIG_LOGO_SIZE);
        label.setText(ABOUT_TEXT);
    }
}
