package server;

import java.net.*;
import java.io.*;


public class ConversionServerMulti{

	
	public static void main(String args[]) throws IOException{
		
		ServerSocket serverSocket = null;
		Boolean listening = true;
		
		try{
			serverSocket = new ServerSocket(6190);
			System.out.println("Server waiting at 6190");
		}catch(IOException e){
			System.err.println("Cannot connet to port");
			System.exit(1);
		}
		
		while(listening)
			new ConversionServerMutliThread(serverSocket.accept()).start();
		
		serverSocket.close();
	
	}
}
