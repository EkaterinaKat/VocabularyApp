package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class WordSearchController {
    @FXML
    public TextField tf;
    @FXML
    public TableView<Pair> table;
    @FXML
    public TableColumn<Pair, String> wordColumn;
    @FXML
    public TableColumn<Pair, String> translationColumn;
    @FXML
    public TableColumn<Pair, Integer> levelColumn;
    @FXML
    public TableColumn<Pair, String> listNameColumn;


    @FXML
    public void initialize(){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillTable(tf.getText());
            }
        });
    }

    private void fillTable(String inputString){
        List<Pair> list = ConnectionWithDB.getPairsForSearch(inputString);
        ObservableList<Pair> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);

        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        translationColumn.setCellValueFactory(new PropertyValueFactory<>("translation"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        listNameColumn.setCellValueFactory(new PropertyValueFactory<>("listName"));

        table.setItems(observableList);
    }
}
