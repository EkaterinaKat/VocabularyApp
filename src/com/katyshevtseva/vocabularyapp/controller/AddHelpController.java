package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class AddHelpController {
    static WordListController wordListController;
    static Entry entry;
    @FXML
    public TextArea textArea;

    @FXML
    public void initialize() {
        textArea.setText(entry.getHelp());
    }

    public void add() {
        if (textAreaIsEmpty()) {
            MessageController.message = TEXT_FIELD_IS_EMPTY_WARNING;
            WindowCreator.getInstance().createModalWindow("message_sample.fxml",
                    WARNING_WINDOW_TITLE, WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT, false);
        } else {
            DataBase.getInstance().addHelp(entry, textArea.getText());
            wordListController.updateTable();
            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.close();
        }
    }

    private boolean textAreaIsEmpty() {
        return (textArea.getText() == null) || (textArea.getText().trim().equals(""));
    }
}
