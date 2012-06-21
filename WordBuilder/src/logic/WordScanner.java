package logic;

import java.util.*;
import core.BuildBoard;

public class WordScanner {

	char[][] board;
	ArrayList<String> thewordlist;
	Direction direction;
	
	public WordScanner(){
		thewordlist = new ArrayList<String>();
		board = new BuildBoard().getBoard();
	}
	
	
	public ArrayList<String> getWords(){
		
		
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				
				for (Direction d: direction.values()){
					
					System.out.println(board[i][j]);
					System.out.println("Do for direction " + d);
					
				}
				
			}
		}
		
		
		return thewordlist;
	}
}
