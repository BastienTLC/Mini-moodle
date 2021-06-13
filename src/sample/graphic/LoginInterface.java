package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.bdd.Verification;

public class LoginInterface extends Stage {
    public LoginInterface() {
        this.setTitle("Connexion");
        this.setResizable(false);
        this.initStyle(StageStyle.DECORATED);

        Scene laScene = new Scene(creerContenu(),250, 250);
        this.setScene(laScene);
    }

    Parent creerContenu() {

        VBox layout = new VBox();

        Label connexion = new Label("Connexion au mini moodle");
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        connexion.setFont(Font.font ("Verdana", 16));
        box1.setStyle("-fx-background-color: #FE4E28;");
        box2.setStyle("-fx-background-color: #FE4E28;");
        box3.setStyle("-fx-background-color: #FE4E28;");
        layout.setStyle("-fx-background-color: #FE4E28;");



        Label email = new Label("email : ");
        email.setFont(Font.font ("Verdana", 12));
        TextField email2 = new TextField();
        Label password = new Label("Mot de passe: ");
        password.setFont(Font.font ("Verdana", 12));
        PasswordField password2 = new PasswordField();

        Button undo = new Button("Annuler");
        Button ok = new Button("Valider");
        Button register = new Button("Inscription");

        //undo.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 70px;-fx-font-weight: bold;-fx-min-height: 50px;");
        //ok.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 70px;-fx-font-weight: bold;-fx-min-height: 50px;");
        //register.setStyle("-fx-background-color: #D40000; -fx-text-fill: white;-fx-min-width: 70px;-fx-font-weight: bold;-fx-min-height: 50px;");


        undo.setPrefWidth(80);
        ok.setPrefWidth(80);
        email2.setPrefWidth(120);
        password2.setPrefWidth(120);

        Insets margin = new Insets(10);

        connexion.setPadding(margin);
        box1.setPadding(margin);
        box2.setPadding(margin);
        box3.setPadding(margin);


        layout.getChildren().addAll(connexion, box1, box2, box3);
        box1.getChildren().addAll(email, email2);
        box2.getChildren().addAll(password, password2);
        box3.getChildren().addAll(ok, register, undo);

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("yo");
                Stage registerWindow = new RegisterInterface();
                registerWindow.show();
                close();


            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Verification verif = new Verification();
                if (verif.doubleUser(email2.getText())){
                    System.out.println("compte trouvé");
                    if (verif.readSpecificRow("password",  email2.getText()).equals(password2.getText())){
                        System.out.println("Mdp correct");
                        if (verif.readSpecificRow("status", email2.getText()).equals("etudiant")){
                            System.out.println("open");
                            Stage etudiantWindow = new EtudiantDashbord(email2.getText());
                            etudiantWindow.show();
                            close();
                        }
                        else if (verif.readSpecificRow("status", email2.getText()).equals("enseignant")){
                            Stage enseingnantWindow = new EnseignantDashbord(email2.getText());
                            enseingnantWindow.show();
                            close();
                        }


                    }
                    else{
                        System.out.println("mdp incorect");
                    }
                }
                else{
                    System.out.println("Compte introuvable");
                }



            }
        });

        undo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });

        return layout;
    }


}
