package main;

import java.util.*;

import logic.WordScanner;
import logic.WordScannerTLauncher;

import core.BuildBoard;
import core.BuildWords;
import core.DataIO;

public class Launch {

	public static void main(String[] args){
		
		DataIO io = new DataIO();
		
		System.out.printf("%s%n","Your board: ");
		new BuildBoard().printBoard();
		
		/*
		Map<Character, ArrayList<String>> wordmap = new BuildWords().getWordMap();
		for (char c : wordmap.keySet()){
			System.out.println(wordmap.get(c));
		}
		*/
		
		/*
		 * This is the sequential version
		 */
		
		/*
		System.out.printf("%n%s%n", "Words in table: ");
		ArrayList<String> thelist = new WordScanner().getWords();
		System.out.println(thelist);
		*/
		
		/*
		 * Multithread need checking
		 */
		
		
		ArrayList<String> thelist = new WordScannerTLauncher().getWords();
		
		System.out.printf("%n%s%n", "Words in table: ");
		System.out.println(thelist);
		
		// write the same to a file.
		io.writeToFile(thelist.toString());
		
	}
	
}
