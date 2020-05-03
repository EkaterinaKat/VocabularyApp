package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class AddListController {
    static MainController mainController;
    @FXML
    TextField nameField;


    private void ok() {

        if (!nameField.getText().trim().equals("")) {
            DataBase.getInstance().addList(nameField.getText());
            mainController.updateInterface();
            nameField.clear();
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        } else {
            MessageController.message = TEXT_FIELD_IS_EMPTY_WARNING;
            WindowCreator.getInstance().createModalWindow("message_sample.fxml",
                    WARNING_WINDOW_TITLE, WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT, false);
        }
    }

    public void ok1() {
        ok();
    }

    public void ok2() {
        ok();
    }
}
