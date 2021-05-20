package sample.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionTF extends  Question{
    private String question_asw;
    private String question_type;
    public QuestionTF(String qcm_id, int question_num, String question_en, String question_asw) {
        super(qcm_id, question_num, question_en);
        this.question_asw = question_asw;
        this.question_type = "TF";
    }

    public void insert() {;
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO question(qcm_id,numeroQuestion, question,reponse, typeQuestion) VALUES(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);

            ps.setString(1,  super.qcm_id);
            ps.setInt(2, super.question_num);
            ps.setString(3,super.question_en);
            ps.setString(4, this.question_asw);
            ps.setString(5, this.question_type);
            ps.execute();
        }catch(SQLException e) {
            System.out.println(e.toString());
        }
    }
}
