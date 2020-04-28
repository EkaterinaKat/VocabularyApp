package com.katyshevtseva.vocabularyapp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class WindowCreator {
    private static WindowCreator instance;

    public static WindowCreator getInstance() {
        if (instance == null) {
            instance = new WindowCreator();
        }
        return instance;
    }

    private WindowCreator() {
    }

    public void createModalWindow(String sampleName, String title, int width, int height, boolean stretchable) {
        Stage stage = getStage(sampleName, title, width, height, stretchable);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void createMainWindow() {
        Stage stage = getStage(MAIN_SAMPLE_NAME, MAIN_WINDOW_TITLE, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT, false);
        stage.show();
    }

    private Stage getStage(String sampleName, String title, int width, int height, boolean stretchable) {
        Stage stage = new Stage();
        stage.setTitle(title);
        if (!stretchable) {
            stage.setMinHeight(height);
            stage.setMaxHeight(height);
            stage.setMinWidth(width);
            stage.setMaxWidth(width);
        }
        stage.setScene(new Scene(getParent(sampleName), width, height));
        String logoURL = IMAGES_PATH + LOGO_IMAGE_NAME;
        stage.getIcons().add(new Image(logoURL));
        setClosingWithEscape(stage);
        return stage;
    }

    private Parent getParent(String sampleName) {
        String fullSampleName = SAMPLES_PATH + sampleName;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fullSampleName));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    private void setClosingWithEscape(Stage stage) {
        stage.getScene().setOnKeyPressed(ke -> {
            if (ke.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });
    }

}
