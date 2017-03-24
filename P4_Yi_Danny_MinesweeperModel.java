public class P4_Yi_Danny_MinesweeperModel implements P4_Yi_Danny_MSModel{
	
	MinePane[][] board;
	int numMines;
	boolean gameOver = false;
	int numRevealed = 0;
	int numFlags = 0;
	
	public P4_Yi_Danny_MinesweeperModel(int numRows, int numCols, int numMines){
		board = new MinePane[numRows][numCols];
		this.numMines = numMines;
		setGrid(numRows, numCols, numMines);
	}
	
	@Override
	public boolean isGameOver(){
		return gameOver;
	}
	
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	public void revealAllBombs(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(!board[i][j].isVisible() && board[i][j].getValue() == -1){
					board[i][j].setVisible(true);
				}
			}
		}
	}
	
	public MinePane[][] getBoard(){
		return board;
	}
	
	@Override
	public int getNumRevealed(){
		return numRevealed;
	}

	@Override
	public void setFlag(int row, int col) {
		if(board[row][col].isFlagged()){
			board[row][col].setFlagged(false);
			numMines++;
			numFlags--;
		}else {
			board[row][col].setFlagged(true);
			numMines--;
			numFlags++;
		}
	}
	
	@Override
	public int getNumFlags(){
		return numFlags;
	}

	@Override
	public void reveal(int row, int col) {
		if(row >= 0 && row < getNumRows() && col >= 0 && col < getNumCols() && !board[row][col].isVisible()
		&& !board[row][col].isFlagged()){
			board[row][col].setVisible(true);
			if(board[row][col].getValue() == -1){
				board[row][col].setValue(-2);
				revealAllBombs();
				gameOver = true;
			}else if(board[row][col].getValue() > 0 && board[row][col].getValue() < 9){
				numRevealed++;
			}else if(board[row][col].getValue() == 0){
				numRevealed++;
				reveal(row - 1, col - 1);
				reveal(row - 1, col);
				reveal(row - 1, col + 1);
				reveal(row, col - 1);
				reveal(row, col + 1);
				reveal(row + 1, col - 1);
				reveal(row + 1, col);
				reveal(row + 1, col + 1);
			}
		}
		if(numRevealed + numFlags + numMines == board.length * board[0].length){
			gameOver = true;
		}
	}

	@Override
	public int getNumRows() {
		return board.length;
	}

	@Override
	public int getNumCols() {
		return board[0].length;
	}

	@Override
	public void setGrid(int numRows, int numCols, int numMines) {
		for(int i = 0; i < numMines; i++){
			int randomRow = (int)(Math.random() * numRows);
			int randomCol = (int)(Math.random() * numCols);
			while(board[randomRow][randomCol] != null){
				randomRow = (int)(Math.random() * numRows);
				randomCol = (int)(Math.random() * numCols);
			}
			board[randomRow][randomCol] = new MinePane(-1, false);
		}
		for(int row = 0; row < getNumRows(); row++){
			for(int col = 0; col < getNumCols(); col++){
				if(board[row][col] == null){
					board[row][col] = new MinePane(getNumNearMines(row, col), false);
				}
			}
		}
	}

	@Override
	public int getNumNearMines(int row, int col) {
		int count = 0;
		for(int i = row - 1; i <= row + 1; i++){
			for(int j = col - 1; j <= col + 1; j++){
				if(i < 0 || i >= getNumRows() || j < 0 || j >= getNumCols()){
					continue;
				}else {
					if(board[i][j] != null && board[i][j].getValue() == -1){
						count++;
					}
				}
			}
		}
		return count;
	}

	@Override
	public int getNumMines() {
		return numMines;
	}

	@Override
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}

	@Override
	public void printBoard() {
		for(int i = 0; i < getNumCols(); i++){
			System.out.print(" " + i);
		}
		System.out.println();
		for(int row = 0; row < getNumRows(); row++){
			System.out.print(row);
			for(int col = 0; col < getNumCols(); col++){
				if(board[row][col].isVisible()){
					if(board[row][col].getValue() == -1){
						System.out.print("* ");
					}else {
						System.out.print(board[row][col].getValue() + " ");
					}
				}else {
					if(board[row][col].isFlagged()){
						System.out.print("f ");
					}else {
						System.out.print("- ");
					}
				}
			}
			System.out.println();
		}
	}

	@Override
	public void printStackedBoard() {
		for(int i = 0; i < getNumCols(); i++){
			System.out.print(" " + i);
		}
		System.out.println();
		for(int row = 0; row < getNumRows(); row++){
			System.out.print(row);
			for(int col = 0; col < getNumCols(); col++){

				if(board[row][col].getValue() == -1){
					System.out.print("* ");
				}else {
					System.out.print(board[row][col].getValue() + " ");
				}
			}
			System.out.println();
		}
	}
}

class MinePane {
	
	boolean visible;
	int value;
	boolean flagged;
	
	public MinePane(int value, boolean visible){
		this.value = value;
		this.visible = visible;
		this.flagged = false;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean isFlagged(){
		return flagged;
	}
	
	public void setFlagged(boolean flag){
		flagged = flag;
	}
}
