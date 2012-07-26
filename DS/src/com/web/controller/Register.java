package com.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.web.model.*;
import com.web.view.*;

public class Register extends HttpServlet{

private String db_username;
private String db_password;
private String db_database;

private JDBCConnect connect;
private Boolean success;

private User user;
private static Logger logger = Logger.getLogger(Register.class);

@Override
public void init(ServletConfig config) throws ServletException {
	ServletContext servletContext = config.getServletContext();
	
	db_username = servletContext.getInitParameter("db_username");
	db_password = servletContext.getInitParameter("db_password");
	db_database = servletContext.getInitParameter("db_database");
	
	logger.setLevel(Level.INFO);
	
	user = new User();
	
	connect = JDBCConnect.getObject(db_username, db_password, db_database);
	logger.info("Register: " + connect);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

	Boolean userexists= false;
	
	user.setUsername(request.getParameter("rusername"));
	user.setPassword(request.getParameter("rpassword"));
	user.setFirstname(request.getParameter("rfirstname"));
	user.setLastname(request.getParameter("rlastname"));

	
	try{
		if (!userexists){
			connect.setAll(user);
			success = true;
			logger.info("wrote to db , setAll");
		}
	}catch(Exception e){
		logger.info("Failed to write to db");
	}finally{
	
	}
	
	if(success){
		HttpSession session = request.getSession(true);
		session.invalidate();
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}else{
		String error_message = "Error while registering!";
		request.setAttribute("register_error", error_message);
		RequestDispatcher view = request.getRequestDispatcher("register.jsp");
		view.forward(request, response);
		
	}

}

}