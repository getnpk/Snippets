package com.web.view;

import java.io.File;



public class Loader {

	private JDBCConnect con;
	
	public Loader(){
		 con = JDBCConnect.getObject("root", "", "mytestdb");
	}	
	
	
	public void load(String uploadername){
		
		File f = new File("/usr/local/tomcat7/webapps/DS/dump");
		File d = new File ("/usr/local/tomcat7/webapps/DS/dump/done");
		
		d.mkdirs();
		
		System.out.println(f.getAbsolutePath());
		
		if (f.exists()){
			
			for (File file : f.listFiles()){
				
				if (!file.isDirectory()){
					if (con.load(file, uploadername)){
						
						Boolean success = file.renameTo(new File(d, file.getName()));
						if (success)
							System.out.println("Loaded and moved " + file);
						
					}
				}
			}
			
		}

		
	}
	
	
	public static void main(String[] args){
		
		
	}
}
