package com.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class ReportFilter implements Filter{

	protected FilterConfig config;
	private ServletContext context;
	private String filterName;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		// comes out in localhost logs
		
		context.log("Remote user: " + req.getRemoteUser() + " : "+ req.getRemoteHost() + " tried to access " + req.getRequestURL() + 
				" on " + new Date() + " reported by " + filterName);
		
		/*if (req.getRemoteHost().equals("192.168.6.15")){
		
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("http://www.google.com");
			
		}else{
			chain.doFilter(request, response);
		}
		*/
		
		chain.doFilter(request, response);
		
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		context = config.getServletContext();
		filterName = config.getFilterName();
	
	}

}
