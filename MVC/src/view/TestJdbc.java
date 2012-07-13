package view;

import model.User;

public class TestJdbc {

	
	public static void main(String[] args){
		
		
		User user = new User();
		user.setFirstname("sdf");
		user.setLastname("sdf");
		user.setUsername("skldfj");
		user.setPassword("sdfsf");
		
		JDBCConnect con = JDBCConnect.getObject("root", null , "mytestdb");
		con.setAll(user);
	}
}
