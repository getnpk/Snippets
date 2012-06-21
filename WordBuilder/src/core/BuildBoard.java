package core;

public class BuildBoard {

	public static final int GRIDSIZE = 5;
	
	char [][] board = new char[GRIDSIZE][GRIDSIZE];
	
	char [] letters = {'a','b','o', 'p','t','c','a','t','j','c','a','d','o','g','r','p','m','a','p','a','r','o','l','n','p'};
	
	public BuildBoard(){
		
		// populate grid
		for (int i =0; i < board.length; i++){
			for (int j =0; j< board[i].length; j++){
				board[i][j] = letters[i*GRIDSIZE + j];
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
