package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Qcm;
import sample.bdd.Verification;

import java.time.LocalDate;

public class CreationQcm extends Stage {
    public CreationQcm(String adressMail) {
        Verification verif = new Verification();
        this.setTitle("Creation QCM : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);

        Scene laScene = new Scene(creerContenu(adressMail, verif),720, 480);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif) {

        VBox layout = new VBox();
        Verification verification = new Verification();

        GridPane gridPane = new GridPane();


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        VBox vBox = new VBox();
        box1.setStyle("-fx-background-color: #994400;");


        Label QcmName = new Label("Entrez de nom du QCM ");
        TextField QcmNamefield = new TextField();
        Label publicatonQcm = new Label("Date publication QCM");
        DatePicker publicatonQcmField = new DatePicker();
        Label combobo2 = new Label("Selectionner un groupe entier");
        ComboBox comboBox2 = new ComboBox();
        comboBox2.getItems().addAll(verification.readSpeData("nomGroupe", "groupe"));

        Label combobo = new Label("Selectionner un demi groupe");
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
        gridPane.setPadding(new Insets(20,20,20,20));

        vBox.getChildren().addAll(QcmName,QcmNamefield);
        box1.getChildren().addAll(vBox);
        box1.setStyle("-fx-background-color: #FE4E28;");
        box1.setAlignment(Pos.CENTER);
        QcmName.setStyle("-fx-font-weight: bold;");
        QcmName.setFont(Font.font ("Verdana", 15));

        gridPane.add(nb_question,0,0,1,1);
        gridPane.add(nb_questionField,0,1,1,1);
        nb_question.setStyle("-fx-font-weight: bold;");
        nb_question.setFont(Font.font ("Verdana", 15));


        gridPane.add(publicatonQcm,2,0,1,1);
        gridPane.add(publicatonQcmField,2,1,1,1);
        publicatonQcm.setStyle("-fx-font-weight: bold;");
        publicatonQcm.setFont(Font.font ("Verdana", 15));


        gridPane.add(combobo2,0,2,1,1);
        gridPane.add(comboBox2,0,3,1,1);
        combobo2.setStyle("-fx-font-weight: bold;");
        combobo2.setFont(Font.font ("Verdana", 15));


        gridPane.add(combobo,2,2,1,1);
        gridPane.add(comboBox,2,3,1,1);
        combobo.setStyle("-fx-font-weight: bold;");
        combobo.setFont(Font.font ("Verdana", 15));


        gridPane.add(qcmDuration,0,4,1,1);
        gridPane.add(qcmDurationField,0,5,1,1);
        qcmDuration.setStyle("-fx-font-weight: bold;");
        qcmDuration.setFont(Font.font ("Verdana", 15));


        gridPane.add(qcmCoef,2,4,1,1);
        gridPane.add(qcmCoefField,2,5,1,1);
        qcmCoef.setStyle("-fx-font-weight: bold;");
        qcmCoef.setFont(Font.font ("Verdana", 15));

        gridPane.setHgap(30);
        gridPane.setVgap(30);

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

        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage HomeReturn = new EnseignantDashbord(emailAdress);
                HomeReturn.show();
                close();
            }
        });



        Insets margin = new Insets(10);

        ajoutQuestion.setStyle("-fx-min-height: 30px; -fx-min-width: 60px");
        ajoutQuestion.setFont(Font.font ("Verdana", 15));
        undo.setStyle("-fx-min-height: 30px; -fx-min-width: 60px");
        undo.setFont(Font.font ("Verdana", 15));
        box2.setSpacing(100.0);

        box1.setPadding(margin);
        box2.getChildren().addAll(undo,ajoutQuestion);

        ajoutQuestion.setAlignment(Pos.CENTER);
        undo.setAlignment(Pos.CENTER);
        box2.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(box1,gridPane, box2);
        /*box1.getChildren().addAll(QcmName,publicatonQcm,nb_question,qcmDuration,qcmCoef);
        box2.getChildren().addAll(QcmNamefield,publicatonQcmField,comboBox2,comboBox,nb_questionField,qcmDurationField,qcmCoefField);
        box3.getChildren().addAll(ajoutQuestion, undo);*/

        return layout;
    }


}
