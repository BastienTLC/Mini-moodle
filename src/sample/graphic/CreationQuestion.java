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
import sample.bdd.QuestionLibre;
import sample.bdd.QuestionTF;
import sample.bdd.Verification;

public class CreationQuestion extends Stage {
    public CreationQuestion(String adressMail, String qcmToken, String qcmName, int nombreQuestion) {
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(adressMail, verif, qcmToken, qcmName, nombreQuestion),1280, 720);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif, String qcmToken, String qcmName, int nombreQuestion) {
        final Integer[] nombre = {0};
        VBox layout = new VBox();
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        box1.setStyle("-fx-background-color: #00ffff;");

        Label informationQuestion = new Label("Question numero :" + nombre[0]);

        CheckBox cb = new CheckBox("Question libre ?");
        cb.setIndeterminate(false);

        Label ennonce = new Label("Votre question. ");
        TextField ennonceField = new TextField();

        Label reponse = new Label("Date publication QCM");
        TextField reponseFiel = new TextField();

        Label NumeroQuestion = new Label("INFORMATION");

        Button newquestion = new Button("Ajouter et passer a la question suivante");
        Button previousquestion = new Button("retourner a la question précedente");
        Button edit = new Button("edit");
        Button delete = new Button("supprimer");

        Button terminer = new Button("Terminer");

        ComboBox comboBox = new ComboBox();

        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String questionDebug = verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString());
                String questionTabDebug[] = questionDebug.split("\\n");
                if (cb.isSelected() == true){
                    System.out.println("coché");
                    box1.getChildren().setAll(ennonce,reponse, informationQuestion);
                    box2.getChildren().setAll(previousquestion,ennonceField,reponseFiel, newquestion);

                }
                else if (cb.isSelected() == true && questionTabDebug[3] != ""){
                    if (questionTabDebug[3] == "libre"){
                        box1.getChildren().setAll(ennonce,reponse, informationQuestion);
                        box2.getChildren().setAll(previousquestion,ennonceField,reponseFiel, newquestion);
                    }
                    else{
                        comboBox.getItems().add("Vrai");
                        comboBox.getItems().add("Faux");
                        comboBox.getItems().add("Aucune");
                        box1.getChildren().setAll(ennonce, reponse, informationQuestion);
                        box2.getChildren().setAll(previousquestion,ennonceField,comboBox, newquestion);
                    }

                }
                else{
                    System.out.println("decouché");
                    comboBox.getItems().add("Vrai");
                    comboBox.getItems().add("Faux");
                    comboBox.getItems().add("Aucune");
                    box1.getChildren().setAll(ennonce, reponse, informationQuestion);
                    box2.getChildren().setAll(previousquestion,ennonceField,comboBox, newquestion);
                }
            }
        });

        newquestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (nombre[0] < nombreQuestion){
                    if (cb.isSelected() == true){
                        QuestionLibre questionLibre = new QuestionLibre(qcmToken, nombre[0], ennonceField.getText(), reponseFiel.getText());
                        System.out.println(questionLibre);
                        questionLibre.insert();

                    }
                    else{
                        QuestionTF questiontf = new QuestionTF(qcmToken, nombre[0], ennonceField.getText(),  comboBox.getValue().toString());
                        System.out.println(questiontf);
                        questiontf.insert();
                    }
                    nombre[0] = nombre[0] + 1;
                    NumeroQuestion.setText( "information QCM " + "\n" +verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString()));
                    System.out.println(nombre[0]);
                    informationQuestion.setText("Question numero :" + nombre[0]);
                }
                else{
                    box1.getChildren().add(terminer);
                }
                }

        });

        previousquestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String questionDebug;
                String questionTabDebug[];
                if (nombre[0] > 0){
                    nombre[0] = nombre[0] - 1;
                    informationQuestion.setText("Question numero :" + nombre[0]);
                    //Label questionInfo = new Label(verif.readAllDatatest());
                    //String test = verif.readSpecificRowtest();
                    questionDebug = verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString());
                    NumeroQuestion.setText( "information QCM " + "\n" + questionDebug);
                    questionTabDebug = questionDebug.split("\\n");

                    for(String w:questionTabDebug){
                        System.out.println(w);
                    }
                    ennonceField.setText(String.valueOf(questionTabDebug[2]));
                    box2.getChildren().setAll(previousquestion,ennonceField,reponseFiel,comboBox, newquestion, edit, NumeroQuestion);


                }
            }
        });

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                verif.updateRow("question", "question", ennonceField.getPromptText(), "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString());
                verif.updateRow("question", "question", reponse.getText(), "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString());
                NumeroQuestion.setText( "information QCM " + "\n" +verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString()));
            }
        });

        terminer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage qcmCreationWindow = new CreationQcm(emailAdress);
                qcmCreationWindow.show();
                close();
            }
        });



        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);

        box1.getChildren().addAll(informationQuestion, NumeroQuestion);
        layout.getChildren().addAll(box1, box2, cb);


        return layout;
    }


}
