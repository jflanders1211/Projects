package Project;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class TicTacToe extends Game{

	private int grid[][] = new int[3][3];
	int turn = (int) Math.floor(Math.random() * 2);
	private int row;
	private int col;
	int hasWon = -1;
	private DropShadow oEffect;
	private DropShadow xEffect;
	private DropShadow blankEffect;
	private Scene menu;

	public TicTacToe(Player player1, Player player2)
	{
		super(player1, player2);
	}

	private void fillGrid(final GridPane gridpane) {

		//Instantiate Stack
		StackPane stack;
		gridpane.getChildren().clear();

		//Iterate over all
		int w = 0;
		int h = 0;
		for (row = 0; row < 5; row++) {

			for (col = 0; col < 5; col++) {

				stack = new StackPane();

				//Filling in the grid
				if (row % 2 == 1) {
					h = 20;
					if (col % 2 == 1) {
						w = 20;
					} else {
						w = 150;
					}
				} else {
					if (col % 2 == 1) {
						h = 150;
						w = 20;
					}
				}
				if (row % 2 == 1 || col % 2 == 1) {
					//Building part of the grid
					Rectangle rect = new Rectangle(w,h);
					rect.setFill(Color.BLACK);
					rect.setStrokeType(StrokeType.OUTSIDE);
					rect.setStroke(Color.TRANSPARENT);
					rect.setOpacity(.8);
					stack.getChildren().add(rect);
				}

				//Adding in the pieces
				if (row % 2 == 0 && col % 2 == 0) {

					//Set the shape of oS
					Circle circ = new Circle(50);
					Circle circ2 = new Circle(35);
					final Shape oS = Path.subtract(circ,circ2);
					oS.setFill(Color.BLUE);
					oS.setStroke(Color.BLUE);
					oS.setOpacity(0);
					oS.setEffect(oEffect);

					//Set the shape of xS
					final Shape xS = new Rectangle(100,100);
					xS.setFill(Color.RED);
					xS.setStroke(Color.RED);
					xS.setOpacity(0);
					xS.setEffect(xEffect);

					//Set the shape of trigger
					final Shape trigger = new Rectangle(150,150);
					trigger.setFill(Color.TRANSPARENT);
					trigger.setStroke(Color.TRANSPARENT);

					//Making sure nothing wacky happens with row and col
					final int colu = col/2;
					final int rowu = row/2;

					//Setting behavior
					trigger.setOnMouseEntered(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if (grid[colu][rowu] == 0 && hasWon < 0) {
								if (turn == 0) {
									oS.setOpacity(0.4);
								} else {
									xS.setOpacity(0.4);
								}
							}
						}
					});
					trigger.setOnMouseExited(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if (grid[colu][rowu] == 0 && hasWon < 0) {
								if (turn == 0) {
									oS.setOpacity(0);
								} else {
									xS.setOpacity(0);
								}
							}
						}
					});
					trigger.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent arg0) {
							if (grid[colu][rowu] == 0 && hasWon < 0) {
								int[] temp = {colu, rowu};
								if (turn == 0) {
									oS.setOpacity(.8);
									oS.setEffect(blankEffect);
								} else {
									xS.setOpacity(.8);
									xS.setEffect(blankEffect);
								}
								takeTurn(temp);
							}
						}
					});

					stack.getChildren().addAll(oS, xS, trigger);
				}
				gridpane.add(stack, col, row);
			}
		}

	}

	@Override
	public void play(Stage primaryStage) {
		// TODO Auto-generated method stub
		//Save the menu scene
		menu = primaryStage.getScene();

		//Initial setup
		final BorderPane root = new BorderPane();
		final GridPane gridpane = new GridPane();
		primaryStage.setTitle("TicTacToe");
		primaryStage.setResizable(true);

		//Scene setup
		Scene scene = new Scene(root, 1280, 720, true);
		//scene.setFill(Color.BLACK);

		//Effect setup
		oEffect = new DropShadow();
		oEffect.setSpread(.2);
		oEffect.setRadius(25);
		oEffect.setColor(Color.BLUE);
		xEffect = new DropShadow();
		xEffect.setSpread(.2);
		xEffect.setRadius(25);
		xEffect.setColor(Color.RED);
		blankEffect = new DropShadow();
		blankEffect.setSpread(.1);
		blankEffect.setRadius(10);
		blankEffect.setColor(Color.gray(.4));

		//Gridpane Placement
		gridpane.setTranslateY(20);
		gridpane.setAlignment(Pos.CENTER);

		//Setting up rows and columns
		//Small ones are for asthetics
		gridpane.getColumnConstraints().addAll(new ColumnConstraints(150,150,Double.MAX_VALUE),
				new ColumnConstraints(20,20,Double.MAX_VALUE),
				new ColumnConstraints(150,150,Double.MAX_VALUE),
				new ColumnConstraints(20,20,Double.MAX_VALUE),
				new ColumnConstraints(150,150,Double.MAX_VALUE));
		gridpane.getRowConstraints().addAll(new RowConstraints(150,150,Double.MAX_VALUE),
				new RowConstraints(20,20,Double.MAX_VALUE),
				new RowConstraints(150,150,Double.MAX_VALUE),
				new RowConstraints(20,20,Double.MAX_VALUE),
				new RowConstraints(150,150,Double.MAX_VALUE));

		//Call method to get fingerpainting
		fillGrid(gridpane);

		//Place gridpane in root
		root.setCenter(gridpane);



		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
	}

	@Override
	public boolean isWin() {
		// TODO Auto-generated method stub
		boolean out = (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] != 0);
		out = out || (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] != 0);
		out = out || (grid[0][0] == grid[0][1] && grid[0][0] == grid[0][2] && grid[0][0] != 0);
		out = out || (grid[1][0] == grid[1][1] && grid[1][0] == grid[1][2] && grid[1][0] != 0);
		out = out || (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] && grid[2][0] != 0);
		out = out || (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] && grid[0][0] != 0);
		out = out || (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] && grid[0][1] != 0);
		out = out || (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] && grid[0][2] != 0);
		return out;
	}

	public boolean isDraw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void takeTurn(int[] input) {
		// TODO Auto-generated method stub
		if (grid[input[0]][input[1]] == 0) {
			grid[input[0]][input[1]] = turn + 1;
		}
		if (turn == 1) {
			turn = turn - 1;
		} else {
			turn++;
		}

		if (isWin()) {
			if (turn == 1) {
				hasWon = 1;
			} else {
				hasWon = 2;
			}
		} else if (isDraw()) {
			hasWon = 0;
		}
	}
}
