package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(adressMail, verif, fullValue),1280, 720);
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
        Button nextQuestion = new Button("Question Suivante");
        TextField anwerArea = new TextField();


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        VBox vBox = new VBox();
        HBox rowQCM = new HBox();
        box1.setStyle("-fx-background-color: #994400;");


        System.out.println("---" + printValue[3]);
        InfoQuestion.setText(questionData[0]);
        if (questionNum[0] < Integer.parseInt(printValue[3])){
            final int[] nommeroQuestion = new int[1];
            Indication.setText("repondez a cette question : " + questionDataSplit[0][2]);
            nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //System.out.println("9999" + questionDataSplit[0][4]);
                    //System.out.println("-----" + anwerArea.getText().toString() + questionDataSplit[0][3]);

                    if (questionNum[0]+1 >= Integer.parseInt(printValue[3])){
                        moy[0] = (note[0]/(Integer.parseInt(printValue[3]))*1.0);
                        System.out.println("-----------"+note[0] + "/" + Integer.parseInt(printValue[3]));


                        //Deleter

                        Stage qcmCreationWindow = new QcmJoin(emailAdress/*, qcmdate + "\n" + moy[0]*/);
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
                        }
                        else if(questionDataSplit[0][4].contains("TF")){
                            System.out.println("champ prédéfini");
                        }
                        anwerArea.setText("");
                        Indication.setText("repondez a cette question : " + questionDataSplit[0][2]);
                        questionNum[0] = questionNum[0] + 1;
                        nextQuestion.setText("Question suivante " + questionNum[0] + "/" + Integer.parseInt(printValue[3]));
                        questionData[0] = verification.readAllDatatest("question", "*", "qcm_id", "numeroQuestion", printValue[2], questionNum[0].toString());
                        InfoQuestion.setText(questionData[0]);
                        questionDataSplit[0] = questionData[0].split("\\n");
                        Indication.setText("repondez a cette question : " + questionDataSplit[0][2]);
                    }


                }
            });

            box1.getChildren().addAll(InfoQuestion);
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
