package com.web.view;

import java.io.File;
import java.util.ArrayList;

import com.web.model.DBFile;

public class FileDisplay {

	
	ArrayList<File> files;
	
	JDBCConnect con;
	
	public FileDisplay(){
		
		files = new ArrayList<File>();
		con = JDBCConnect.getObject("root", "", "mytestdb");
	}
	
	
	public ArrayList<DBFile> getFiles(String filename, String user){
		return con.getFiles(filename, user);
	}
	
	
}
