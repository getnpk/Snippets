package com.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.web.view.FileDisplay;
import com.web.view.JDBCConnect;
import com.web.model.*;

public class Download extends HttpServlet{

	private String db_username;
	private String db_password;
	private String db_database;

	private JDBCConnect connect;
	
	private static Logger logger = Logger.getLogger(Download.class);
	
	
	private String user;
	private String filename;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		
		db_username = servletContext.getInitParameter("db_username");
		db_password = servletContext.getInitParameter("db_password");
		db_database = servletContext.getInitParameter("db_database");
		
		logger.setLevel(Level.INFO);
		
		connect = JDBCConnect.getObject(db_username, db_password, db_database);
		logger.info("Download: " + connect);
	}

	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		user = req.getParameter("user");
		filename = req.getParameter("filename");
		
		ArrayList<DBFile> files = new FileDisplay().getFiles(filename, user);
		
		req.setAttribute("dbfiles", files);
		
		RequestDispatcher view = req.getRequestDispatcher("download.jsp");
		view.forward(req, resp);
		
	}




	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println(req.getContextPath());
		out.println("<br>");
		out.println(req.getScheme());
		out.println("<br>");
		out.println(req.getServerName());
		out.println("<br>");
		out.println(req.getRequestURL());
		out.println("<br>");
		
		out.println("<br>");
		
		
		ServletContext sc = getServletContext();
		
		StringBuffer sb = new StringBuffer();
		sb.append("/dump/");
		sb.append(filename);
		String location = sb.toString();
		
		InputStream is = sc.getResourceAsStream(location);
						
		int read = 0;
		byte[] bytes = new byte[1024];
		
		*
		*/
		
		Blob blob;
		byte[] b = null;
		
		String filename = requset.getParameter("filename");
		
		if (filename.indexOf("jpg") > 0) {
            response.setContentType("image/jpg");
        }else if (filename.indexOf("gif") > 0) {
            response.setContentType("image/gif");
        }else if(filename.indexOf("png") > 0){
        	response.setContentType("image/png");
        } else if (filename.indexOf("pdf") > 0) {
            response.setContentType("application/pdf");
        } else if (filename.indexOf("html") > 0) {
            response.setContentType("text/html");
        } else if (filename.indexOf("zip") > 0) {
            response.setContentType("application/zip");
        } else if (filename.indexOf("tar") > 0) {
            response.setContentType("application/tar");
        } else if (filename.indexOf("jar") > 0) {
            response.setContentType("application/jar");
        }
		
		response.setHeader("Content-Disposition","attachment;filename=" + filename);
		
		try {
			blob = connect.getFile(filename);
			b = blob.getBytes(1, (int)blob.length());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		OutputStream os = response.getOutputStream();
		os.write(b);
		
		
		os.flush();
		os.close();

	}

	
}
