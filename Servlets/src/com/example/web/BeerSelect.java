package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		ArrayList<String> brands = new BeerExpert().getBrands(color);
		
		out.println("<br>Init Mail id: " + getServletConfig().getInitParameter("adminEmail"));
		
		out.println("<br>Context Mail id: " + getServletContext().getInitParameter("adminEmail"));
		
		/*
		Enumeration<String> e = getServletConfig().getInitParameterNames();
		while(e.hasMoreElements()){
			out.println("<br> param name= " + e.nextElement() + "<br>");
		}
		*/
		
		for (String s : brands){
			out.println("<br> Try this :" + s);
		}
		
		// listener class usage
		Beer beer = (Beer) getServletContext().getAttribute("mybeer");
		out.println("<br> Your fav beer is " + beer.getBrand());
		
	}

	
}
