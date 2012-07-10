package com.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.example.model.Beer;

public class BeerContextListener implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	
	public void contextInitialized(ServletContextEvent event) {

		ServletContext sc = event.getServletContext();
		String beerBrand = sc.getInitParameter("brand");
		
		Beer b = new Beer(beerBrand);
		
		sc.setAttribute("mybeer", b);
		
		
	}

}
