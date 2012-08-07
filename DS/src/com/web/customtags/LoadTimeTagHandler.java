package com.web.customtags;

import java.io.IOException;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.web.view.JDBCConnect;

public class LoadTimeTagHandler extends SimpleTagSupport{

	private String filename;
	private JDBCConnect con;
	private long now;
	
	private long seconds;
	private long minutes;
	private long hours;
	private long days;
	
	public LoadTimeTagHandler(){
	
			con = JDBCConnect.getObject("", "", "");
			now = System.currentTimeMillis();
	}
	
	public void doTag() {
		
		try {
			getJspContext().getOut().write(getUploadTime());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	private String getUploadTime(){
		
		long diff = now - con.getUploadTime(filename);
	
		/*
		seconds = diff/1000;
		minutes = seconds/60;
		hours = minutes/60;
		
		days = hours/24;
		
		if (seconds > 60 )
			seconds = seconds % 60;
		if (minutes > 60 )
			minutes = minutes % 60;
		if (hours > 24 )
			hours = hours % 24;
		if (days > 365)
			days = days % 365;	
		
		return String.format("Days %s Hours %s Mins %s Secs %s", days, hours, minutes, seconds);
		
		*/
		days = diff /(3600000 * 24);
		if (days > 365)
			days = days % 365;	
		
		return String.format("%s days", days);
	}
}
