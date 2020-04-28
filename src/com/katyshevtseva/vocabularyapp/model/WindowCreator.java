package com.katyshevtseva.vocabularyapp.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class WindowCreator {
    private final String SAMPLES_PATH = "/com/katyshevtseva/vocabularyapp/view/";
    private static WindowCreator instance;

    public static WindowCreator getInstance(){
        if (instance==null){
            instance = new WindowCreator();
        }
        return instance;
    }

    private WindowCreator() {
    }

    public void createWindow(String sampleName, String title, int width, int height, boolean strechable) {
        try {
            String fullSampleName = SAMPLES_PATH + sampleName;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fullSampleName));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            if (!strechable) {
                stage.setMinHeight(height);
                stage.setMaxHeight(height);
                stage.setMinWidth(width);
                stage.setMaxWidth(width);
            }
            stage.setScene(new Scene(root1, width, height));
            stage.getIcons().add(new Image("/res/logo.png"));
            stage.show();

            stage.getScene().setOnKeyPressed(ke -> {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    System.out.printf("Key Pressed: %s. Window closed.\n", ke.getCode());
                    stage.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
