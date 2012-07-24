package com.web.view;

import java.io.File;
import java.util.ArrayList;

public class FileDisplay {

	
	ArrayList<File> files;
	
	JDBCConnect con;
	
	public FileDisplay(){
		
		files = new ArrayList<File>();
		
		con = JDBCConnect.getObject("root", "", "mytestdb");
		
	}
	
	
	public ArrayList<File> getFiles(){
		return con.getFiles();
	}
	
	
}
