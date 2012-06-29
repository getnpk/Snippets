package core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BuildWords {

	//String[] wordlist = {"rct","dab","god","cat","dog","map","cap","crap","cjt"};
	
	DataIO dio;
	String[] wordlist;
	ArrayList<String> words;
	
	
	JDBCConnect job = new JDBCConnect();
	ResultSet resultset;
	
	Map<Character, ArrayList<String>> wordmap;
	
	public BuildWords(){
		
		// to check jdbc
		words = new ArrayList<String>();
		
		resultset = job.executeSQL("select word from worddump");
		try {
			while(resultset.next()){
				words.add(resultset.getString("word"));
			}
			
			wordlist = words.toArray(new String[words.size()]);
			System.out.println("Database wordlist: ");
			for (String s : wordlist)
				System.out.printf("%s ", s);
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// older 
		
		dio = new DataIO();
		
		wordlist = dio.thewordliststring;
		
		wordmap = new HashMap<Character, ArrayList<String>>();
		
		ArrayList<String> templist;
		
		for (String word : wordlist){
			
			char keychar = word.charAt(0);
			
			if (wordmap.containsKey(keychar))
				break;
			
			templist = new ArrayList<String>();
			
			for(int j=0; j< wordlist.length; j++){
			
				if ( keychar == wordlist[j].charAt(0)){
					templist.add(wordlist[j]);
				}
				
			wordmap.put(keychar, templist);
			
			}
		}
	}
	
	public Map<Character, ArrayList<String>> getWordMap(){
		return wordmap;
	}
	
}
