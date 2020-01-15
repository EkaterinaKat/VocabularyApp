package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddWordController {
    static WordListController wordListController;

    @FXML
    public TextField wordTF;
    @FXML
    public TextField translationTF;

    @FXML
    public void initialize(){
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

    public void addButtonPressed(MouseEvent mouseEvent) {
        addWord();
    }

    public void enterOnTextField(ActionEvent actionEvent) {
        addWord();
    }

    private void addWord() {
        if (!wordTF.getText().trim().equals("") && !translationTF.getText().trim().equals("")) {
            ConnectionWithDB.addWord(WordListController.nameOfList, wordTF.getText(), translationTF.getText());
            wordTF.clear();
            translationTF.clear();
            wordListController.updateTable();
            wordTF.requestFocus();
        } else {
            MessageController.message = "Both fields must be filled in";
            WindowCreator windowCreator = new WindowCreator();
            windowCreator.createWindow("message_sample.fxml", "Warning", 400, 200, false);
        }
    }

    public void nextField(ActionEvent actionEvent) {
        translationTF.requestFocus();
    }


}
