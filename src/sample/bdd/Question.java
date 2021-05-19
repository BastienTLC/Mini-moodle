package sample.bdd;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

public class Question {
    private String question_id;
    private int question_num;
    private String question_en;
    private String question_awn;

    public Question(String question_id, int question_num, String question_en, String question_awn) {
        super();
        this.question_id = question_id;
        this.question_num = question_num + 1;
        this.question_en = question_en;
        this.question_awn = question_awn;
    }

    public void insert() {
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO qcm(question_id,question_num, question_en,question_awn) VALUES(?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,  this.question_id);
            ps.setInt(2, this.question_num);
            ps.setString(3,this.question_en);
            ps.setString(4,  this.question_awn);
            ps.execute();
        }catch(SQLException e) {
            System.out.println(e.toString());
        }
    }


    private static String generateNewToken() {
        final SecureRandom secureRandom = new SecureRandom();
        final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        System.out.println(base64Encoder.encodeToString(randomBytes));
        return base64Encoder.encodeToString(randomBytes);
    }


}

