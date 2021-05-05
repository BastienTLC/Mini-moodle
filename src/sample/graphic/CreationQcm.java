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

public class CreationQcm extends Stage {
    public CreationQcm(String adressMail) {
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
        box1.setStyle("-fx-background-color: #0000ff;");


        Label QcmName = new Label("Nom du qcm");
        TextField QcmNamefield = new TextField();
        Label publicatonQcm = new Label("Date publication QCM");
        TextField publicatonQcmField = new TextField();
        Label classOwner = new Label("Selectionner des classes");
        TextField classOwnerFiel = new TextField();
        Label groupOwner = new Label("Selectionner des groupes");
        TextField groupOwnerFiel = new TextField();
        Label nb_question = new Label("Selectionner le nombre de question");
        TextField nb_questionField = new TextField();
        Label qcmDuration = new Label("Selectionner le temps du qcm");
        TextField qcmDurationField = new TextField();
        Label qcmCoef = new Label("Selectionner un coeficient pour ce qcm");
        TextField qcmCoefField = new TextField();

        Button qcmCreation = new Button("créer un QCM");
        Button note = new Button("Consulter les notes");
        Button correction = new Button("Consulter les reponses au qcm");
        Button setting = new Button("paramètre");


        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(box1, box2);
        box1.getChildren().addAll(QcmName,publicatonQcm,classOwner,groupOwner,nb_question,qcmDuration,qcmCoef);
        box2.getChildren().addAll(QcmNamefield,publicatonQcmField,classOwnerFiel,groupOwnerFiel,nb_questionField,qcmDurationField,qcmCoefField);

        return layout;
    }


}