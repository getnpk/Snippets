package com.web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.web.model.DBFile;
import com.web.model.User;

public class JDBCConnect{

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	
	private static Logger logger = Logger.getLogger(JDBCConnect.class);
	
	String username;
	String password;
	String db;
	
	private DataSource datasource;
	private Connection connection;
	private Statement statement;
	private ResultSet resultset;

	private static JDBCConnect jdbcconnect;
	
	private PreparedStatement setAllStatement = null;
	private PreparedStatement passStatement = null;
	private PreparedStatement getUserDetailsStatement = null;
	private PreparedStatement loadStatement = null;
	private PreparedStatement getFileStatement = null;
	private PreparedStatement getFilesStatement = null;
	
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
				Context context = new InitialContext();
				Context envContext = (Context) context.lookup("java:comp/env");
				datasource =  (DataSource)envContext.lookup("jdbc/testdb");
			} catch (NamingException e1) {
				e1.printStackTrace();
			}
			
			connection = datasource.getConnection();
			statement = connection.createStatement();
			
			/*
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
			*/
			
			
			// Prepared Statements
			getUserDetailsStatement = connection.prepareStatement("select * from users where username = ?");
			
			setAllStatement = connection.prepareStatement("insert into users (firstname, lastname, username, password) values (?,?,?,sha(?))");
			
			passStatement = connection.prepareStatement("select username , password from users where username = ? and password = sha(?)");
			
			loadStatement = connection.prepareStatement("insert into files(filename, filetype, filesize, " +
				"filelocation, user_requested, file ) values(?,?,?,?,?,?)");
		
			getFileStatement = connection.prepareStatement("select file from files where filename = ?");
			
			getFilesStatement = connection.prepareStatement("select filename, filetype, filesize, user_requested from files where filename like ?");
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	
	public ArrayList<DBFile> getFiles(String filename){
		
		ArrayList<DBFile> files = new ArrayList<DBFile>();
		
		if (filename == null ){
			filename = "%";
		}else{
			filename = "%" + filename + "%";
		}
		
		try {
			
			getFilesStatement.setString(1, filename.toLowerCase());
		
			resultset = getFilesStatement.executeQuery();
			
			while (resultset.next()){
				DBFile dbfile = new DBFile();
				dbfile.setFilename(resultset.getString("filename"));
				dbfile.setFilesize(resultset.getString("filesize"));
				dbfile.setFiletype(resultset.getString("filetype"));
				dbfile.setUserRequested(resultset.getString("user_requested"));
				
				files.add(dbfile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return files;
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
	
	
	
	public Boolean load(File file){
		
		
		Boolean isOkay = false;
		int result;
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			System.out.println("file not found ehre!!!!!!!");
			e1.printStackTrace();
			isOkay = false;
		}
		
		try {
		
			loadStatement.setString(1, file.getName());
			loadStatement.setString(2, file.getName().split("\\.")[file.getName().split("\\.").length - 1]);
			loadStatement.setFloat(3, (file.length()/1024));
			loadStatement.setString(4, file.getAbsolutePath());
			loadStatement.setString(5, "default");
			loadStatement.setBinaryStream(6, fis, (int)file.length());
			
			logger.info(loadStatement);
			
			result = loadStatement.executeUpdate();
			
			isOkay = true;
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			isOkay = false;
			
		}finally{
			 
			try {
				fis.close();
			} catch (IOException e) {
				 
				e.printStackTrace();
			} 
			
		}
		
		return isOkay;
		
	}
	
	
	public Blob getFile(String filename){
		
		int result;
		Blob blob = null;
		
		try {
			
			getFileStatement.setString(1, filename);
			logger.info(loadStatement);
			resultset = getFileStatement.executeQuery();
			resultset.next();
			blob = resultset.getBlob("file");
			
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		
		return blob;
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
