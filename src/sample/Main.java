package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.bdd.Qcm;
import sample.graphic.LoginInterface;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    public void start(Stage primaryStage){
        primaryStage = new LoginInterface();
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }

}
