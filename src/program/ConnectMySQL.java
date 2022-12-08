package program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
	public static Connection openConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/quanlysach";
			String user = "root";
			String password = "";
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Success");
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Ngu");
			e.printStackTrace();
		}
		return null;
		
	}
}
