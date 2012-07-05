package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCookie extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			Cookie[] cookies = req.getCookies();
			
			for (Cookie cookie : cookies){
				if(cookie.getName().equals("thecolor")){
					String color = cookie.getValue();
					out.println("<br> You like " + color);
					break;
				}
			}
	}

}
