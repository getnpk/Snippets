package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DataIO {

	File file;
	File filetowrite;
	
	BufferedReader reader;
	PrintWriter writer;
	
	String thegridsize;
	String thewordstring;
	String[] thewordliststring;
	
	ArrayList<Character> clist;
	
	public DataIO(){
		try{
		file = new File("data"+File.separator+"inputdata.txt");
		
		readFile();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public int getGridSize(){
		return Integer.parseInt(thegridsize);
	}
	
	public ArrayList<Character> getclist(){
		return clist;
	}
	
	public String[] thewordlistString(){
		return thewordliststring;
	}
	
	public void writeToFile(String theline){
		filetowrite = new File("data"+ File.separator + "yourwords.txt");
		try {
			writer = new PrintWriter(filetowrite);
			writer.println(theline);
			writer.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
	
	public void readFile(){
		try {
			reader = new BufferedReader (new FileReader (file));
			thegridsize = reader.readLine();
			thewordstring = reader.readLine();
			thewordliststring = reader.readLine().split(",");
			
			clist = new ArrayList<Character>();
			
			for (char c : thewordstring.toCharArray()){
				clist.add(c);
			}
			
			
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}
