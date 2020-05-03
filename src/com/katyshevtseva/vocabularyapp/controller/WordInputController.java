package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Translator;
import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class WordInputController {
    static WordListController wordListController;
    @FXML
    public TextField wordTextField;
    @FXML
    public TextField translationTextField;

    @FXML
    public void initialize() {
        wordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            wordTextField.setText(Translator.translateToEng(newValue));
        });

        translationTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            translationTextField.setText(Translator.translateToRus(newValue));

        });
    }

    public void addButtonPressed() {
        addWord();
    }

    public void enterOnTextField() {
        addWord();
    }

    private void addWord() {
        if (!wordTextField.getText().trim().equals("") && !translationTextField.getText().trim().equals("")) {
            DataBase.getInstance().addWord(WordListController.nameOfList, wordTextField.getText(), translationTextField.getText());
            wordTextField.clear();
            translationTextField.clear();
            wordListController.updateTable();
            wordTextField.requestFocus();
        } else {
            MessageController.message = WORD_INPUT_WARNING;
            WindowCreator.getInstance().createModalWindow("message_sample.fxml",
                    WARNING_WINDOW_TITLE, WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT, false);
        }
    }

    public void nextField() {
        translationTextField.requestFocus();
    }
}
