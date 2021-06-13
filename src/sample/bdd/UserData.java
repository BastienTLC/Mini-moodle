package sample.bdd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserData {

    private String email;
    private String etablissement;
    private String qcm;



    public UserData(String email, String qcm) {
        super();
        this.email = email;
        this.etablissement = "IUT LANNION";
        this.qcm = qcm;

    }

    public void insert() {
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO user_data(email, etablissement,qcm) VALUES(?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,  this.email);
            ps.setString(2,  this.etablissement);
            ps.setString(3,  this.qcm);
            ps.execute();
        }catch(SQLException e) {
            System.out.println(e.toString());
        }
    }

}
