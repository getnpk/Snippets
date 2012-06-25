package logic;

import java.util.*;

import core.BuildBoard;
import core.BuildWords;

public class WordScanner {

	char[][] board;
	ArrayList<String> thewordlist;
	Direction direction;
	
	Map<Character, ArrayList<String>> wordmap;

	public WordScanner(){
		
		thewordlist = new ArrayList<String>();
		board = new BuildBoard().getBoard();
	
		wordmap = new BuildWords().getWordMap();
	}
	
	
	public ArrayList<String> getWords(){
		
		//new BuildBoard().printBoard();
		
		char letter;
		StringBuffer sbuffer;
		
		for (int i = 0; i< board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				
				letter = board[i][j];
				
				
				
				for (Direction d: Direction.values()){
					
					// ignore looking if not on found
					if (!(wordmap.containsKey(letter)))
						break;
					
					int k;
					
					switch (d){
						case NORTH:
							sbuffer = new StringBuffer();
							sbuffer.append(letter);
							k = i-1;
							while (k >= 0){
								sbuffer.append(board[k][j]);
								if (wordmap.get(letter).contains(sbuffer.toString().trim()))
									thewordlist.add(sbuffer.toString().trim());
								k--;
							}
		
							break;
					
						case SOUTH:
							sbuffer = new StringBuffer();
							sbuffer.append(letter);
							k = i+1;
							while (k < board[i].length){
								sbuffer.append(board[k][j]);
								if (wordmap.get(letter).contains(sbuffer.toString().trim()))
									thewordlist.add(sbuffer.toString().trim());
								k++;
							}
							break;
						
						case EAST:
							
							sbuffer = new StringBuffer();
							sbuffer.append(letter);
							k = j+1;
							while (k < board[i].length){
								sbuffer.append(board[i][k]);
								if (wordmap.get(letter).contains(sbuffer.toString().trim()))
									thewordlist.add(sbuffer.toString().trim());
								k++;
							}
							break;
						
						case WEST:
							sbuffer = new StringBuffer();
							sbuffer.append(letter);
							k = j-1;
							while (k >= 0){
								sbuffer.append(board[i][k]);
								if (wordmap.get(letter).contains(sbuffer.toString().trim()))
									thewordlist.add(sbuffer.toString().trim());
								k--;
							}
		
							break;
					
					}
						
					
				}
				
			}
		}
		
		
		return thewordlist;
	}
}
