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
import sample.bdd.User;
import sample.bdd.Verification;
public class RegisterInterface extends Stage {
    public RegisterInterface() {
        this.setTitle("Vive l'IHM V2");
        this.setResizable(false);
        this.initStyle(StageStyle.UTILITY);

        Scene registerScene = new Scene(creerContenu(),250, 250);
        this.setScene(registerScene);
    }

    Parent creerContenu() {

        VBox layout = new VBox();

        Label connexion = new Label("Inscription au mini moodle");
        HBox box1 = new HBox();
        HBox box2 = new HBox();
        HBox box3 = new HBox();
        HBox box4 = new HBox();
        HBox box5 = new HBox();


        Label nom = new Label("Nom : ");
        TextField nom2 = new TextField();
        Label prenom = new Label("Prenom : ");
        TextField prenom2 = new TextField();
        Label adressMail = new Label("Adresse mail : ");
        TextField adress = new TextField();
        Label password = new Label("Mot de passe: ");
        PasswordField password2 = new PasswordField();

        Label label = new Label("Your Gender: ");
        ToggleGroup group = new ToggleGroup();
        RadioButton buttonStudent = new RadioButton("etudiant");
        buttonStudent.setToggleGroup(group);
       // buttonStudent.setSelected(true);
        RadioButton buttonProf = new RadioButton("enseignant");
        buttonProf.setToggleGroup(group);


        Button undo = new Button("Annuler");
        Button ok = new Button("Ok");
        Button login = new Button("login");

        undo.setPrefWidth(80);
        ok.setPrefWidth(80);
        nom2.setPrefWidth(120);
        password2.setPrefWidth(120);

        Insets margin = new Insets(10);

        connexion.setPadding(margin);
        box1.setPadding(margin);
        box2.setPadding(margin);
        box3.setPadding(margin);


        layout.getChildren().addAll(connexion, box1, box2, box3, box4, box5);
        box1.getChildren().addAll(nom, nom2, prenom, prenom2);
        box2.getChildren().addAll(adressMail, adress);
        box3.getChildren().addAll(password, password2);
        box4.getChildren().addAll(label, buttonStudent, buttonProf);
        box5.getChildren().addAll(ok, undo, login);

        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage loginWindow = new LoginInterface();
                loginWindow.show();
                close();
            }
        });
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Verification verif = new Verification();

                if (adress.getText().equals("") || password2.getText().equals("") || nom2.getText().equals("") || prenom2.getText().equals("")){
                    System.out.println("champ non saisie");
                }
                else if(!adress.getText().contains("@") && !adress.getText().contains(".")){
                    System.out.println("Adresse mail non valide");
                }
                else if (verif.doubleUser(adress.getText())){
                    System.out.println("Cette adresse mail existe déjà");
                }
                else{
                    System.out.println("Ok");
                    System.out.println(adress.getText());
                        if(buttonProf.isSelected()){
                            User user = new User(nom2.getText(), prenom2.getText(), adress.getText(), password2.getText(), buttonProf.getText());
                            user.insert();
                            Stage enseignantWindow = new EnseignantDashbord(adress.getText());
                            enseignantWindow.show();
                            close();
                        }
                        else if (buttonStudent.isSelected()){
                            User user = new User(nom2.getText(), prenom2.getText(), adress.getText(), password2.getText(), buttonStudent.getText());
                            user.insert();
                            Stage etudiantWindow = new EtudiantDashbord(adress.getText());
                            etudiantWindow.show();
                            close();

                        }
                        else{
                            System.out.println("Status no");
                        }
                }
            }
        });

        return layout;
    }


}
