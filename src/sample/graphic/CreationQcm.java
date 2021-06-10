package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Qcm;
import sample.bdd.Verification;

import java.time.LocalDate;

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
        Verification verification = new Verification();


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        box1.setStyle("-fx-background-color: #994400;");


        Label QcmName = new Label("Nom du qcm");
        TextField QcmNamefield = new TextField();
        Label publicatonQcm = new Label("Date publication QCM");
        DatePicker publicatonQcmField = new DatePicker();
        ComboBox comboBox2 = new ComboBox();
        comboBox2.getItems().addAll(verification.readSpeData("nomGroupe", "groupe"));

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(verification.readSpeData("nomDemi", "groupe"));
        Label nb_question = new Label("Selectionner le nombre de question");
        TextField nb_questionField = new TextField();
        Label qcmDuration = new Label("Selectionner le temps du qcm");
        TextField qcmDurationField = new TextField();
        Label qcmCoef = new Label("Selectionner un coeficient pour ce qcm");
        TextField qcmCoefField = new TextField();

        Button ajoutQuestion = new Button("Ajouter des questions");
        Button undo = new Button("retourner a l'accueil");

        java.util.Date now = new java.util.Date();
        publicatonQcmField.setValue(LocalDate.now());
        LocalDate localDate = publicatonQcmField.getValue();
        //LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf( localDate );


        ajoutQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Qcm qcm = new Qcm(QcmNamefield.getText(),sqlDate,comboBox2.getValue().toString(),comboBox.getValue().toString(),Integer.parseInt(nb_questionField.getText()),Integer.parseInt(qcmDurationField.getText()), Double.parseDouble(qcmCoefField.getText()));
                qcm.insert();
                Stage creationQuestion = new CreationQuestion(emailAdress, qcm.getTokken(), qcm.getName(), qcm.getNbquestion());
                creationQuestion.show();
                close();
            }
        });



        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(box1, box2, box3);
        box1.getChildren().addAll(QcmName,publicatonQcm,nb_question,qcmDuration,qcmCoef);
        box2.getChildren().addAll(QcmNamefield,publicatonQcmField,comboBox2,comboBox,nb_questionField,qcmDurationField,qcmCoefField);
        box3.getChildren().addAll(ajoutQuestion, undo);

        return layout;
    }


}
