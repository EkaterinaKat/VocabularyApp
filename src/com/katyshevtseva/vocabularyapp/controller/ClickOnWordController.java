package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Pair;
import com.katyshevtseva.vocabularyapp.model.WindowCreator;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClickOnWordController {
    WindowCreator windowCreator = new WindowCreator();
    static WordListController wordListController;
    static Pair pair;
    public Button btn;

    public void editWord(MouseEvent mouseEvent) {
        EditWordController.wordListController = wordListController;
        EditWordController.pair = pair;
        windowCreator.createWindow("edit_word_sample.fxml","Edit word", 350, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void deleteWord(MouseEvent mouseEvent) {
        DeleteWordController.wordListController = wordListController;
        DeleteWordController.pair = pair;
        windowCreator.createWindow("delete_word_sample.fxml","Delete word", 450, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void addHelp(MouseEvent mouseEvent) {
        AddHelpController.wordListController = wordListController;
        AddHelpController.pair = pair;
        windowCreator.createWindow("add_help_sample.fxml","Add help", 350, 200, false);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}


