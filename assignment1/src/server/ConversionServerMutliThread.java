package server;

import java.io.*;
import java.net.*;

import logic.InfixToPostfix;
import logic.PostfixEvaluation;

public class ConversionServerMutliThread extends Thread{

	Socket socket = null;
	
	public ConversionServerMutliThread(Socket socket){
		super("the server thread");
		this.socket = socket;
	}
	
	public void run(){

		String input;
		InfixToPostfix ip = new InfixToPostfix();
		PostfixEvaluation pe = new PostfixEvaluation();
		
		
		try{
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while ((input = in.readLine()) != null){
				String processedValue = ip.getValue(input);
				int evalValue = pe.getValue(processedValue);
				
				//out.println(processedValue);
				System.out.println("Server says: " + processedValue);
				out.println(evalValue);
				System.out.println("Postfix evaluation: " + evalValue);
			}
		
			in.close();
			out.close();
			socket.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
}
