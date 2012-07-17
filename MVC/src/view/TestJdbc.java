package view;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

import model.User;

public class TestJdbc {

	
	public static void main(String[] args){
		
		Logger logger = Logger.getLogger(TestJdbc.class);
		logger.debug("Hello World");

		User user = new User();
		user.setFirstname("goo");
		user.setLastname("goo");
		user.setUsername("goo");
		user.setPassword("goo");
		
		JDBCConnect con = JDBCConnect.getObject("root", null , "mytestdb");
		
		//con.setAll(user);
		//System.out.println(user);
		//System.out.println(con.pass(user));
		
		System.out.println(con.getUserDetails("nitin"));
		

	}
}
