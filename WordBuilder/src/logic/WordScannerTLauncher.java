package logic;

import java.util.*;

import core.BuildBoard;

//TODO IMPLEMENT THREADS

public class WordScannerTLauncher {

	char[][] board;

	
	public WordScannerTLauncher(){
		
		board = new BuildBoard().getBoard();
		
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				new Thread(new WordScannerMultiThread(i,j)).start();
			}
		}
	}
	
	public ArrayList<String> getWords(){
	
		return WordScannerMultiThread.thewordlist;
		
	}
	
}

