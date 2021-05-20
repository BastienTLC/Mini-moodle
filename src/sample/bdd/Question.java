package sample.bdd;


import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.sql.Date;

public abstract class Question {

    public String qcm_id;
    public int question_num;
    public String question_en;
    public String question_rep;


    public Question(String qcm_id, int question_num, String question_en) {
        this.qcm_id = qcm_id;
        this .question_num = question_num;
        this.question_en = question_en;
    }


    public int getQuestionNum(){
        return question_num;
    }
}
