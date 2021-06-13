package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.graphic.QcmJoin;

public class Main extends Application {

   /* public void start(Stage primaryStage){
        primaryStage = new LoginInterface();
        //primaryStage = new EtudiantDashbord("bastientalec@orange.fr");
        primaryStage.show();
    }*/

    public void start(Stage primaryStage){
        primaryStage = new QcmJoin("bastient@gmail.com", null);
        primaryStage.show();
    }

   /* public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        stage.show();

    }*/

    public static void main(String[] args) {

        launch(args);
        //ArrayList<String> liste = new ArrayList<String>();
        //liste.add("talec");
        //liste.add("bast");
        //liste.add("bastien");
        //SaisieLibre saisieLibre = new SaisieLibre("bastien", liste);
        //System.out.println(saisieLibre.isRight());
        /*java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        Qcm qcm = new Qcm(sqlDate,"201","h2", 20,3600, 3.5);
        qcm.insert();cul*/
    }

}
