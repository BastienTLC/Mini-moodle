package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.UserData;
import sample.bdd.Verification;

import java.util.Optional;

public class QcmJoin extends Stage {
    public QcmJoin(String adressMail, String valeur) {
        Verification verif = new Verification();
        this.setTitle("Dashbord : "+ verif.readSpecificRow("firstname", adressMail) +" "+  verif.readSpecificRow("secondName", adressMail));
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);

        Scene laScene = new Scene(creerContenu(adressMail, verif, valeur),720, 480);
        this.setScene(laScene);
    }

    Parent creerContenu(String emailAdress, Verification verif, String valeur) {

        Label Title = new Label("Vos QCM");
        Title.setPadding(new Insets(20, 0,20,310));
        Title.setStyle("-fx-font-weight: bold;");
        Title.setFont(Font.font ("Verdana", 20));

        VBox layout = new VBox();
        Button returneHome = new Button ("Accueil");
        Button setting = new Button ("Parametre");
        Button deconexion = new Button ("Se déconnecter");


        HBox box1 = new HBox();
        HBox box2 = new HBox();
        VBox Vbox = new VBox();
        VBox ListeQCM = new VBox();
        HBox rowQCM = new HBox();
        Vbox.setStyle("-fx-background-color: #FE4E28;-fx-min-height: 500px");
        box1.setStyle("-fx-background-color: #FE4E28;");
        box2.setStyle("-fx-background-color: #FE4E28;");
        returneHome.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 150px;-fx-font-weight: bold;-fx-min-height: 50px;");
        setting.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 150px;-fx-font-weight: bold;-fx-min-height: 50px;");
        deconexion.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 150px;-fx-font-weight: bold;-fx-min-height: 50px;");



        Verification verification = new Verification();


        char temps = verification.readSpecificRow("groupe", emailAdress).charAt(0);

        System.out.println("++++++++++++++" + verification.readQcmGroup(verification.readSpecificRow("groupe", emailAdress),String.valueOf(verification.readSpecificRow("groupe", emailAdress).charAt(0))));
        System.out.println("++++++++++++++" + verification.readQcmGroup("H1","H"));
        System.out.println("++++++++++++++" + verification.readSpecificRow("groupe", emailAdress));
        System.out.println("++++++++++++++" + verification.readSpecificRow("groupe", emailAdress).charAt(0));

            for( String value : verification.readQcmGroup(verification.readSpecificRow("groupe", emailAdress),String.valueOf(verification.readSpecificRow("groupe", emailAdress).charAt(0)))) {
                String fullValue = value;
                String[] printValue = fullValue.split("\\n");
                if (verification.checkUserQcm(printValue[2], emailAdress) == false){
                    Button qcmJoin = new Button("REJOINDRE QMC : " + printValue[0] + " \n "+ printValue[1]);
                    qcmJoin.setStyle("-fx-background-color: \n" +
                            "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
                            "        linear-gradient(#020b02, #3a3a3a),\n" +
                            "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\n" +
                            "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);\n" +
                            "    -fx-background-insets: 0,1,4,5;\n" +
                            "    -fx-background-radius: 9,8,5,4;\n" +
                            "    -fx-padding: 15 30 15 30;\n" +
                            "    -fx-font-family: \"Helvetica\";\n" +
                            "    -fx-font-size: 18px;\n" +
                            "    -fx-font-weight: bold;\n" +
                            "    -fx-text-fill: #333333;\n" +
                            "    -fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);" +
                            "-fx-min-width: 500px");
                    ListeQCM.getChildren().addAll(qcmJoin);
                    qcmJoin.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Dialog");
                            alert.setHeaderText("Etes-vous sur de bien vouloir démarer une tentative");
                            alert.setContentText("OK pour lancer la tentative");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                UserData setUserData = new UserData(emailAdress,printValue[2]);
                                setUserData.insert();
                                Stage ReponseQuestion = new ReponseQuestion(emailAdress, value);
                                ReponseQuestion.show();
                                close();
                            } else {
                            }
                        }
                    });
                    returneHome.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage Dashbord = new EtudiantDashbord(emailAdress);
                            Dashbord.show();
                            close();
                        }
                    });

                }
                else{
                }
            }


        Insets margin = new Insets(10);

        box1.setPadding(margin);
        box2.setPadding(new Insets(0,0,50,0));
        Vbox.setPadding(new Insets(0,40,50,10));

        Vbox.getChildren().addAll();
        layout.getChildren().addAll(box1, box2, Vbox);
        Vbox.getChildren().addAll(setting, returneHome, deconexion);
        ListeQCM.getChildren().addAll(rowQCM);
        box1.getChildren().addAll(Title);
        box2.getChildren().addAll(Vbox,ListeQCM);
        Vbox.getChildren().addAll();

        return layout;
    }


}
