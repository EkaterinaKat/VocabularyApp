package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.DataBase;
import com.katyshevtseva.vocabularyapp.model.Pair;
import com.katyshevtseva.vocabularyapp.model.Translator;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditWordController {
    static Pair pair;
    static WordListController wordListController;

    @FXML
    public TextField wordTF;
    @FXML
    public TextField translationTF;


    @FXML
    public void initialize() {
        wordTF.setText(pair.getWord());
        translationTF.setText(pair.getTranslation());

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
            MessageController.message = "Both fields must be filled in";
            WindowCreator.getInstance().createModalWindow(
                    "message_sample.fxml", "Warning", 400, 200, false);
        } else {
            DataBase.getInstance().editWord(pair, wordTF.getText(), translationTF.getText());
            wordListController.updateTable();
            Stage stage = (Stage) wordTF.getScene().getWindow();
            stage.close();
        }
    }
}
