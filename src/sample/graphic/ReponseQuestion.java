package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.AutoCorrection.SaisieLibre;
import sample.bdd.Verification;

public class ReponseQuestion extends Stage {
    public ReponseQuestion(String adressMail, String valueQCM) {
        String fullValue = valueQCM;
        String[] printValue = fullValue.split("\\n");
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail) +" " + "QCM en cours : " + printValue[0]);
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);

        Scene laScene = new Scene(creerContenu(adressMail, verif, fullValue),720, 480);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif, String qcmdate) {
        Verification verification = new Verification();
        final Integer[] questionNum = {0};
        final int[] note = {0};
        final double[] moy = {0};

        VBox layout = new VBox();
        String fullValue = qcmdate;
        String[] printValue = fullValue.split("\\n");
        final String[] questionData = {verification.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", printValue[2], questionNum[0].toString())};
        final String[][] questionDataSplit = {questionData[0].split("\\n")};
        Label InfoQuestion = new Label();
        Label Indication = new Label();
        Indication.setPadding(new Insets(20, 0,10,0));
        Indication.setStyle("-fx-font-weight: bold; -fx-text-alignment: CENTER");
        Indication.setFont(Font.font ("Verdana", 30));

        Label TitlePage = new Label("Répondez a cette question");
        TitlePage.setPadding(new Insets(20, 0,0,220));
        TitlePage.setAlignment(Pos.CENTER);
        TitlePage.setStyle("-fx-font-weight: bold;");
        TitlePage.setFont(Font.font ("Verdana", 20));

        Button nextQuestion = new Button("Question Suivante");
        nextQuestion.setStyle("-fx-background-color: #FE4E28; -fx-text-fill: white;-fx-min-width: 150px;-fx-font-weight: bold;-fx-min-height: 100px;");


        TextField anwerArea = new TextField();
        anwerArea.setStyle("-fx-min-height: 40px; -fx-background-color: #eeeeee; -fx-font-size: 30px; -fx-max-width: 600px");
        anwerArea.setAlignment(Pos.CENTER);



        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        VBox vBox = new VBox();
        HBox rowQCM = new HBox();

        box1.setStyle("-fx-background-color: #FE4E28;");
        layout.setStyle("-fx-background-color: #FFFFFF;");
        //layout.setFont(Font.font ("Verdana", 16));

        ComboBox comboBox = new ComboBox();
        comboBox.setStyle("-fx-max-height: 50px; -fx-min-height: 60px;-fx-max-width: 200px; -fx-min-width: 200px;");
        comboBox.getItems().add("Vrai");
        comboBox.getItems().add("Faux");
        comboBox.getItems().add("Aucune");


        System.out.println("---" + printValue[3]);
        //InfoQuestion.setText(questionData[0]);
        if (questionNum[0] < Integer.parseInt(printValue[3])){
            final int[] nommeroQuestion = new int[1];
            Indication.setText(questionDataSplit[0][2]);
            nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println("9999" + questionDataSplit[0][4]);
                    //System.out.println("-----" + anwerArea.getText().toString() + questionDataSplit[0][3]);

                    if (questionNum[0]+1 >= Integer.parseInt(printValue[3])){
                        moy[0] = (note[0]/(Integer.parseInt(printValue[3]))*1.0);
                        System.out.println("-----------"+note[0] + "/" + Integer.parseInt(printValue[3]));



                        Stage qcmCreationWindow = new QcmJoin(emailAdress, null);
                        qcmCreationWindow.show();
                        close();
                    }
                    else {
                        if (questionDataSplit[0][4].contains("libre")){
                            System.out.println("ChampLibre");
                            SaisieLibre anwerCheck = new SaisieLibre(anwerArea.getText().toString(), questionDataSplit[0][3]);
                            if (anwerCheck.isRight()){
                                System.out.println("Bonne réponse");
                                note[0] = note[0] + 1;
                            }
                            else{
                                System.out.println("Mauvaise réponse");
                                note[0] = note[0] - 1;
                            }
                            vBox.getChildren().setAll(Indication, anwerArea);
                        }
                        else if(questionDataSplit[0][4].contains("TF")){
                            System.out.println("champ prédéfini");
                            vBox.getChildren().setAll(Indication, comboBox);
                        }
                        anwerArea.setText("");
                        Indication.setText(questionDataSplit[0][2]);
                        questionNum[0] = questionNum[0] + 1;
                        nextQuestion.setText("Question suivante " + questionNum[0] + "/" + Integer.parseInt(printValue[3]));
                        questionData[0] = verification.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", printValue[2], questionNum[0].toString());
                        //InfoQuestion.setText(questionData[0]);
                        questionDataSplit[0] = questionData[0].split("\\n");
                        Indication.setText(questionDataSplit[0][2]);
                    }


                }
            });
            Insets margin = new Insets(10);
            vBox.setAlignment(Pos.CENTER);
            box2.setAlignment(Pos.CENTER);
            box3.setAlignment(Pos.BOTTOM_RIGHT);
            box3.setPadding(new Insets(0,10,0,0));
            box1.getChildren().addAll(InfoQuestion, TitlePage);
            vBox.getChildren().addAll(Indication, anwerArea);
            box2.getChildren().addAll(vBox);
            box3.getChildren().addAll(nextQuestion);


        }

        else{

        }

        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(margin);


        layout.getChildren().addAll(box1, box2, box3);

        return layout;
    }


}
