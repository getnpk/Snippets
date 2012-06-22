package logic;

import java.util.ArrayList;
import java.util.Map;

import core.BuildBoard;
import core.BuildWords;

// TODO RETURN PROPER ARRAYLIST

public class WordScannerMultiThread implements Runnable{

	
	char[][] board;
	Map<Character, ArrayList<String>> wordmap;
	ArrayList<String> thewordlist;
	
	char i, j;
	
	public WordScannerMultiThread(char i, char j){
		
		board = new BuildBoard().getBoard();
		wordmap = new BuildWords().getWordMap();
		thewordlist = new ArrayList<String>();
		this.i =i;
		this.j =j;
		
	}
	
	@Override
	public void run() {
	
		char letter;
		StringBuffer sbuffer;
		
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
							while (k > 0){
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
							while (k > 0){
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
