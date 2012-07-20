package com.web.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;

import com.web.model.User;

public class JDBCConnect{

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	private static Logger logger = Logger.getLogger(JDBCConnect.class.getName());
	
	String username;
	String password;
	String db;
	
	Connection connection;
	Connection connection_two;
	Statement statement;
	ResultSet resultset;

	private static JDBCConnect jdbcconnect;
	
	private PreparedStatement setAllStatement = null;
	private PreparedStatement passStatement = null;
	private PreparedStatement getUserDetailsStatement = null;
	
	public static JDBCConnect getObject(String username, String password, String db){
		
		if (jdbcconnect == null)
			return new JDBCConnect(username, password, db);
		else
			return jdbcconnect;
	}
	
	
	private JDBCConnect(String username, String password, String db){
		
		
		logger.debug(username);
		logger.debug(password);
		this.username = username;
		this.password = password;
		this.db = db;
		
		try {
			try {
				Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(DataBase_URL+db, username, password);
			logger.debug(" established connection "+connection.getAutoCommit());
			statement = connection.createStatement();
			logger.debug(" created statement "+statement);
			
			// Prepared Statements
			getUserDetailsStatement = connection.prepareStatement("select * from users where username = ?");
			
			setAllStatement = connection.prepareStatement("insert into users (firstname, lastname, username, password) values (?,?,?,sha(?))");
			
			passStatement = connection.prepareStatement("select username , password from users where username = ? and password = sha(?)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}


	// set all fields in table
	
	public void setAll(User user){
		//String query = String.format("insert into users (firstname, lastname, username, password) " +
		//		"values ('%s', '%s', '%s',sha('%s'))", user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword());
		
		int result;
		
		try {
			
			setAllStatement.setString(1, user.getFirstname());
			setAllStatement.setString(2, user.getLastname());
			setAllStatement.setString(3, user.getUsername());
			setAllStatement.setString(4, user.getPassword());
			
			logger.info(setAllStatement);
			
			result = setAllStatement.executeUpdate();
			
			//statement.executeUpdate(query);
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}finally{
			//closeAll();
		}
		
	}
	
	public Boolean pass(User user){
		
		String username, password;
		Boolean flag = false;
		
		//String query = String.format("select username, password from users where " +
		//		"username = '%s' and password = sha('%s')", user.getUsername(), user.getPassword());
		
		try{
			//resultset = statement.executeQuery(query);
			
			passStatement.setString(1, user.getUsername());
			passStatement.setString(2, user.getPassword());
			
			System.out.println(passStatement.toString());
			resultset = passStatement.executeQuery();
			
			while (resultset.next()){
					username = resultset.getString("username");
					password = resultset.getString("password");
					
					logger.info("Username " + username + " Password " + password);
					
					flag = true;
					break;
					
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	
	if(flag)
			return true;
		else
			return false;
		
	}
	
	
	
	public User getUserDetails(String username){
		
		User user = new User();
		
		//String query = String.format("SELECT * from users where username = '%s'", username);
		
		try {
			getUserDetailsStatement.setString(1, username);
			resultset = getUserDetailsStatement.executeQuery();
			
			while (resultset.next()){
			
				user.setUsername(resultset.getString("username"));
				user.setPassword(resultset.getString("password"));
				user.setFirstname(resultset.getString("firstname"));
				user.setLastname(resultset.getString("lastname"));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	private void closeAll(){
		try {
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
		
}
