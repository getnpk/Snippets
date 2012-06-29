package server;

import java.net.*;
import java.io.*;

import logic.InfixToPostfix;

public class ConversionServer {

	public static void main(String args[]) throws IOException{
		
		ServerSocket serverSocket = null;
		String input;
		
		InfixToPostfix ip = new InfixToPostfix();
		
		try{
			serverSocket = new ServerSocket(6190);
			System.out.println("Server waiting at 6190");
		}catch(IOException e){
			System.err.println("Cannot connet to port");
			System.exit(1);
		}
		
		Socket clientSocket = null;
		
		try{
			clientSocket = serverSocket.accept();
			
		}catch(IOException e){
			System.err.println("Cannot accept from serverSocket!");
			System.exit(1);
		}
		
		
		try{
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while ((input = in.readLine()) != null){
				String processedValue = ip.getValue(input);
				out.println(processedValue);
				System.out.println("Server says: " + processedValue);
			}
		
			in.close();
			out.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		serverSocket.close();
		clientSocket.close();
		
		
		
	
	}
}
