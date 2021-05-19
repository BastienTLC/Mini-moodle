package sample.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionLibre extends Question{

    private String question_asw;
    private  String question_type;
    public QuestionLibre(String question_id, int question_num, String question_en, String question_asw) {
        super(question_id, question_num, question_en);
        this.question_asw = question_asw;
        this.question_type = "libre";
    }


    public void insert() {;
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO qcm(question_id,question_num, question_en,question_asw, question_type) VALUES(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);

            ps.setString(1,  super.question_id);
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
