package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddHelpController {
    static WordListController wordListController;
    static Entry entry;

    @FXML
    public TextArea ta;

    @FXML
    public void initialize(){
        ta.setText(entry.getHelp());
    }

    public void add() {
        if((ta.getText()==null)||(ta.getText().trim().equals(""))){
            MessageController.message = "Text field is empty";
            WindowCreator.getInstance().createModalWindow(
                    "message_sample.fxml", "Warning", 350, 200, false);
        }else{
            DataBase.getInstance().addHelp(entry, ta.getText());
            wordListController.updateTable();
            Stage stage = (Stage) ta.getScene().getWindow();
            stage.close();
        }
    }
}
