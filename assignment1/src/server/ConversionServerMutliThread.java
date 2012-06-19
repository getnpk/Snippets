package server;

import java.io.*;
import java.net.*;

import logic.InfixToPostfix;

public class ConversionServerMutliThread extends Thread{

	Socket socket = null;
	
	public ConversionServerMutliThread(Socket socket){
		super("the server thread");
		this.socket = socket;
	}
	
	public void run(){

		String input;
		InfixToPostfix ip = new InfixToPostfix();
		
		try{
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while ((input = in.readLine()) != null){
				String processedValue = ip.getValue(input);
				out.println(processedValue);
				System.out.println("Server says: " + processedValue);
			}
		
			in.close();
			out.close();
			socket.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
}
