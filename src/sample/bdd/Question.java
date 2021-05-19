package sample.bdd;


import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.sql.Date;

public class Question {

    private String question_id;
    private int question_num;
    private String question_en;
    private String question_rep;
    private boolean question_type; // true = vrai/false and false = saisie libre


    public Question(String question_id, int question_num, String question_en, String question_rep, boolean question_type) {
        this.question_id = question_id;
        this .question_num = question_num + 1;
        this.question_en = question_en;
        this.question_rep = question_rep;
        this.question_type = question_type;
    }

    public int getQuestionNum(){
        return question_num;
    }

    public void insert() {;
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO qcm(question_id,question_num, question_en,question_rep, question_type) VALUES(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,  this.question_id);
            ps.setInt(2, this.question_num);
            ps.setString(3,this.question_en);
            ps.setString(4, this.question_rep);
            ps.setBoolean(5, this.question_type);
            ps.execute();
        }catch(SQLException e) {
            System.out.println(e.toString());
        }
    }
}
