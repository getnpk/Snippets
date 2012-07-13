package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.JDBCConnect;


public class Login extends HttpServlet{

	private String db_username;
	private String db_password;
	private String db_database;
	
	
	private JDBCConnect connect;
	
	public void init() throws ServletException {
		super.init();
		
		db_username = getServletContext().getInitParameter("db_username");
		db_password = "";
		db_database = getServletContext().getInitParameter("db_database");
		
		connect = JDBCConnect.getObject(db_username, db_password , db_database);
		
	}
	
	

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><title>Welcome</title><body>");
		out.println("<h2> Hello! </h2>");
		
		out.println("<FORM METHOD=POST ACTION=\"Home\">");

		out.println("Username: <INPUT TYPE=TEXT NAME=username SIZE=20><BR>");
		out.println("Password: <INPUT TYPE=PASSWORD NAME=password SIZE=20><BR>");

		out.println("<P><INPUT TYPE=SUBMIT>");
		
		out.println("<P><a href=\"Register\">Register</a>");

		out.println("</body>></html>");
		
	}



	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		
	
	}

	
}
