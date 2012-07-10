package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.Beer;
import com.example.model.BeerExpert;
import java.util.*;

public class BeerSelect extends HttpServlet{

	@Override	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		
		
		
		out.println("Beer Selection Advice<br>");
		
		String color = req.getParameter("color");
		
		// Sessions
		
		HttpSession session = req.getSession();
		Date created = new Date(session.getCreationTime());
		Date lastaccess = new Date(session.getLastAccessedTime());
		
		out.println("ID: " + session.getId());
		out.println("<br>");
		out.println("Creation time: " + created);
		out.println("<br>");
		out.println("Last access time: " + lastaccess);
		out.println("<br>");
		
		session.setAttribute("Present_color_"+color, color);
		
		if (session.isNew()){
			out.println("So this is your first time!<br>");
		}else{
			out.println("Hey man, welcome back!<br>");
		}
		
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()){
			String name = (String)e.nextElement();
			String value = session.getAttribute(name).toString();
			
			out.println("session store : " + name + " = " + value);
			out.println("<br>");
		}
		
		
		// COOKIE
		
		Cookie cookie = new Cookie("thecolor", color);
		cookie.setMaxAge(30*60);
		resp.addCookie(cookie);
		
		
		
		
		ArrayList<String> brands = new BeerExpert().getBrands(color);
		
		out.println("<br>Init Mail id: " + getServletConfig().getInitParameter("adminEmail"));
		
		out.println("<br>Context Mail id: " + getServletContext().getInitParameter("adminEmail"));
		
		/*
		Enumeration<String> e = getServletConfig().getInitParameterNames();
		while(e.hasMoreElements()){
			out.println("<br> param name= " + e.nextElement() + "<br>");
		}
		
		
		synchronized(getServletContext()){
			getServletContext().setAttribute("type", "cold");
		}
		*/
		
		for (String s : brands){
			out.println("<br> Try this :" + s);
		}
		
		// listener class usage
		Beer beer = (Beer) getServletContext().getAttribute("mybeer");
		out.println("<br> Your fav beer is " + beer.getBrand());
		
		/*
		RequestDispatcher view = req.getRequestDispatcher("cookieresult.jsp");
		view.forward(req, resp);
		*/
		
		Cookie[] cookies = req.getCookies();
		
		for (Cookie c : cookies){
			if(c.getName().equals("thecolor")){
				String acolor = c.getValue();
				out.println("<br> You like " + acolor);
				break;
			}
		}
		
	}

	
}
