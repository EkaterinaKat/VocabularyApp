package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.ConnectionWithDB;
import com.katyshevtseva.vocabularyapp.model.WindowCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddListController {
    static Main main;
    @FXML
    TextField nameField;


    public void ok() {

        if (!nameField.getText().trim().equals("")) {
            ConnectionWithDB.addList(nameField.getText());
            main.updateInterface();
            nameField.clear();
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } else {
            MessageController.message = "Text field is empty";
            WindowCreator.getInstance().createWindow(
                    "message_sample.fxml", "Warning", 350, 200, false);
        }
    }

    public void ok1(MouseEvent mouseEvent) {
        ok();
    }

    public void ok2(ActionEvent actionEvent) {
        ok();
    }
}
