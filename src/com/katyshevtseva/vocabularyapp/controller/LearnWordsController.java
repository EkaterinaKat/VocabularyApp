package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class LearnWordsController {
    static List<Entry> list;
    private int count;

    @FXML
    public Label wordLabel;
    @FXML
    public Label translationLabel;
    @FXML
    public Label countLabel;
    @FXML
    public Button showTranslationButton;
    @FXML
    public Label levelLabel;
    @FXML
    public Button okBtn;
    @FXML
    public Button notOkBtn;
    @FXML
    public Button helpButton;
    @FXML
    public Label helpLabel;

    @FXML
    void initialize() {
        Collections.shuffle(list);
        count = -1;
        nextWord();

        //добавляем галочку и крестик на кнопку
        Image image = new Image("/res/tick.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        okBtn.graphicProperty().setValue(imageView);

        Image image1 = new Image("/res/red_cross.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(25);
        imageView1.setFitWidth(25);
        notOkBtn.graphicProperty().setValue(imageView1);
    }

    public void getHelp() {
        helpLabel.setText(list.get(count).getHelp());
        helpButton.setDisable(true);
    }

    public void showTranslation() {
        translationLabel.setText(list.get(count).getTranslation());
        showTranslationButton.setDisable(true);
        okBtn.setDisable(false);
        notOkBtn.setDisable(false);
    }

    public void okBtnPressed() {
        int l = list.get(count).getLevel();
        DataBase.getInstance().changeLevel(list.get(count), l + 1);
        nextWord();
    }

    public void notOkBtnPressed() {
        int l = list.get(count).getLevel();
        if (l != 0) {
            DataBase.getInstance().changeLevel(list.get(count), l - 1);
        }
        nextWord();
    }

    private void nextWord() {
        count++;

        //проверяем не закончились ли слова для изучения
        if (count == list.size()) {
            MessageController.warningIconNeeded = false;
            MessageController.message = "Learning is completed!";
            WindowCreator.getInstance().createModalWindow(
                    "message_sample.fxml", "Message", 350, 200, false);
            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.close();
        } else {
            //настраиваем лэйблы
            wordLabel.setText(list.get(count).getWord());
            String s = String.format("%s/%s", count + 1, list.size());
            countLabel.setText(s);
            levelLabel.setText("Word level: " + list.get(count).getLevel());
            helpLabel.setText("");
            translationLabel.setText("");

            //настраиваем кнопки
            showTranslationButton.setDisable(false);
            if ((list.get(count).getHelp() == null) || (list.get(count).getLevel() > 3)) {
                helpButton.setDisable(true);
            } else {
                helpButton.setDisable(false);
            }
            okBtn.setDisable(true);
            notOkBtn.setDisable(true);
        }
    }
}
