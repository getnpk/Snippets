package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		for (String s : brands){
			out.println("<br> Try this :" + s);
		}
	}

	
}
