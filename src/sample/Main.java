package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.graphic.LoginInterface;

public class Main extends Application {

    public void start(Stage primaryStage){
        primaryStage = new LoginInterface();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
