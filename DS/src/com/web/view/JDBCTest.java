package com.web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.web.model.User;

import java.sql.Blob;
import java.util.Date;


public class JDBCTest {

	private static final String DataBase_URL = "jdbc:mysql://localhost/";
	public static void main(String[] args) throws IOException{
		
		long now = System.currentTimeMillis();
		long diff = 0;
		Connection connection;
		Statement statement;
		ResultSet resultset;
		
		String db="mytestdb";
		String username = "root";
		String password = "";
		Date date = null;
		
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
		
			statement = connection.createStatement();

			resultset = statement.executeQuery("select uploadtime from files where filename='stylesheet.css'");
			
			while(resultset.next()){
				date = resultset.getTimestamp("uploadtime");
				long then = date.getTime();
				
				diff = now - then;
				
			}
			
			} catch (SQLException e) {

			e.printStackTrace();
		}
		String d = date.toString().split(" ")[0];
		
		
		System.out.println("Days " + (diff/(1000*60*60*24)));
		System.out.println("Hours " + (diff/(1000*60*60)));
		System.out.println("Mins " + (diff/(1000*60)));
		System.out.println("Seconds " + (diff/1000) % 60);
		
		User u = new User();
		u.showClassDetails();
		
		String name = "nitin.p.kumar";
		//System.out.println(name.substring(0, name.lastIndexOf('.')));
		System.out.println(name.substring(5));
	}
}
