package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Verification;

public class EnseignantDashbord extends Stage {
    public EnseignantDashbord(String adressMail) {
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(adressMail, verif),1280, 720);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif) {

        VBox layout = new VBox();

        Label connexion = new Label("Bonjour " +  verif.readSpecificRow("firstname", emailAdress) +" "+  verif.readSpecificRow("secondName", emailAdress));
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        box1.setStyle("-fx-background-color: #FF0000;");


       /* Label email = new Label("email : ");
        TextField email2 = new TextField();
        Label password = new Label("Mot de passe: ");
        PasswordField password2 = new PasswordField();*/

        Button qcmCreation = new Button("créer un QCM");
        Button note = new Button("Consulter les notes");
        Button correction = new Button("Consulter les reponses au qcm");
        Button setting = new Button("paramètre");


        Insets margin = new Insets(10);

        connexion.setPadding(margin);
        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(connexion, box1, box2);
        box1.getChildren().addAll(qcmCreation, note);
        box2.getChildren().addAll(setting, correction);

        qcmCreation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage qcmCreationWindow = new CreationQcm(emailAdress);
                qcmCreationWindow.show();
                close();
            }
        });

        return layout;
    }


}
