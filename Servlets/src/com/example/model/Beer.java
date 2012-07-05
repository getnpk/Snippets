package com.example.model;

import java.io.Serializable;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Beer implements HttpSessionBindingListener, HttpSessionAttributeListener, Serializable{

	private String brand;
	
	public Beer(String brand){
		this.brand = brand;
	}
	
	public String getBrand(){
		return brand;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		
		// attribute addition to a session
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		
		String name = arg0.getName();
		Object value = arg0.getValue();
		System.out.println("Attribute added "+ name + "wiht this awesome value " + value);
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
