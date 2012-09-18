package com.imaginea.qctree;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imaginea.qctree.hive.Hivejdbc;

public class BuildTree {

	private static final Log LOG = LogFactory.getLog(BuildTree.class);
	
	private static Boolean isRunning(int port){
		ServerSocket sc = null;
		Boolean running = false;
		
		try {
			sc = new ServerSocket(port);
			running = false;
		} catch (IOException e) {
			running = true;
		}finally{
			try {
				if (sc != null)
					sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return running;
	}
	
	
	public static void main (String[] args ){
		
		Boolean running = isRunning(Integer.parseInt(Property.HIVE_PORT));
		LOG.info("Hive Server running: " + running);
		if (running){
			
			Hivejdbc obj = Hivejdbc.getObject();
			if(obj.checkSource())
					obj.buildQCube();
			else
				LOG.info("Load files in new Source table.");
		}else{
			LOG.info("Exiting.");
		}
		
		
	}
}
