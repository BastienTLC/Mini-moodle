package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        this.initStyle(StageStyle.DECORATED);

        Scene laScene = new Scene(creerContenu(adressMail, verif, qcmToken, qcmName, nombreQuestion),720, 480);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif, String qcmToken, String qcmName, int nombreQuestion) {
        final Integer[] nombre = {1};
        VBox layout = new VBox();
        HBox box1 = new HBox();
        box1.setAlignment(Pos.CENTER);
        box1.setStyle("-fx-background-color: #FE4E28;");
        HBox box2 = new HBox();
        box2.setAlignment(Pos.CENTER);
        box2.setStyle("-fx-background-color: #ffffff;");

        HBox box3 = new HBox();
        box3.setAlignment(Pos.BOTTOM_CENTER);
        box3.setSpacing(220);
        HBox box4 = new HBox();
        box4.setStyle("-fx-min-height: 220px");
        box4.setAlignment(Pos.BOTTOM_CENTER);
        box4.setSpacing(220);

        VBox question = new VBox();
        question.setStyle("-fx-min-height: 150px");
        question.setAlignment(Pos.CENTER);
        question.setPadding(new Insets(0,5,0,10));
        VBox anwers = new VBox();
        anwers.setStyle("-fx-min-height: 150px");
        anwers.setAlignment(Pos.CENTER);
        anwers.setPadding(new Insets(0,10,0,5));

        Label informationQuestion = new Label("QUESTION : " + nombre[0]);
        //informationQuestion.setStyle();
        informationQuestion.setStyle("-fx-font-weight: bold;");
        informationQuestion.setFont(Font.font ("Verdana", 20));
        informationQuestion.setAlignment(Pos.CENTER);

        CheckBox cb = new CheckBox("Question libre ?");
        cb.setIndeterminate(false);

        Label ennonce = new Label("La question ");
        TextField ennonceField = new TextField();
        ennonceField.setStyle("-fx-min-height: 40px; -fx-background-color: #eeeeee; -fx-font-size: 30px; -fx-max-width: 600px");

        Label reponse = new Label("La réponse");
        TextField reponseFiel = new TextField();
        reponseFiel.setStyle("-fx-min-height: 40px; -fx-background-color: #eeeeee; -fx-font-size: 30px; -fx-max-width: 600px");


        Label NumeroQuestion = new Label();

        Button newquestion = new Button("Ajouter et passer a la question suivante");
        newquestion.setStyle("-fx-max-height: 60px;");
        Button previousquestion = new Button("retourner a la question précedente");
        previousquestion.setStyle("-fx-max-height: 60px");
        Button edit = new Button("edit");
        Button delete = new Button("supprimer");

        Button terminer = new Button("Terminer");

        ComboBox comboBox = new ComboBox();
        comboBox.setStyle("-fx-max-height: 50px; -fx-min-height: 60px;-fx-max-width: 200px; -fx-min-width: 200px;");
        comboBox.getItems().add("Vrai");
        comboBox.getItems().add("Faux");
        comboBox.getItems().add("Aucune");

        cb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String questionDebug = verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString());
                String questionTabDebug[] = questionDebug.split("\\n");
                if (cb.isSelected() == true){
                    System.out.println("coché");

                    question.getChildren().setAll(ennonce,ennonceField);
                    anwers.getChildren().setAll(reponse,reponseFiel);
                    box1.getChildren().setAll(informationQuestion);
                    box3.getChildren().setAll(cb);
                    box4.getChildren().setAll(previousquestion,newquestion);

                }
                else if (cb.isSelected() == true && questionTabDebug[3] != ""){
                    if (questionTabDebug[3] == "libre"){
                        question.getChildren().setAll(ennonce,ennonceField);
                        anwers.getChildren().setAll(reponse,reponseFiel);
                        box1.getChildren().setAll(informationQuestion);
                        box3.getChildren().setAll(cb);
                        box4.getChildren().setAll(previousquestion,newquestion);
                    }
                    else{

                        question.getChildren().setAll(ennonce,ennonceField);
                        anwers.getChildren().setAll(reponse,comboBox);
                        box1.getChildren().setAll(informationQuestion);
                        box3.getChildren().setAll(cb);
                        box4.getChildren().setAll(previousquestion,newquestion);
                    }

                }
                else{
                    System.out.println("decouché");

                    question.getChildren().setAll(ennonce,ennonceField);
                    anwers.getChildren().setAll(reponse,comboBox);
                    box1.getChildren().setAll(informationQuestion);
                    box2.getChildren().setAll(question,anwers);
                    box3.getChildren().setAll(cb);
                    box4.getChildren().setAll(previousquestion,newquestion);
                }
            }
        });

        newquestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (nombre[0] < nombreQuestion){
                    if (cb.isSelected() == true){
                        ennonceField.setText("");
                        reponseFiel.setText("");
                        QuestionLibre questionLibre = new QuestionLibre(qcmToken, nombre[0], ennonceField.getText(), reponseFiel.getText());
                        System.out.println(questionLibre);
                        questionLibre.insert();

                    }
                    else{
                        ennonceField.setText("");
                        reponseFiel.setText("");
                        QuestionTF questiontf = new QuestionTF(qcmToken, nombre[0], ennonceField.getText(),  comboBox.getValue().toString());
                        System.out.println(questiontf);
                        questiontf.insert();
                    }
                    nombre[0] = nombre[0] + 1;
                    NumeroQuestion.setText( "information QCM " + "\n" +verif.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", qcmToken, nombre[0].toString()));
                    System.out.println(nombre[0]);
                    informationQuestion.setText("Question numero :" + nombre[0]);
                    box3.getChildren().setAll(cb);
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
                    //box2.getChildren().setAll(previousquestion,ennonceField,reponseFiel,comboBox, newquestion, edit, NumeroQuestion);
                    question.getChildren().setAll(ennonce,ennonceField);
                    anwers.getChildren().setAll(reponse,reponseFiel);
                    box1.getChildren().setAll(informationQuestion);
                    box3.getChildren().setAll(cb,edit);
                    box4.getChildren().setAll(previousquestion,newquestion);

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
        question.getChildren().addAll(ennonce, ennonceField);
        anwers.getChildren().addAll(reponse, reponseFiel);
        box1.getChildren().addAll(informationQuestion, NumeroQuestion);
        box2.getChildren().addAll(question, anwers);
        box3.getChildren().addAll(cb);
        box4.getChildren().addAll(newquestion);
        layout.getChildren().addAll(box1, box2, box3,box4);


        return layout;
    }


}
