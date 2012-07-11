package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnect {

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	String username;
	String password;
	String db;
	
	Connection connection;
	Statement statement;
	ResultSet resultset;

	public JDBCConnect(){
		this("root", "","mytestdb");
	}
	
	public JDBCConnect(String username, String password, String db){
		this.username = username;
		this.password = password;
		this.db = db;
		
		try {
			connection = DriverManager.getConnection(DataBase_URL+db, username, password);
			statement = connection.createStatement();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public ResultSet executeSQL (String query){
		
		try {
			resultset = statement.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultset;
	}
	
	public void closeAll(){
		try {
			resultset.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
