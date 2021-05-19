package sample.bdd;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbConnection {

	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\BastienP\\Desktop\\Mini-moodle\\MiniMoodleBdd.db");
			System.out.println("SQLite DB connected");
		}catch(Exception e) {
			System.out.println(e);
		}
		return connection;
	}
}
