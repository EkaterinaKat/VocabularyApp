package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.model.Translator;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddWordController {
    static WordListController wordListController;

    @FXML
    public TextField wordTF;
    @FXML
    public TextField translationTF;

    @FXML
    public void initialize() {
        //устанавливаем слушателей которые будут преводить текст на нужный язык
        wordTF.textProperty().addListener((observable, oldValue, newValue) -> {
            wordTF.setText(Translator.translateToEng(newValue));
        });

        translationTF.textProperty().addListener((observable, oldValue, newValue) -> {
            translationTF.setText(Translator.translateToRus(newValue));

        });
    }

    public void addButtonPressed() {
        addWord();
    }

    public void enterOnTextField() {
        addWord();
    }

    private void addWord() {
        if (!wordTF.getText().trim().equals("") && !translationTF.getText().trim().equals("")) {
            DataBase.getInstance().addWord(WordListController.nameOfList, wordTF.getText(), translationTF.getText());
            wordTF.clear();
            translationTF.clear();
            wordListController.updateTable();
            wordTF.requestFocus();
        } else {
            MessageController.message = "Both fields must be filled in";
            WindowCreator.getInstance().createModalWindow(
                    "message_sample.fxml", "Warning", 400, 200, false);
        }
    }

    public void nextField() {
        translationTF.requestFocus();
    }


}
