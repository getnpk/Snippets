package server;

import java.io.*;
import java.net.*;

import logic.InfixToPostfix;
import logic.PostfixEvaluation;
import logic.ValidateInput;

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
				
				// assuming server is doing the infix string validation
				if (new ValidateInput().isOkay(input)){
				
					String processedValue = ip.getValue(input);
					int evalValue = pe.getValue(processedValue);
					//out.println(processedValue);
					//System.out.println("Server says: " + processedValue);
					out.println(evalValue);
					System.out.println("Postfix evaluation: " + evalValue);
				}else{
					out.println("Incorrect infix exp, use 0-9 , supported ops!");
				}
			}
		
			in.close();
			out.close();
			socket.close();

		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
}
