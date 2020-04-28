package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.ConnectionWithDB;
import com.katyshevtseva.vocabularyapp.model.Pair;
import com.katyshevtseva.vocabularyapp.model.Translator;
import com.katyshevtseva.vocabularyapp.model.WindowCreator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditWordController{
    static Pair pair;
    static WordListController wordListController;

    @FXML
    public TextField wordTF;
    @FXML
    public TextField translationTF;


    @FXML
    public void initialize(){
        wordTF.setText(pair.getWord());
        translationTF.setText(pair.getTranslation());

        //устанавливаем слушателей которые будут преводить текст на нужный язык
        wordTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!Translator.isStingInEnglish(newValue)){
                    wordTF.setText(Translator.translateToEng(newValue));
                }
            }
        });

        translationTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!Translator.isStingInRussian(newValue)){
                    translationTF.setText(Translator.translateToRus(newValue));
                }
            }
        });
    }

    public void done(MouseEvent mouseEvent) {
        if((wordTF.getText().equals(""))||(translationTF.getText().equals(""))){
            MessageController.message = "Both fields must be filled in";
            WindowCreator.getInstance().createWindow(
                    "message_sample.fxml", "Warning", 400, 200, false);
        }else {
            ConnectionWithDB.editWord(pair, wordTF.getText(), translationTF.getText());
            wordListController.updateTable();
            Stage stage = (Stage) wordTF.getScene().getWindow();
            stage.close();
        }
    }
}
