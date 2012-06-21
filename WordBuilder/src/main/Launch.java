package main;

import java.util.*;

import logic.WordScanner;

import core.BuildBoard;
import core.BuildWords;

public class Launch {

	public static void main(String[] args){
		
		System.out.printf("%s%n","Your board: ");
		new BuildBoard().printBoard();
		
		/*
		Map<Character, ArrayList<String>> wordmap = new BuildWords().getWordMap();
		for (char c : wordmap.keySet()){
			System.out.println(wordmap.get(c));
		}
		*/
		
		System.out.printf("%n%s%n", "Words in table: ");
		ArrayList<String> thelist = new WordScanner().getWords();
		System.out.println(thelist);
	}
	
}
