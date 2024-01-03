package q1;

public class Sudoku
{
	private int[][] board;

	public Sudoku()
	{
		board = new int[9][9];
	}

	public boolean tryAddingNumber(int row, int col, int digit){
		if (existInRow(digit, row))
		{
			return false;
		}
		if (existInCol(digit, col))
		{
			return false;
		}
		if (existInSquare(digit, row, col))
		{
			return false;
		}
		
		board[row][col] = digit;
		return true;
	}

	private boolean existInSquare(int digit, int row, int col){
		int squareRowIndex = row / 3;
		int squareColIndex = col / 3;
		for (int i = squareRowIndex * 3; i < squareRowIndex * 3 + 3; i++){
			for (int j = squareColIndex * 3; j < (squareColIndex + 1) * 3; j++){
				if (board[i][j] == digit){
					return true;
				}
			}
		}

		return false;
	}

	private boolean existInCol(int digit, int col){
		for (int i = 0; i < board.length; i++){
			if (board[i][col] == digit){
				return true;
			}
		}

		return false;
	}

	private boolean existInRow(int digit, int row){
		for (int i = 0; i < board.length; i++){
			if (board[row][i] == digit){
				return true;
			}
		}

		return false;
	}

	public void init(){
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board.length; j++){
				board[i][j] = 0;
			}
		}
	}
}

