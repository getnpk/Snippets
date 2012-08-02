package com.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionCheckFilter implements Filter{

	protected FilterConfig config;
	private ServletContext context;
	private String filterName;
	private String thepath;
	private String pathToBeIgnored;
	
	private ArrayList<String> excludefiles;
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		excludefiles = new ArrayList<String>();
		
		this.config = config;
		context = config.getServletContext();
		filterName = config.getFilterName();
		pathToBeIgnored = config.getInitParameter("pathsToBeIgnored");
	
		/*
		Enumeration<String> en = config.getInitParameterNames();
		if(en.hasMoreElements()){
			excludefiles.add(config.getInitParameter(en.nextElement()));
		}
		*/
		
		if (pathToBeIgnored.indexOf(',') > 0){
			for (String s : pathToBeIgnored.split(",")){
				excludefiles.add(s);
			}
		}else{
			excludefiles.add(pathToBeIgnored);
		}
		
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);
		
		thepath= req.getRequestURI();
		
		context.log("Exclude locations are: "+ excludefiles.toString());
		
		if (excludefiles.contains(thepath)){
			chain.doFilter(request, response);
			context.log(filterName + " ignoring path " + thepath);

		}else{
		
		if (session.getAttribute("username") == null){
		
			context.log(filterName + " the path " + thepath + " forwarding to index.jsp from " + req.getRemoteHost());
			
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			view.forward(request, response);
			
		}else{
			
			chain.doFilter(request, response);

			
		}
		
		}
	}

	
}
