package sample.bdd;

import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
    public void readAllData(){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM user";
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                String firstName = rs.getString("firstname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String secondName = rs.getString("secondName");

                System.out.println("First Name " + firstName);
                System.out.println("Second Name" + secondName);
                System.out.println("email" + email);
                System.out.println("password" + password);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            try {
                rs.close();;
                ps.close();
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        }



    public void readSpeData(String data){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT " + data  + " FROM user";
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                String dataContain = rs.getString(data);

                System.out.println(data + " " +  dataContain);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }

    public boolean doubleUser(String emailAdress){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isFind = false;

        try {
            String sql = "SELECT " + "email"  + " FROM user";
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next() && isFind != true){
                String dataContain = rs.getString("email");
                if(emailAdress.equals(dataContain)){
                    isFind = true;
                }
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
        return isFind;
    }









    /*private  static  void  readSpecificRow(String row){
        Connection con = DbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "Select " + row +" from users where email = ?";
            ps.setString(1,);
        }catch (SQLException e){
            System.out.println(e.toString());
        }finally {
            try {
                rs.close();
                ps.close();
                con.close();

            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }



    }*/
}
