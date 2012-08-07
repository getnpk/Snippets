package com.web.customtags;

import java.io.IOException;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.web.view.JDBCConnect;

public class LoadDateTagHandler extends SimpleTagSupport{

	private String filename;
	private JDBCConnect con;
	
	public LoadDateTagHandler(){
		con = JDBCConnect.getObject("", "", "");
	}
	
	public void doTag() {
		
		try {
			getJspContext().getOut().write(getLoadDate());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFilename(String filename){
		this.filename = filename;
	}
	
	private String getLoadDate(){
		
		return con.getUploadDate(filename);
		
	}
}
