package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChooseLevelsController {
    static List<Pair> list;
    List<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public Button doneBtn;
    @FXML
    public ScrollPane scrollPane;

    @FXML
    public void initialize() {
        //используем хашсет для получения упорядоченного набора существующих уровней в единственном экземпляре
        Set<Integer> existingLevels = new HashSet<>();
        for (Pair p : list) {
            existingLevels.add(p.getLevel());
        }

        //настраиваем сетку, для каждого уровня создаем по чекбоксу и добавляем их на экран и а эррэйлист
        GridPane gridPane = new GridPane();
        scrollPane.setContent(gridPane);
        gridPane.setVgap(10);
        int rowCount = 0;
        for (Integer i : existingLevels) {
            CheckBox cb = new CheckBox("level: " + i);
            checkBoxes.add(cb);
            gridPane.add(cb,0,rowCount);
            rowCount++;
        }
    }

    public void done(MouseEvent mouseEvent) {
        //собираем сет выбранных уровней
        Set<Integer> chosenLevels = new HashSet<>();
        for (CheckBox cb : checkBoxes) {
            if (cb.isSelected()) {
                int level = Integer.parseInt(cb.getText().substring(7));
                chosenLevels.add(level);
            }
        }

        //собираем конечный лист который предадим в лернвордс
        List<Pair> finalList = new ArrayList<>();
        for (Pair pair : list) {
            if (chosenLevels.contains(pair.getLevel())) {
                finalList.add(pair);
            }
        }

        //передаем конечный лист в лернвордс контроллер и запускаем окно для изучения слов
        LearnWordsController.list = finalList;
        WindowCreator windowCreator = new WindowCreator();
        windowCreator.createWindow("learn_words_sample.fxml", "Learn words", 550, 500, false);

        //закрываем окно выбора уровней
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        stage.close();
    }
}
