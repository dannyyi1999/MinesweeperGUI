
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class P4_Yi_Danny_MinesweeperView extends Application{
	
	private P4_Yi_Danny_MinesweeperModel model;
	private P4_Yi_Danny_MinesweeperPane minePane;
	
	private Image one = new Image("file://images/num_1.gif");
	private Image two = new Image("file:images/num_2.gif");
	private Image three = new Image("file:images/num_3.gif");
	private Image four = new Image("file:images/num_4.gif");
	private Image five = new Image("file:images/num_5.gif");
	private Image six = new Image("file:images/num_6.gif");
	private Image seven = new Image("file:images/num_7.gif");
	private Image eight = new Image("file:images/num_8.gif");
	private Image zero = new Image("file:images/num_0.gif");
	private Image blank = new Image("file:images/blank.gif");
	private Image bombReveal = new Image("file:images/bomb_revealed.gif");
	private Image bombDeath = new Image("file:images/bomb_death.gif");
	private Image flag = new Image("file:images/bomb_flagged.gif");
	private Image wrongBomb = new Image("file:images/num_1.gify");
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		
		model = new P4_Yi_Danny_MinesweeperModel(8, 8, 3);
		minePane = new P4_Yi_Danny_MinesweeperPane();
		minePane.setModel(model);
		
		minePane.setOnMouseReleased(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				int row = minePane.rowForYPos(e.getY());
				int col = minePane.colForXPos(e.getX());
				if(row >= 0 && row < minePane.getModel().getNumRows() && col >= 0 && col < minePane.getModel().getNumCols()){
					if(e.getButton().equals(MouseButton.PRIMARY)){
						if(!model.getBoard()[row][col].isVisible()){
							model.reveal(row, col);
							minePane.setModel(model);
						}
					}else if(e.getButton().equals(MouseButton.SECONDARY)){
						if(!model.getBoard()[row][col].isVisible()){
							model.setFlag(row, col);
							minePane.setModel(model);
						}
					}
				}}});
		root.setCenter(minePane);
		
		MenuBar menBar = new MenuBar();
		Menu game = new Menu("Game");
		Menu help = new Menu("Help");
		MenuItem beginnerGame = new MenuItem("New Beginner Game");
		beginnerGame.setOnAction((i1) -> {
			minePane.setBeginnerGame();
			model = minePane.getModel();
			});
		MenuItem intermediateGame = new MenuItem("New Intermediate Game");
		intermediateGame.setOnAction((i1) -> {
			minePane.setIntermediateGame();
			model = minePane.getModel();
		});
		MenuItem expertGame = new MenuItem("New Expert Game");
		MenuItem exit = new MenuItem("Exit");
		MenuItem numberMines = new MenuItem("Set Number of Mines");
		MenuItem rules = new MenuItem("How to Play");
		MenuItem about = new MenuItem("About");
		game.getItems().addAll(beginnerGame, intermediateGame, expertGame, numberMines, exit);
		help.getItems().addAll(rules, about);
		menBar.getMenus().addAll(game, help);
		
		root.setTop(menBar);
		
		Label time = new Label("Time elapsed: 0");
		Label mines = new Label("Mines left: 0");
		
		VBox labels = new VBox();
		labels.getChildren().addAll(time, mines);
		root.setRight(labels);
		
		primaryStage.setTitle("Minesweeper");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
