package com.katyshevtseva.vocabularyapp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCreator {
    private final String SAMPLES_PATH = "/com/katyshevtseva/vocabularyapp/view/";
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
        Stage stage = getStage("main_sample.fxml", "Vocabulary", 550, 500, false);
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
        stage.getIcons().add(new Image("/res/logo.png"));
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
