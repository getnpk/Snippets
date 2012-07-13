package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class JDBCConnect{

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	String username;
	String password;
	String db;
	
	Connection connection;
	Statement statement;

	private static JDBCConnect jdbcconnect;
	
	public static JDBCConnect getObject(String username, String password, String db){
		
		if (jdbcconnect == null)
			return new JDBCConnect(username, password, db);
		else
			return jdbcconnect;
	}
	
	
	private JDBCConnect(String username, String password, String db){
	
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


	// set all fields in table
	
	public void setAll(User user){
		String query = String.format("insert into users (firstname, lastname, username, password) values ('%s', '%s', '%s',sha('%s'))", user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword());
		
		System.out.println(query);
		
		try {
			
			statement.executeUpdate(query);
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}finally{
			//closeAll();
		}
		
	}
	
	private void closeAll() throws SQLException{
		try {
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
		
}
