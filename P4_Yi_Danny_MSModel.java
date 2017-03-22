
public interface P4_Yi_Danny_MSModel {
	public void setFlag(int row, int col);
	public void reveal(int row, int col);
	public int getNumRows();
	public int getNumCols();
	public void setGrid(int numRows, int numCols, int numMines);
	public int getNumNearMines(int row, int col);
	public int getNumMines();
	public void setNumMines(int numMines);
	public void printBoard();
	public boolean isGameOver();
	public int getNumFlags();
	public int getNumRevealed();
	public void printStackedBoard();
}
