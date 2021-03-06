package com.katyshevtseva.vocabularyapp.controller;

import com.katyshevtseva.vocabularyapp.model.Entry;
import com.katyshevtseva.vocabularyapp.utils.DataBase;
import com.katyshevtseva.vocabularyapp.utils.WindowCreator;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.katyshevtseva.vocabularyapp.utils.Constants.*;

public class ChooseListsController {
    static List<String> catalogue;
    private List<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public void initialize() {
        GridPane gridPane = new GridPane();
        scrollPane.setContent(gridPane);
        gridPane.setVgap(10);
        int rowCount = 0;
        for (String s : catalogue) {
            CheckBox cb = new CheckBox(s);
            checkBoxes.add(cb);
            gridPane.add(cb, 0, rowCount);
            rowCount++;
        }
    }

    public void done(MouseEvent mouseEvent) {
        //создаем и заполняем список в который входят все пары из помечкных галочками списков
        List<Entry> listSet = new ArrayList<>();
        for (CheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                List<Entry> list = DataBase.getInstance().getListOfPairs(cb.getText());
                listSet.addAll(list);
            }
        }

        //проверяем не пустой ли у нас получился список
        if (listSet.size() == 0) {
            MessageController.message = EMPTY_LISTS_WARNING;
            WindowCreator.getInstance().createModalWindow("message_sample.fxml",
                    WARNING_WINDOW_TITLE, WARNING_WINDOW_WIDTH, WARNING_WINDOW_HEIGHT, false);
        } else {
            //передаем этот список в чузлевелконтроллер
            ChooseLevelsController.list = listSet;

            //создаем окно для выбора уровней
            WindowCreator.getInstance().createModalWindow("levels_choice_sample.fxml",
                    LEVELS_CHOICE_WINDOW_TITLE, LEVELS_CHOICE_WINDOW_WIDTH, LEVELS_CHOICE_WINDOW_HEIGHT, false);

            //закрываем окно выбора списков
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}
