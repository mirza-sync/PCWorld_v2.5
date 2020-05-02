package pcworld.connection;

import java.sql.*;
public class ConnectionManager {
	static Connection conn;
//	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
//	private static final String DB_CONNECTION ="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION ="jdbc:mysql://localhost:3306/pcworld?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			try {
				conn = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
				System.out.println("connected");
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return conn;
	}
}