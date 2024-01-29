package Project;

import javafx.stage.Stage;

public abstract class Game {

	int hasWon = 0;
	Player players[] = new Player[2];
	
	Game(Player player1, Player player2) {
		this.players[0] = player1;
		this.players[1] = player2;
	}
	
	abstract void play(Stage primaryStage);
	
	abstract boolean isWin();
	
	abstract void takeTurn(int input[]);
}
