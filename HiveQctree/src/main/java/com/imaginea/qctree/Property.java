package com.imaginea.qctree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public final class Property {

	private static Properties prop;
	
	private static final String configFile = "config.properties";
	
	static { 
		
		prop = new Properties();
		try {
			prop.load(new FileInputStream(configFile));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	private Property(){
		
	}
	
	public static final String baseTableName = prop.getProperty("base_table_name");
	
	public static final String QCTableName = "qc_" + prop.getProperty("base_table_name");
	
	// Hive Properties
	public static final String wareHousePath = prop.getProperty("warehouse_path");
	
	public static final String hiveUsername = prop.getProperty("hive_username");
	
	public static final String hivePassword = prop.getProperty("hive_password");
	
	public static final String hivePort = prop.getProperty("hive_port");
	
	public static final String hiveserverPort = prop.getProperty("hive_server_port");
	
	public static final String hiveClientIP = prop.getProperty("hive_client_ip");
	
	public static final String hiveDB = prop.getProperty("hive_db");
	
	public static final String hiveDriverName = prop.getProperty("hive_driver_name");
	
	public static final String fileSeperator = prop.getProperty("file_seperator");
	
	//QCTree
	
	public static final String latticeFilename = prop.getProperty("lattice_filename");
	
	public static final String qcTreeFilename = prop.getProperty("qctree_filename");
	
	public static String printProperties(){
		Enumeration<Object> e = prop.keys();
		StringBuilder sb = new StringBuilder();
		while(e.hasMoreElements()){
			String key = (String) e.nextElement();
			sb.append(key + " : " + prop.getProperty(key));
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
