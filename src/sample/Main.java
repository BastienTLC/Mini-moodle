package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.graphic.CreationQcm;

public class Main extends Application {

    public void start(Stage primaryStage){
        primaryStage = new CreationQcm("bastien@gmail.com");
        primaryStage.show();
    }

    /*public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        stage.show();

    }*/

    public static void main(String[] args) {

        launch(args);
        /*java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        Qcm qcm = new Qcm(sqlDate,"201","h2", 20,3600, 3.5);
        qcm.insert();cul*/
    }

}
