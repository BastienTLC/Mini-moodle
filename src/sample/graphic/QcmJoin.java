package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Verification;

public class QcmJoin extends Stage {
    public QcmJoin(String adressMail) {
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(adressMail, verif),1280, 720);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif) {

        VBox layout = new VBox();


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        VBox ListeQCM = new VBox();
        HBox rowQCM = new HBox();
        box1.setStyle("-fx-background-color: #994400;");




        Verification verification = new Verification();



        System.out.println(verification.readQcmGroup("H2", "H"));

        for( String value : verification.readQcmGroup("H2", "H") ) {
            String fullValue = value;
            String[] printValue = fullValue.split("\\n");
            Button qcmJoin = new Button("REJOINDRE QMC : " + printValue[0] + " \n "+ printValue[1]);
            ListeQCM.getChildren().addAll(qcmJoin);
            qcmJoin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage ReponseQuestion = new ReponseQuestion(emailAdress, value);
                    ReponseQuestion.show();
                    close();
                }
            });
        }








        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(box1, box2, box3);
       // ListeQCM.getChildren().addAll(rowQCM);
        box1.getChildren().addAll();
        box2.getChildren().addAll(ListeQCM);
        box3.getChildren().addAll();

        return layout;
    }


}
