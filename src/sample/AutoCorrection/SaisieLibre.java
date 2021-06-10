package sample.AutoCorrection;




public class SaisieLibre {
    private String SaisieUtilisateur;
    private String Reponse;

    public SaisieLibre(String SaisieUtilisateur, String Reponse){
        this.SaisieUtilisateur = SaisieUtilisateur;
        this.Reponse = Reponse;
    }

    public boolean isRight(){
        boolean isTheSame = false;

        if(SaisieUtilisateur.equalsIgnoreCase(Reponse)){
            isTheSame = true;
        }
        return  isTheSame;
    }


    /*public boolean manqueChar(){
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
    }*/

}
