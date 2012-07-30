package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.model.User;
import com.web.view.JDBCConnect;

public class Login extends HttpServlet{

	private JDBCConnect con;
	private User user;
	private String username;
	private String password;
	private HttpSession session;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		con = JDBCConnect.getObject("db_username", "db_password", "db_database");
		user = new User();
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		user.setUsername(username);
		user.setPassword(password);
		Boolean pass = con.pass(user);
		
		session = request.getSession(true);
		
		if (pass){
			
			session.setMaxInactiveInterval(20 * 60); // mins
			
			session.setAttribute( "username", username );
			
			RequestDispatcher view = request.getRequestDispatcher("download.jsp");
			view.forward(request, response);
		

		}else{
			
			String error_message = "Something went wrong!";
			session.setAttribute("error", error_message);
			
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		
		}

		
	}

	
}
