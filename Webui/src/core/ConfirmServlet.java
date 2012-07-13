package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmServlet extends HttpServlet{

	
	private String db_username;
	private String db_password;
	private String database;
	
	private JDBCConnect job;
	private ResultSet resultset;
	
	private String username;
	private String password;
	
	private HttpSession session;
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		db_username = getServletContext().getInitParameter("db_username");
		db_password = getServletContext().getInitParameter("db_password");
		database = getServletContext().getInitParameter("database");
		
		job = new JDBCConnect(db_username, db_password, database);
		System.out.println("inside init qqq");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	
		username = req.getParameter("username");
		password = req.getParameter("password");
		
		session = req.getSession(false);
		if(session == null){
			session = req.getSession(true);
		}
		session.setAttribute("username", username);
		
		
		out.println("<br>Username:" + db_username);
		out.println("<br>Password:" + db_password);
		out.println("<br>Database:" + database);
		
		String quest = String.format("select username, password from users where username='%s' and password='%s'", username, password);
		
		out.println("<br>" + quest);
		
		
		if (!(username.equals(password))){
			
			try{
				out.println("<br> Error in connection, denied Redirecting..");
				Thread.sleep(2000);
			}catch(Exception e){
				
			}
			
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, resp);
		}else{
			
			Cookie cookie = new Cookie("username", username);
			resp.addCookie(cookie);
			
			RequestDispatcher view = req.getRequestDispatcher("Display");
			view.forward(req, resp);
		}
		
		
		/*
		try{
			
		out.println("<br> the job object " + job);
		
		resultset = job.executeSQL(quest);
		
		
		if (!(resultset.next())){
			RequestDispatcher view = req.getRequestDispatcher("login.jsp");
			out.println("<br> Error in connection, denied");
			view.forward(req, resp);
		}else{
			RequestDispatcher view = req.getRequestDispatcher("display.jsp");
			view.forward(req, resp);
		}
			
		}catch(Exception e){
			out.println("<br> error in resultset");
			e.printStackTrace();
		}
		*/
	}

	
}
