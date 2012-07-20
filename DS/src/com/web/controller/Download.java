package com.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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
		*/
		
		String filename = req.getParameter("value");
		
		resp.setContentType("application/jar");
		ServletContext sc = getServletContext();
		
		StringBuffer sb = new StringBuffer();
		sb.append("/dump/");
		sb.append(filename);
		String location = sb.toString();
		
		InputStream is = sc.getResourceAsStream(location); 
		int read = 0;
		byte[] bytes = new byte[1024];
		
		OutputStream os = resp.getOutputStream();
		
		while((read = is.read(bytes)) != -1){
			os.write(bytes, 0, read);
		}
		
		os.flush();
		os.close();

	}

	
}
