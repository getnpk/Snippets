package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.User;
import view.JDBCConnect;

public class Register extends HttpServlet{

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	private String db_username;
	private String db_password;
	private String db_database;
	
	private JDBCConnect connect;
	
	private User user;
	private static Logger logger = Logger.getLogger(Register.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		
		db_username = servletContext.getInitParameter("db_username");
		db_password = servletContext.getInitParameter("db_password");
		db_database = servletContext.getInitParameter("db_database");
		
		logger.debug(db_database);
		user = new User();
		
		connect = JDBCConnect.getObject(db_username, db_password, db_database);
		
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><title>Welcome</title><body>");
		out.println("<h2> Registration form</h2>");
		
		out.println("<FORM METHOD=POST ACTION=\"Register\">");

		out.println("Firstname: <INPUT TYPE=TEXT NAME=firstname SIZE=20><BR>");
		out.println("Lastname: <INPUT TYPE=TEXT NAME=lastname SIZE=20><BR>");
		out.println("Username: <INPUT TYPE=TEXT NAME=username SIZE=20><BR>");
		out.println("Password: <INPUT TYPE=PASSWORD NAME=password SIZE=20><BR>");

		out.println("<P><INPUT TYPE=SUBMIT>");
		
		out.println("</body>></html>");

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
	
		
		//connect.setAll(user);
		
		try{
			connect.setAll(user);
			
		}catch(Exception e){
			out.println("<br>Failed to write to db");
		}finally{
			
		}
		
		out.println("<br>Successfully registerd");
		
		out.println("<br>Username " + user.getUsername());
		out.println("<br>Firstname " + user.getFirstname());
		
		out.println("<P><a href=\"Login\">Login now/a>");
		
}

}
