package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import view.JDBCConnect;

public class Home extends HttpServlet{


	private String db_username;
	private String db_password;
	private String db_database;
	
	private User user;
	private String username;
	
	private JDBCConnect connect;
	
	private Boolean flag;
	
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext servletContext = config.getServletContext();
		
		db_username = servletContext.getInitParameter("db_username");
		db_password = servletContext.getInitParameter("db_password");
		db_database = servletContext.getInitParameter("db_database");
				
		connect = JDBCConnect.getObject(db_username, db_password , db_database);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			username = req.getParameter("username");
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			user = connect.getUserDetails(username);
			
			out.println("<html><title>Welcome </title><body>");
			
			
			out.println("<h2> Hello there! ");
			out.print(user.getFirstname().toUpperCase());
			out.println("</h2>");
			
			out.print("<br>Username: ");out.println(user.getUsername());
			out.print("<br>Lastname: ");out.println(user.getLastname());
			
			out.println("</body>></html>");

			
	}

}
