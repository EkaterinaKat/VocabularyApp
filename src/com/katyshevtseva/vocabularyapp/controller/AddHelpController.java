package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.ConnectionWithDB;
import com.katyshevtseva.vocabularyapp.model.Pair;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddHelpController {
    static WordListController wordListController;
    static Pair pair;

    @FXML
    public TextArea ta;

    @FXML
    public void initialize(){
        ta.setText(pair.getHelp());
    }

    public void add(MouseEvent mouseEvent) {
        if((ta.getText()==null)||(ta.getText().trim().equals(""))){
            MessageController.message = "Text field is empty";
            WindowCreator.getInstance().createModalWindow(
                    "message_sample.fxml", "Warning", 350, 200, false);
        }else{
            ConnectionWithDB.addHelp(pair, ta.getText());
            wordListController.updateTable();
            Stage stage = (Stage) ta.getScene().getWindow();
            stage.close();
        }
    }
}
