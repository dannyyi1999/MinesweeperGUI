import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class P4_Yi_Danny_MinesweeperPane extends Group{
	
	private ImageView[][] cells;
	private double tileSize;
	private P4_Yi_Danny_MinesweeperModel model;
	private Image blank = new Image("file:images/blank.gif");
	
	public P4_Yi_Danny_MinesweeperPane(){
		this.model = null;
		this.cells = null;
		this.tileSize = blank.getHeight();
	}
	
	public void resetCells(){
		getChildren().remove(0, getChildren().size());
		cells = new ImageView[model.getNumRows()][model.getNumCols()];
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				if(!model.getBoard()[row][col].isVisible()){
					if(model.getBoard()[row][col].isFlagged()){
						cells[row][col] = new ImageView("file:images/bomb_flagged.gif");
					}else {
						cells[row][col] = new ImageView("file:images/blank.gif");
					}
				}else {
					if(model.getBoard()[row][col].isFlagged()){
						System.out.println("yey111");
						if(model.getBoard()[row][col].getValue() != -1){
							cells[row][col] = new ImageView("file:images/bomb_wrong.gif");
							System.out.println("yey");
						}else {
							cells[row][col] = new ImageView("file:images/bomb_flagged.gif");
						}
					}else if(model.getBoard()[row][col].getValue() == -1){
						cells[row][col] = new ImageView("file:images/bomb_revealed.gif");
					}else if(model.getBoard()[row][col].getValue() == 0){
						cells[row][col] = new ImageView("file:images/num_0.gif");
					}else if(model.getBoard()[row][col].getValue() == 1){
						cells[row][col] = new ImageView("file:images/num_1.gif");
					}else if(model.getBoard()[row][col].getValue() == 2){
						cells[row][col] = new ImageView("file:images/num_2.gif");
					}else if(model.getBoard()[row][col].getValue() == 3){
						cells[row][col] = new ImageView("file:images/num_3.gif");
					}else if(model.getBoard()[row][col].getValue() == 4){
						cells[row][col] = new ImageView("file:images/num_4.gif");
					}else if(model.getBoard()[row][col].getValue() == 5){
						cells[row][col] = new ImageView("file:images/num_5.gif");
					}else if(model.getBoard()[row][col].getValue() == 6){
						cells[row][col] = new ImageView("file:images/num_6.gif");
					}else if(model.getBoard()[row][col].getValue() == 7){
						cells[row][col] = new ImageView("file:images/num_7.gif");
					}else if(model.getBoard()[row][col].getValue() == 8){
						cells[row][col] = new ImageView("file:images/num_8.gif");
					}else if(model.getBoard()[row][col].getValue() == -2){
						cells[row][col] = new ImageView("file:images/bomb_death.gif");
					}
				}
				cells[row][col].setX(tileSize * col);
				cells[row][col].setY(tileSize * row);
				getChildren().add(cells[row][col]);
			}
		}
	}
	
	public void setBeginnerGame(){
		this.model = new P4_Yi_Danny_MinesweeperModel(8, 8, 10);
		model.setGameOver(false);
		resetCells();
	}
	
	public void setIntermediateGame(){
		this.model = new P4_Yi_Danny_MinesweeperModel(16, 16, 40);
		model.setGameOver(false);
		resetCells();
	}
	
	public void setExpertGame(){
		this.model = new P4_Yi_Danny_MinesweeperModel(16, 31, 99);
		model.setGameOver(false);
		resetCells();
	}
	
	public void setModel(P4_Yi_Danny_MinesweeperModel model){
		this.model = model;
		resetCells();
	}
	
	public P4_Yi_Danny_MinesweeperModel getModel(){
		return model;
	}
	
	public double getTileSize(){
		return tileSize;
	}
	
	public void setTileSize(double tileSize){
		this.tileSize = tileSize;
		resetCells();
	}
	
	public double xPosForCol(int col) {
		return col * tileSize;
	}
	
	public double yPosForRow(int row) {
		return row * tileSize;
	}
	
	public int colForXPos(double x) {
		return (int)(x / tileSize);
	}
	
	public int rowForYPos(double y) {
		return (int)(y / tileSize);
	}
	
}
