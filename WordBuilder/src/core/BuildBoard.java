package core;

import java.util.ArrayList;

public class BuildBoard {

	public int GRIDSIZE = 5;
	
	char [][] board;
	
	//char [] letters = {'a','b','o', 'p','t','c','a','t','j','c','a','d','o','g','r','p','m','a','p','a','r','o','l','n','p'};
	
	ArrayList<Character> letters;
	
	DataIO dr = new DataIO();
	
	public BuildBoard(){
		
		letters = dr.getclist();
		GRIDSIZE = dr.getGridSize();
		board = new char[GRIDSIZE][GRIDSIZE];
		
		// populate grid
		for (int i =0; i < board.length; i++){
			for (int j =0; j< board[i].length; j++){
				board[i][j] = letters.get(i*GRIDSIZE + j);
			}
		}
	}
	
	public void printBoard(){
		
		for (int i =0; i < board.length; i++){
			for (int j =0; j< board[i].length; j++){
				System.out.printf("%c | ", board[i][j]);
			}
			System.out.println();
			for (int j =0; j< board[i].length; j++){
				System.out.printf("%s ", " --");
			}
			System.out.println();
		}
	}
	
	public char[][] getBoard(){
		return board;
	}
}
