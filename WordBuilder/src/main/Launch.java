package main;

import java.util.*;

import core.BuildBoard;
import core.BuildWords;
import logic.WordScanner;

public class Launch {

	public static void main(String[] args){
		
		BuildWords words = new BuildWords();
		Map<Character, ArrayList<String>> wordmap = words.getWordMap();

		for (char c: wordmap.keySet()){
			System.out.println(wordmap.get(c));
		}
		
		WordScanner ws = new WordScanner();
		ArrayList<String> list = ws.getWords();
	}
	
}
