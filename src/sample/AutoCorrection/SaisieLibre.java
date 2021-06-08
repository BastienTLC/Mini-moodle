package sample.AutoCorrection;

import java.util.ArrayList;



public class SaisieLibre {
    private String SaisieUtilisateur;
    private ArrayList<String> listeReponse;

    public SaisieLibre(String SaisieUtilisateur, ArrayList<String> listeReponse){
        this.SaisieUtilisateur = SaisieUtilisateur;
        this.listeReponse = listeReponse;
    }

    public boolean isRight(){
        boolean isright = false;
        // est contenu dans le tableau
        if (listeReponse.contains(SaisieUtilisateur)){
            isright = true;
        }
        else
        {
            isright = false;
        }
        // chaine correct contient les maj au mauvaise endroit
        for(String s : listeReponse) {
            if (s.equalsIgnoreCase(SaisieUtilisateur)){
                isright=true;
            }
        }
        // Manque des caractères

        isright = manqueChar();



        return  isright;
    }


    public boolean manqueChar(){
        boolean isright = false;
        for(String s : listeReponse) {
            int nombreFauteMot = 0;
            for (int w = 0; w < s.length(); w++){
                if (s.charAt(w) != SaisieUtilisateur.charAt(w)){
                    int decalage = w;
                    nombreFauteMot = nombreFauteMot + 1;
                    System.out.println("Faux");
                    System.out.println(s.charAt(w) + " != " + SaisieUtilisateur.charAt(decalage+1));
                    if (s.charAt(w) == SaisieUtilisateur.charAt(decalage+1)){
                        nombreFauteMot = nombreFauteMot - 1;
                        System.out.println(nombreFauteMot);
                    }
                    else{
                        nombreFauteMot = nombreFauteMot + 1;
                    }
                }
                System.out.println(s.length() + " " + SaisieUtilisateur.length());

                if (nombreFauteMot > 1){
                    isright = false;
                }
                else{
                    isright = true;
                }
            }
        }
        return  isright;
    }

}
