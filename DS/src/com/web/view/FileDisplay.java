package com.web.view;

import java.io.File;

public class FileDisplay {

	
	public static void main(String args[]){
	File file = new File("/usr/local/tomcat7/webapps/DS/dump");
	System.out.println(file.isDirectory());
	
	for (File f : file.listFiles()){
		System.out.println(f.getName());
	}
	
	}
	
	public File[] getFiles(){
		File file = new File("/usr/local/tomcat7/webapps/DS/dump");
		return file.listFiles();
	}
	
	
}
