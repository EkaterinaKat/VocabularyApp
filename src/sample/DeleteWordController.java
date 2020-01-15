package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;

public class DeleteWordController{
    static WordListController wordListController;
    static Pair pair;
    @FXML
    public Label label;
    @FXML
    public ImageView imageView;

    @FXML
    public void initialize(){
        Image image = new Image ("/res/question.png");
        imageView.setImage(image);
    }

    public void delete(MouseEvent mouseEvent) {
        ConnectionWithDB.deleteWord(pair);
        wordListController.updateTable();
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }

    public void cancel(MouseEvent mouseEvent) {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
}
