package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Question;
import sample.bdd.Verification;

import java.awt.*;

public class CreationQuestion extends Stage {
    public CreationQuestion(String adressMail, String qcmToken, String qcmName) {
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(adressMail, verif, qcmToken, qcmName),1280, 720);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif, String qcmToken, String qcmName) {
        Integer nombre = 0;
        VBox layout = new VBox();
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        box1.setStyle("-fx-background-color: #00ffff;");

        CheckBox cb = new CheckBox("Question libre ?");
        cb.setIndeterminate(false);

        Label ennonce = new Label("Votre question. ");
        TextField ennonceField = new TextField();

        Label reponse = new Label("Date publication QCM");
        TextField reponseFiel = new TextField();

        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cb.isSelected() == true){
                    System.out.println("coché");


                    box1.getChildren().setAll(ennonce,reponse);
                    box2.getChildren().setAll(ennonceField,reponseFiel);
                }
                else{
                    System.out.println("decouché");


                    ComboBox comboBox = new ComboBox();


                    comboBox.getItems().add("Vrai");
                    comboBox.getItems().add("Faux");
                    comboBox.getItems().add("Aucune");
                    box1.getChildren().setAll(ennonce, reponse);
                    box2.getChildren().setAll(ennonceField,comboBox);
                }
            }
        });







        Button nextQuestion = new Button("Question Suivante");
        Button previousQuestion = new Button("Question precedente");
        Button retuneQcmInterface = new Button("Retourner a l'interface QCM");

        nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nombre.equals(nombre+1);
               // Question question = new Question(qcmToken, nombre, ennonceField.getText(), reponseFiel.getText());
            }
        });


        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(box1, box2, cb);


        return layout;
    }


}
