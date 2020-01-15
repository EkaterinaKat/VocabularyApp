package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseListsController {
    WindowCreator windowCreator = new WindowCreator();
    static List<String> catalogue;
    List<CheckBox> checkBoxes = new ArrayList<>();

    @FXML
    public ScrollPane scrollPane;

    @FXML
    public void initialize(){
        GridPane gridPane = new GridPane();
        scrollPane.setContent(gridPane);
        gridPane.setVgap(10);
        int rowCount = 0;
        for(String s: catalogue){
            CheckBox cb = new CheckBox(s);
            checkBoxes.add(cb);
            gridPane.add(cb,0,rowCount);
            rowCount++;
        }
    }

    public void done(MouseEvent mouseEvent) {
        //создаем и заполняем список в который входят все пары из помечкных галочками списков
        List<Pair> listSet = new ArrayList<>();
        for(CheckBox cb: checkBoxes){
            if(cb.isSelected()){
                List<Pair> list = ConnectionWithDB.getListOfPairs(cb.getText());
                listSet.addAll(list);
            }
        }

        //проверяем не пустой ли у нас получился список
        if(listSet.size()==0){
            MessageController.message = "Chosen lists are empty";
            windowCreator.createWindow("message_sample.fxml", "Warning", 350, 200, false);
        }else{
            //передаем этот список в чузлевелконтроллер
            ChooseLevelsController.list = listSet;

            //создаем окно для выбора уровней
            windowCreator.createWindow("choose_levels_sample.fxml", "Choose levels", 400, 450, false);

            //закрываем окно выбора списков
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}
