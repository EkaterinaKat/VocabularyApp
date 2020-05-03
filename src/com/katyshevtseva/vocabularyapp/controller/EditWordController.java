package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.model.Translator;
import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class EditWordController {
    static Entry entry;
    static WordListController wordListController;

    @FXML
    public TextField wordTF;
    @FXML
    public TextField translationTF;


    @FXML
    public void initialize() {
        wordTF.setText(entry.getWord());
        translationTF.setText(entry.getTranslation());

        //устанавливаем слушателей которые будут преводить текст на нужный язык
        wordTF.textProperty().addListener((observable, oldValue, newValue) -> {
            wordTF.setText(Translator.translateToEng(newValue));

        });

        translationTF.textProperty().addListener((observable, oldValue, newValue) -> {
            translationTF.setText(Translator.translateToRus(newValue));

        });
    }

    public void done() {
        if ((wordTF.getText().equals("")) || (translationTF.getText().equals(""))) {
            MessageController.message = WORD_INPUT_WARNING;
            WindowCreator.getInstance().createModalWindow("message_sample.fxml",
                    WARNING_WINDOW_TITLE, WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT, false);
        } else {
            DataBase.getInstance().editWord(entry, wordTF.getText(), translationTF.getText());
            wordListController.updateTable();
            Stage stage = (Stage) wordTF.getScene().getWindow();
            stage.close();
        }
    }
}
