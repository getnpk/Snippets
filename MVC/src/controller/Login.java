package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import view.JDBCConnect;


public class Login extends HttpServlet{

	private String db_username;
	private String db_password;
	private String db_database;
	
	private User user;
	
	private JDBCConnect connect;
	
	private Boolean flag;
	
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext servletContext = config.getServletContext();
		
		db_username = servletContext.getInitParameter("db_username");
		db_password = servletContext.getInitParameter("db_password");
		db_database = servletContext.getInitParameter("db_database");
				
		connect = JDBCConnect.getObject(db_username, db_password , db_database);
		
		user = new User();
		flag = false;
	}
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><title>Welcome</title><body>");
		out.println("<h2> Hello there! </h2>");
		
		out.println("<FORM METHOD=POST ACTION=\"Login\">");

		out.println("Username: <INPUT TYPE=TEXT NAME=username SIZE=20><BR>");
		out.println("Password: <INPUT TYPE=PASSWORD NAME=password SIZE=20><BR>");

		out.println("<P><INPUT TYPE=SUBMIT>");
		
		out.println("<P><a href=\"Register\">Register</a>");

		out.println("</body>></html>");
		
	}



	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Boolean isOkay = false;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		isOkay = connect.pass(user);
		
		if(isOkay){

			RequestDispatcher view = request.getRequestDispatcher("Home");
			view.forward(request, response);


		}else{
			out.println("<br>Something is wrong! <br>");
			out.println("<P><a href=\"Login\">Try again?</a>");


		}	
		
		/*
		if (connect.pass(user)){
			RequestDispatcher view = request.getRequestDispatcher("Home");
			view.forward(request, response);
		}
		else{
			RequestDispatcher view = request.getRequestDispatcher("Login");
		    view.forward(request, response);
		    flag = true;
		}
		*/
		
	}

	
	
}
