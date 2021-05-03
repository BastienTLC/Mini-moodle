package sample;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class DbConnection {

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:MiniMoodleBdd.db");
			System.out.println("SQLite DB connected");
		}catch(Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
