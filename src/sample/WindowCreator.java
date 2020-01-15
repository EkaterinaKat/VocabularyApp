package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowCreator {

    public void createWindow(String sample, String title, int width, int height, boolean strechable){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sample));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            if(!strechable){
                stage.setMinHeight(height);
                stage.setMaxHeight(height);
                stage.setMinWidth(width);
                stage.setMaxWidth(width);
            }
            stage.setScene(new Scene(root1,width,height));
            stage.getIcons().add(new Image ("/res/logo.png"));
            stage.show();

            stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.ESCAPE) {
                        System.out.printf("Key Pressed: %s. Window closed.\n", ke.getCode());
                        stage.close();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
