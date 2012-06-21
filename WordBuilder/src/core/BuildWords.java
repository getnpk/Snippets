package core;

import java.util.*;

public class BuildWords {

	String[] wordlist = {"cat","dog","map","cap","abopt","crap"};
	
	Map<Character, ArrayList<String>> wordmap;
	
	public BuildWords(){
		
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
