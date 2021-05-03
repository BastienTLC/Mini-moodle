package sample.graphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginInterface extends Stage {
    public LoginInterface() {
        this.setTitle("Vive l'IHM V2");
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene laScene = new Scene(creerContenu(),250, 250);
        this.setScene(laScene);
    }

    Parent creerContenu() {

        VBox layout = new VBox();

        Label connexion = new Label("Connexion au mini moodle");
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();


        Label nom = new Label("Nom : ");
        TextField nom2 = new TextField();
        Label password = new Label("Mot de passe: ");
        PasswordField password2 = new PasswordField();

        Button undo = new Button("Annuler");
        Button ok = new Button("Ok");
        Button register = new Button("register");

        undo.setPrefWidth(80);
        ok.setPrefWidth(80);
        nom2.setPrefWidth(120);
        password2.setPrefWidth(120);

        Insets margin = new Insets(10);

        connexion.setPadding(margin);
        box1.setPadding(margin);
        box2.setPadding(margin);
        box3.setPadding(margin);


        layout.getChildren().addAll(connexion, box1, box2, box3);
        box1.getChildren().addAll(nom, nom2);
        box2.getChildren().addAll(password, password2);
        box3.getChildren().addAll(ok, undo, register);

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("yo");
                Stage registerWindow = new RegisterInterface();
                registerWindow.show();
                close();


            }
        });

        return layout;
    }


}
