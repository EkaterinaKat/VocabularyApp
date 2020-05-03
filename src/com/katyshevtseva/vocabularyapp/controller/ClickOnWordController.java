package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClickOnWordController {
    static WordListController wordListController;
    static Entry entry;
    public Button btn;

    //todo константы
    public void editWord() {
        EditWordController.wordListController = wordListController;
        EditWordController.entry = entry;
        WindowCreator.getInstance().createModalWindow(
                "edit_word_sample.fxml", "Edit word", 350, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void deleteWord() {
        DeleteWordController.wordListController = wordListController;
        DeleteWordController.entry = entry;
        WindowCreator.getInstance().createModalWindow(
                "delete_word_sample.fxml", "Delete word", 450, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void addHelp() {
        AddHelpController.wordListController = wordListController;
        AddHelpController.entry = entry;
        WindowCreator.getInstance().createModalWindow(
                "add_help_sample.fxml", "Add help", 350, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}


