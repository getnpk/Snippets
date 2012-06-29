package server;

import java.io.*;
import java.net.*;

public class ConversionClient {

	public static void main (String[] args) throws IOException{
	
	
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try{
			clientSocket = new Socket("localhost",6190);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		}catch(Exception e){
			System.err.println("An exception occured at: " + e);
			System.exit(1);
		}
		
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		String inputvalue;
		
		System.out.println("An infix exp?");
		try {
			while ((inputvalue = input.readLine()) != null){
				
				out.println(inputvalue);
				System.out.println("Client says: " + inputvalue);
				System.out.println("Server says: " + in.readLine());
				
			}
		} catch (IOException e) {
			System.err.println("Error reading input");
			e.printStackTrace();
		}
		
		out.close();
		in.close();
		input.close();
		clientSocket.close();
		
	}
	
}
