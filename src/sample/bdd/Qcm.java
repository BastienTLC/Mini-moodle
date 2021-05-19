package sample.bdd;


import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.sql.Date;

public class Qcm {

    private String qcm_id = generateNewToken();
    private String qcm_name;
    private Date qcm_date;
    private String class_owner;
    private String group_owner;
    private int nb_question;
    private int Temps;
    private Double coef;


    public Qcm(String qcm_name,Date qcm_date, String class_owner, String group_owner, int nb_question, int Temps, Double coef) {
        super();
        this.qcm_name = qcm_name;
        this.qcm_date = qcm_date;
        this.class_owner = class_owner;
        this.group_owner = group_owner;
        this.nb_question = nb_question;
        this.Temps = Temps;
        this.coef = coef;
    }

    public void insert() {
        Connection connection = DbConnection.connect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO qcm(qcm_id,qcm_name, qcm_date,qcm_time ,class_owner, group_owner, nb_question, Temps, coef) VALUES(?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,  this.qcm_id);
            ps.setString(2, this.qcm_name);
            ps.setDate(3,this.qcm_date);
            ps.setString(4,  this.class_owner);
            ps.setString(5,  this.group_owner);
            ps.setInt(6,  this.nb_question);
            ps.setInt(7, this.Temps);
            ps.setDouble(8, this.coef);
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
    public String getTokken(){
        return qcm_id;
    }
    public String getName(){
        return qcm_name;
    }


}
