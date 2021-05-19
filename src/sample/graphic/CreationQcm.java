package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import sample.bdd.Qcm;
import sample.bdd.Verification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
        HBox box3 = new HBox();
        box1.setStyle("-fx-background-color: #00EEff;");


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

        Button qcmCreation = new Button("Creer le qcm");


        Insets margin = new Insets(30);

        box1.setPadding(margin);
        box2.setPadding(margin);
        box3.setPadding(margin);


        java.util.Date now = new java.util.Date();
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(2016, 7, 25));
        datePicker.setShowWeekNumbers(true);

        // Converter
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("dd-MM-yyyy");


        layout.getChildren().addAll(box1, box2, box3);
        box1.getChildren().addAll(QcmName,publicatonQcm,classOwner,groupOwner,nb_question,qcmDuration,qcmCoef);
        box2.getChildren().addAll(QcmNamefield,publicatonQcmField,classOwnerFiel,groupOwnerFiel,nb_questionField,qcmDurationField,qcmCoefField);
        box3.getChildren().addAll(qcmCreation, datePicker);

        qcmCreation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

               LocalDate locald = converter.fromString(/*converter.toString(datePicker.getValue()) +*/ publicatonQcmField.getText());
               System.out.println(locald);



            }
        });

        return layout;
    }


}
