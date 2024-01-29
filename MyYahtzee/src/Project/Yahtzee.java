package Project;


import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.control.CheckBox;
import javafx.event.EventHandler;
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

public class Yahtzee extends Game {


    public Yahtzee(Player player1, Player player2)
    {
        super(player1, player2);
    }

    // Number of rolls per turn
    static int numOfRolls = 3;

    // Keeps track of whos turn it is
    int turn[] = new int[]{1};

    // keeping track of win counts for players
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int hasWon = -1;


    // Keeps track of players scores to see who won
    private int plr1Score = 0;
    private int plr2Score = 0;

    private Stage primaryStageCopy;
    private Scene menuScene;

    void play(Stage primaryStage)
    {
        BorderPane layout = new BorderPane();

        primaryStageCopy = primaryStage;
        menuScene = primaryStage.getScene();

        // Creating an array of type dice
        Die[] dice = new Die[5];

        // Creating 5 die objects
        Die dieObj1 = new Die();
        Die dieObj2 = new Die();
        Die dieObj3 = new Die();
        Die dieObj4 = new Die();
        Die dieObj5 = new Die();

        // Storing objects of die in array
        dice[0] = dieObj1;
        dice[1] = dieObj2;
        dice[2] = dieObj3;
        dice[3] = dieObj4;
        dice[4] = dieObj5;

        //----------------Main menu, info, exit, and forfeit-----------------//

        Button exitButton = new Button("Exit");
        exitButton.setMinWidth(20);
        Button mainScreenButton = new Button("Main Screen");
        mainScreenButton.setMinWidth(50);
        Button scorePoints = new Button("Score Points");
        scorePoints.setMinWidth(50);
        Button info = new Button("Info");
        info.setMinHeight(20);
        Text player1WinCount = new Text(players[0].name + "'s win count: " + player1Wins);
        Text player2WinCount = new Text(players[1].name + "'s win count: " + player2Wins);

        // Setting up the buttons to the top of the panel
        HBox topHbox = new HBox();
        topHbox.setSpacing(160);
        topHbox.getChildren().addAll(mainScreenButton, player1WinCount, scorePoints, player2WinCount, info, exitButton);
        layout.setTop(topHbox);

        //------------------Left side scorecard-------------------------//

        //------------------Upper Part of scorecard----------------------//

        // Showing Player name above scorecard
        Text name1 = new Text(players[0].name);
        GridPane.setHalignment(name1, HPos.CENTER);
        name1.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(name1,0,0);

        // Creating buttons for the upper part of the scorecard
        // Includes Ones - Sixes
        Button ace1 = new Button("Aces");
        ace1.setPrefSize(100, 30);

        Button two1 = new Button("Twos");
        two1.setPrefSize(100, 30);

        Button three1 = new Button("Threes");
        three1.setPrefSize(100, 30);

        Button four1 = new Button("Fours");
        four1.setPrefSize(100, 30);

        Button five1 = new Button("Fives");
        five1.setPrefSize(100, 30);

        Button six1 = new Button("Sixes");
        six1.setPrefSize(100, 30);

        // Creating textFields to correlate with every button
        // which will store in the scores for each category
        TextField acePts1 = new TextField();
        acePts1.setPrefSize(50, 30);

        TextField twoPts1 = new TextField();
        twoPts1.setPrefSize(50, 30);

        TextField threePts1 = new TextField();
        threePts1.setPrefSize(50, 30);

        TextField fourPts1 = new TextField();
        fourPts1.setPrefSize(50, 30);

        TextField fivePts1 = new TextField();
        fivePts1.setPrefSize(50, 30);

        TextField sixPts1 = new TextField();
        sixPts1.setPrefSize(50, 30);


        // Creating a bold Upper Total text, to match the Yahtzee scorecard
        Text upperTotal1 = new Text("Upper Total");
        GridPane.setHalignment(upperTotal1, HPos.CENTER);
        upperTotal1.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(upperTotal1,0,7);

        // Creating a textField for the upper score
        TextField upperTotalPts1 = new TextField("0");
        upperTotalPts1.setPrefSize(50, 30);
        GridPane.setConstraints(upperTotalPts1, 1, 7);

        // Creating a bold Bonus text, to match the Yahtzee scorecard
        Text bonus1 = new Text("Bonus");
        GridPane.setHalignment(bonus1, HPos.CENTER);
        bonus1.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(bonus1,0,8);

        // Creating a textField for the bonus score
        TextField bonusPts1 = new TextField("0");
        bonusPts1.setPrefSize(50, 30);
        GridPane.setConstraints(bonusPts1, 1, 8);

        // Align all the buttons in the upper part for the GridPane
        GridPane.setConstraints(ace1, 0, 1);
        GridPane.setConstraints(two1, 0, 2);
        GridPane.setConstraints(three1, 0, 3);
        GridPane.setConstraints(four1, 0, 4);
        GridPane.setConstraints(five1, 0, 5);
        GridPane.setConstraints(six1, 0, 6);

        // Align all the textFields in the upper part for GridPane
        GridPane.setConstraints(acePts1, 1, 1);
        GridPane.setConstraints(twoPts1, 1, 2);
        GridPane.setConstraints(threePts1, 1, 3);
        GridPane.setConstraints(fourPts1, 1, 4);
        GridPane.setConstraints(fivePts1, 1, 5);
        GridPane.setConstraints(sixPts1, 1, 6);


        //------------------Lower Part of scorecard----------------------//


        // Creating all the buttons for the bottom part of the scorecard
        Button threeOfAKind1 = new Button("3 of a kind");
        threeOfAKind1.setPrefSize(100, 30);

        Button fourOfAKind1 = new Button("4 of a kind");
        fourOfAKind1.setPrefSize(100, 30);

        Button fullHouse1 = new Button("Full house");
        fullHouse1.setPrefSize(100, 30);

        Button smallStraight1 = new Button("Sm. straight");
        smallStraight1.setPrefSize(100, 30);

        Button largeStraight1 = new Button("Lg. straight");
        largeStraight1.setPrefSize(100, 30);

        Button yahtzee1 = new Button("Yahtzee");
        yahtzee1.setPrefSize(100, 30);

        Button chance1 = new Button("Chance");
        chance1.setPrefSize(100, 30);


        // Creating textFields to correlate with every button
        // which will store in the scores for each category
        TextField threeOfAKindPts1 = new TextField();
        threeOfAKindPts1.setPrefSize(50, 30);

        TextField fourOfAKindPts1 = new TextField();
        fourOfAKindPts1.setPrefSize(50, 30);

        TextField fullHousePts1 = new TextField();
        fullHousePts1.setPrefSize(50, 30);

        TextField smallStraightPts1 = new TextField();
        smallStraightPts1.setPrefSize(50, 30);

        TextField largeStraightPts1 = new TextField();
        largeStraightPts1.setPrefSize(50, 30);

        TextField yahtzeePts1 = new TextField();
        yahtzeePts1.setPrefSize(50, 30);

        TextField chancePts1 = new TextField();
        chancePts1.setPrefSize(50, 30);

        // Creating a bold Lower Total text, to match the Yahtzee scorecard
        Text lowerTotal1 = new Text("Lower Total");
        GridPane.setHalignment(lowerTotal1, HPos.CENTER);
        lowerTotal1.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(lowerTotal1,0,16);

        // Creating a textField for the lower score
        TextField lowerTotalPts1 = new TextField("0");
        lowerTotalPts1.setPrefSize(50, 30);
        GridPane.setConstraints(lowerTotalPts1, 1, 16);

        // Creating a bold Grand Total text, to match the Yahtzee scorecard
        Text grandTotal1 = new Text("Grand Total");
        GridPane.setHalignment(grandTotal1, HPos.CENTER);
        grandTotal1.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(grandTotal1,0,17);

        // Creating a textField for the grand score
        TextField grandTotalPts1 = new TextField("0");
        grandTotalPts1.setPrefSize(50, 30);
        GridPane.setConstraints(grandTotalPts1, 1, 17);

        // Align all the buttons in the lower part for the GridPane
        GridPane.setConstraints(threeOfAKind1, 0, 9);
        GridPane.setConstraints(fourOfAKind1, 0, 10);
        GridPane.setConstraints(fullHouse1, 0, 11);
        GridPane.setConstraints(smallStraight1, 0, 12);
        GridPane.setConstraints(largeStraight1, 0, 13);
        GridPane.setConstraints(yahtzee1, 0, 14);
        GridPane.setConstraints(chance1, 0, 15);

        // Align all the textFields in the lower part for GridPane
        GridPane.setConstraints(threeOfAKindPts1, 1, 9);
        GridPane.setConstraints(fourOfAKindPts1, 1, 10);
        GridPane.setConstraints(fullHousePts1, 1, 11);
        GridPane.setConstraints(smallStraightPts1, 1, 12);
        GridPane.setConstraints(largeStraightPts1, 1, 13);
        GridPane.setConstraints(yahtzeePts1, 1, 14);
        GridPane.setConstraints(chancePts1, 1, 15);

        //------------------Right side scorecard-------------------------//

        // Showing Player name above scorecard
        Text name2 = new Text(players[1].name);
        GridPane.setHalignment(name2, HPos.CENTER);
        name2.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(name1,0,0);

        // Creating buttons for the upper part of the scorecard
        // Includes Ones - Sixes
        Button ace2 = new Button("Aces");
        ace2.setPrefSize(100, 30);

        Button two2 = new Button("Twos");
        two2.setPrefSize(100, 30);

        Button three2 = new Button("Threes");
        three2.setPrefSize(100, 30);

        Button four2 = new Button("Fours");
        four2.setPrefSize(100, 30);

        Button five2 = new Button("Fives");
        five2.setPrefSize(100, 30);

        Button six2 = new Button("Sixes");
        six2.setPrefSize(100, 30);

        // Creating textFields to correlate with every button
        // which will store in the scores for each category
        TextField acePts2 = new TextField();
        acePts2.setPrefSize(50, 30);

        TextField twoPts2 = new TextField();
        twoPts2.setPrefSize(50, 30);

        TextField threePts2 = new TextField();
        threePts2.setPrefSize(50, 30);

        TextField fourPts2 = new TextField();
        fourPts2.setPrefSize(50, 30);

        TextField fivePts2 = new TextField();
        fivePts2.setPrefSize(50, 30);

        TextField sixPts2 = new TextField();
        sixPts2.setPrefSize(50, 30);


        // Creating a bold Upper Total text, to match the Yahtzee scorecard
        Text upperTotal2 = new Text("Upper Total");
        GridPane.setHalignment(upperTotal2, HPos.CENTER);
        upperTotal2.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(upperTotal2,0,7);

        // Creating a textField for the upper score
        TextField upperTotalPts2 = new TextField("0");
        upperTotalPts2.setPrefSize(50, 30);
        GridPane.setConstraints(upperTotalPts2, 1, 7);

        // Creating a bold Bonus text, to match the Yahtzee scorecard
        Text bonus2 = new Text("Bonus");
        GridPane.setHalignment(bonus2, HPos.CENTER);
        bonus2.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(bonus2,0,8);

        // Creating a textField for the bonus score
        TextField bonusPts2 = new TextField("0");
        bonusPts2.setPrefSize(50, 30);
        GridPane.setConstraints(bonusPts2, 1, 8);

        // Align all the buttons in the upper part for the GridPane
        GridPane.setConstraints(ace2, 0, 1);
        GridPane.setConstraints(two2, 0, 2);
        GridPane.setConstraints(three2, 0, 3);
        GridPane.setConstraints(four2, 0, 4);
        GridPane.setConstraints(five2, 0, 5);
        GridPane.setConstraints(six2, 0, 6);

        // Align all the textFields in the upper part for GridPane
        GridPane.setConstraints(acePts2, 1, 1);
        GridPane.setConstraints(twoPts2, 1, 2);
        GridPane.setConstraints(threePts2, 1, 3);
        GridPane.setConstraints(fourPts2, 1, 4);
        GridPane.setConstraints(fivePts2, 1, 5);
        GridPane.setConstraints(sixPts2, 1, 6);


        //------------------Lower Part of scorecard----------------------//


        // Creating all the buttons for the bottom part of the scorecard
        Button threeOfAKind2 = new Button("3 of a kind");
        threeOfAKind2.setPrefSize(100, 30);

        Button fourOfAKind2 = new Button("4 of a kind");
        fourOfAKind2.setPrefSize(100, 30);

        Button fullHouse2 = new Button("Full house");
        fullHouse2.setPrefSize(100, 30);

        Button smallStraight2 = new Button("Sm. straight");
        smallStraight2.setPrefSize(100, 30);

        Button largeStraight2 = new Button("Lg. straight");
        largeStraight2.setPrefSize(100, 30);

        Button yahtzee2 = new Button("Yahtzee");
        yahtzee2.setPrefSize(100, 30);

        Button chance2 = new Button("Chance");
        chance2.setPrefSize(100, 30);


        // Creating textFields to correlate with every button
        // which will store in the scores for each category
        TextField threeOfAKindPts2 = new TextField();
        threeOfAKindPts2.setPrefSize(50, 30);

        TextField fourOfAKindPts2 = new TextField();
        fourOfAKindPts2.setPrefSize(50, 30);

        TextField fullHousePts2 = new TextField();
        fullHousePts2.setPrefSize(50, 30);

        TextField smallStraightPts2 = new TextField();
        smallStraightPts2.setPrefSize(50, 30);

        TextField largeStraightPts2 = new TextField();
        largeStraightPts2.setPrefSize(50, 30);

        TextField yahtzeePts2 = new TextField();
        yahtzeePts2.setPrefSize(50, 30);

        TextField chancePts2 = new TextField();
        chancePts2.setPrefSize(50, 30);

        // Creating a bold Lower Total text, to match the Yahtzee scorecard
        Text lowerTotal2 = new Text("Lower Total");
        GridPane.setHalignment(lowerTotal2, HPos.CENTER);
        lowerTotal2.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(lowerTotal2,0,16);

        // Creating a textField for the lower score
        TextField lowerTotalPts2 = new TextField("0");
        lowerTotalPts2.setPrefSize(50, 30);
        GridPane.setConstraints(lowerTotalPts2, 1, 16);

        // Creating a bold Grand Total text, to match the Yahtzee scorecard
        Text grandTotal2 = new Text("Grand Total");
        GridPane.setHalignment(grandTotal2, HPos.CENTER);
        grandTotal2.setStyle("-fx-font-weight: bold");
        GridPane.setConstraints(grandTotal2,0,17);

        // Creating a textField for the grand score
        TextField grandTotalPts2 = new TextField("0");
        grandTotalPts2.setPrefSize(50, 30);
        GridPane.setConstraints(grandTotalPts2, 1, 17);

        // Align all the buttons in the lower part for the GridPane
        GridPane.setConstraints(threeOfAKind2, 0, 9);
        GridPane.setConstraints(fourOfAKind2, 0, 10);
        GridPane.setConstraints(fullHouse2, 0, 11);
        GridPane.setConstraints(smallStraight2, 0, 12);
        GridPane.setConstraints(largeStraight2, 0, 13);
        GridPane.setConstraints(yahtzee2, 0, 14);
        GridPane.setConstraints(chance2, 0, 15);

        // Align all the textFields in the lower part for GridPane
        GridPane.setConstraints(threeOfAKindPts2, 1, 9);
        GridPane.setConstraints(fourOfAKindPts2, 1, 10);
        GridPane.setConstraints(fullHousePts2, 1, 11);
        GridPane.setConstraints(smallStraightPts2, 1, 12);
        GridPane.setConstraints(largeStraightPts2, 1, 13);
        GridPane.setConstraints(yahtzeePts2, 1, 14);
        GridPane.setConstraints(chancePts2, 1, 15);

        //------------------Settings for the left card GridPane----------------------//


        // Setting up the size and layout of the GridPane
        GridPane leftCard = new GridPane();
        leftCard.setPadding(new Insets(125, 0, 100, 50));

        leftCard.getColumnConstraints().addAll(new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(50,50,Double.MAX_VALUE));

        // Include all components for scorecard in GridPanel
        leftCard.getChildren().addAll(ace1, two1, three1, four1, five1, six1, acePts1, twoPts1, threePts1,
                fourPts1, fivePts1, sixPts1, upperTotal1, upperTotalPts1, bonus1, bonusPts1,
                threeOfAKind1, fourOfAKind1, fullHouse1, smallStraight1, largeStraight1,
                yahtzee1, chance1, threeOfAKindPts1, fourOfAKindPts1, fullHousePts1, smallStraightPts1,
                largeStraightPts1, yahtzeePts1, chancePts1, lowerTotal1, lowerTotalPts1,
                grandTotal1, grandTotalPts1, name1);



        //------------------Settings for the right card GridPane----------------------//


        // Setting up the size and layout of the GridPane
        GridPane rightCard = new GridPane();
        rightCard.setPadding(new Insets(125, 50, 100, 60));

        rightCard.getColumnConstraints().addAll(new ColumnConstraints(100,100,Double.MAX_VALUE),
                new ColumnConstraints(50,50,Double.MAX_VALUE));

        // Include all components for scorecard in GridPanel
        rightCard.getChildren().addAll(ace2, two2, three2, four2, five2, six2, acePts2, twoPts2, threePts2,
                fourPts2, fivePts2, sixPts2, upperTotal2, upperTotalPts2, bonus2, bonusPts2,
                threeOfAKind2, fourOfAKind2, fullHouse2, smallStraight2, largeStraight2,
                yahtzee2, chance2, threeOfAKindPts2, fourOfAKindPts2, fullHousePts2, smallStraightPts2,
                largeStraightPts2, yahtzeePts2, chancePts2, lowerTotal2, lowerTotalPts2,
                grandTotal2, grandTotalPts2, name2);

        rightCard.setDisable(true);

        //------------------------ Play Area------------------------------//

        // Creating a stackPane for the center play area
        StackPane middlePlayArea = new StackPane();
        middlePlayArea.setPadding(new Insets(0, 0, 0, 55));

        // Creating the center play area background
        Rectangle playArea = new Rectangle(600, 475);
        Image playBackground = new Image("greenBackground.png");
        ImagePattern background = new ImagePattern(playBackground);
        playArea.setFill(background);

        // Creating dice 1 for the game
        Rectangle dice1 = new Rectangle(75, 75);
        Image diceOneBackground = new Image("dice1.png");
        ImagePattern die1 = new ImagePattern(diceOneBackground);
        dice1.setFill(die1);
        dice1.setX(375);
        dice1.setY(200);

        // Creating dice 2 for the game
        Rectangle dice2 = new Rectangle(75, 75);
        Image diceTwoBackground = new Image("dice2.png");
        ImagePattern die2 = new ImagePattern(diceTwoBackground);
        dice2.setFill(die1);
        dice2.setX(486);
        dice2.setY(200);

        // Creating dice 3 for the game
        Rectangle dice3 = new Rectangle(75, 75);
        Image diceThreeBackground = new Image("dice3.png");
        ImagePattern die3 = new ImagePattern(diceThreeBackground);
        dice3.setFill(die1);
        dice3.setX(597);
        dice3.setY(200);

        // Creating dice 4 for the game
        Rectangle dice4 = new Rectangle(75, 75);
        Image diceFourBackground = new Image("dice4.png");
        ImagePattern die4 = new ImagePattern(diceFourBackground);
        dice4.setFill(die1);
        dice4.setX(708);
        dice4.setY(200);

        // Creating dice 5 for the game
        Rectangle dice5 = new Rectangle(75, 75);
        Image diceFiveBackground = new Image("dice5.png");
        ImagePattern die5 = new ImagePattern(diceFiveBackground);
        dice5.setFill(die1);
        dice5.setX(820);
        dice5.setY(200);

        // Creating die 6 Face
        Image diceSixBackground = new Image("dice6.png");
        ImagePattern die6 = new ImagePattern(diceSixBackground);

        // CheckBox to roll for dice 1
        CheckBox checkBox1 = new CheckBox();
        checkBox1.setLayoutX(405);
        checkBox1.setLayoutY(295);

        // CheckBox to roll for dice 2
        CheckBox checkBox2 = new CheckBox();
        checkBox2.setLayoutX(516);
        checkBox2.setLayoutY(295);

        // CheckBox to roll for dice 3
        CheckBox checkBox3 = new CheckBox();
        checkBox3.setLayoutX(627);
        checkBox3.setLayoutY(295);

        // CheckBox to roll for dice 4
        CheckBox checkBox4 = new CheckBox();
        checkBox4.setLayoutX(738);
        checkBox4.setLayoutY(295);

        // CheckBox to roll for dice 5
        CheckBox checkBox5 = new CheckBox();
        checkBox5.setLayoutX(850);
        checkBox5.setLayoutY(295);

        // Button for rolling dice
        Button rollDice = new Button("Roll");
        rollDice.setPrefSize(50,30);

        TextField rollCount = new TextField("3");
        rollCount.setPrefSize(100,30);
        rollCount.setStyle("-fx-font-weight: bold");

        middlePlayArea.getChildren().addAll(playArea, rollDice);


        //------------------Settings for the BorderPane----------------------//

        // Moving all the Yahtzee components to the correct positions
        layout.setRight(rightCard);
        layout.setLeft(leftCard);
        layout.setCenter(middlePlayArea);
        layout.getChildren().addAll(dice1, dice2, dice3, dice4, dice5, checkBox1,
                checkBox2, checkBox3, checkBox4, checkBox5, rollCount);

        // Creating the Scene and adding the GridPane to it
        Scene scene = new Scene(layout,1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();


        //------------------Event Handlers for the game----------------------//

        //Close the application if an "Exit" button from any screen is clicked
        EventHandler<MouseEvent> exitProgram = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Platform.exit();
            }
        };
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, exitProgram);

        // Return back to the main menu screen
        EventHandler<MouseEvent> returnToMainScreen = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setScene(menuScene);
                primaryStage.show();
            }
        };
        mainScreenButton.addEventFilter(MouseEvent.MOUSE_CLICKED ,returnToMainScreen);

        //Calculates to see who won
        EventHandler<MouseEvent> calculatingWinner = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String winner = "";
                // Checks what player one
                boolean check = isWin();

                // Adds a 1 win to the winners win count and shows the winner
                if(check == true)
                {
                    winner = players[0].name;
                    player1Wins++;
                    player1WinCount.setText(players[0].name + "'s win count: " + player1Wins);
                }
                else if(plr2Score > plr1Score)
                {
                    winner = players[1].name;
                    player2Wins++;
                    player2WinCount.setText(players[1].name + "'s win count: " + player2Wins);
                }

                player1WinCount.setText(players[0].name + "'s win count: " + player1Wins);
                player2WinCount.setText(players[1].name + "'s win count: " + player2Wins);

                //Shows the winner and asks if they want to play again
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Winner");
                alert.setHeaderText(null);

                // Checks if their is a winner or draw
                if(winner != "")
                    alert.setContentText("The winner is " + winner + "\n\nWould you like to play again?");
                else
                    alert.setContentText("It's a draw" + "\n\nWould you like to play again?");

                // Stores what the user clicks
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);

                // Checks to see what the user clicked
                if (button == ButtonType.OK)
                {
                    //Resetting all values to default for player 1
                    acePts1.setText("");
                    twoPts1.setText("");
                    threePts1.setText("");
                    fourPts1.setText("");
                    fivePts1.setText("");
                    sixPts1.setText("");
                    upperTotalPts1.setText("0");
                    bonusPts1.setText("0");
                    threeOfAKindPts1.setText("");
                    fourOfAKindPts1.setText("");
                    fullHousePts1.setText("");
                    smallStraightPts1.setText("");
                    largeStraightPts1.setText("");
                    yahtzeePts1.setText("");
                    chancePts1.setText("");
                    lowerTotalPts1.setText("0");
                    grandTotalPts1.setText("0");

                    //Resetting all buttons to default for player 1
                    ace1.setDisable(false);
                    two1.setDisable(false);
                    three1.setDisable(false);
                    four1.setDisable(false);
                    five1.setDisable(false);
                    six1.setDisable(false);
                    upperTotal1.setDisable(false);
                    bonus1.setDisable(false);
                    threeOfAKind1.setDisable(false);
                    fourOfAKind1.setDisable(false);
                    fullHouse1.setDisable(false);
                    smallStraight1.setDisable(false);
                    largeStraight1.setDisable(false);
                    yahtzee1.setDisable(false);
                    chance1.setDisable(false);
                    lowerTotal1.setDisable(false);
                    grandTotal1.setDisable(false);

                    //Resetting all values to default for player 2
                    acePts2.setText("");
                    twoPts2.setText("");
                    threePts2.setText("");
                    fourPts2.setText("");
                    fivePts2.setText("");
                    sixPts2.setText("");
                    upperTotalPts2.setText("0");
                    bonusPts2.setText("0");
                    threeOfAKindPts2.setText("");
                    fourOfAKindPts2.setText("");
                    fullHousePts2.setText("");
                    smallStraightPts2.setText("");
                    largeStraightPts2.setText("");
                    yahtzeePts2.setText("");
                    chancePts2.setText("");
                    lowerTotalPts2.setText("0");
                    grandTotalPts2.setText("0");

                    //Resetting all buttons to default for player 2
                    ace2.setDisable(true);
                    two2.setDisable(true);
                    three2.setDisable(true);
                    four2.setDisable(true);
                    five2.setDisable(true);
                    six2.setDisable(true);
                    upperTotal2.setDisable(true);
                    bonus2.setDisable(true);
                    threeOfAKind2.setDisable(true);
                    fourOfAKind2.setDisable(true);
                    fullHouse2.setDisable(true);
                    smallStraight2.setDisable(true);
                    largeStraight2.setDisable(true);
                    yahtzee2.setDisable(true);
                    chance2.setDisable(true);
                    lowerTotal2.setDisable(true);
                    grandTotal2.setDisable(true);

                    //Reset rolls to 3
                    numOfRolls = 3;
                    rollDice.setDisable(false);
                    turn[0] = 1;
                }
            }
        };
        scorePoints.addEventFilter(MouseEvent.MOUSE_CLICKED, calculatingWinner);

        // Event Handler for more information
        EventHandler<MouseEvent> moreInfo = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                Alert information = new Alert(Alert.AlertType.INFORMATION);
                information.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                information.setTitle("Information");
                information.setHeaderText("Learn How To Play Yahtzee");
                information.setContentText("Rules For Scoring\n" + "-------------------\n" +
                        "At the start of a turn, the player takes all 5 dice and rolls them. " +
                        "They can then roll some or all of the dice up to two more times, " +
                        "setting aside any dice they’d like to keep and rerolling the rest. " +
                        "The dice can be scored after any of the rolls, but scoring the dice " +
                        "ends the player’s turn. Setting dice aside after one roll does not " +
                        "prevent one or more of them from being rolled again on any " +
                        "subsequent roll if the player so chooses. Below is the different " +
                        "categories that can be scored and how to score them.\n\n" +
                        "Ace: 1 point per 1 rolled,         Two: 2 points per 2 rolled\n" +
                        "Three: 3 points per 3 rolled,     Four: 4 points per 4 rolled\n" +
                        "Five: 5 points per 5 rolled,       Six: Six points per 6 rolled\n\n" +
                        "3 of A kind: 3 dice of same number is needed. Add all dice up\n" +
                        "4 of A kind: 4 dice of same number is needed. Add all dice up\n" +
                        "Small Straight: Any four consecutive numbers, worth 30 points\n" +
                        "Large straight: Any five consecutive numbers , worth 40 points\n" +
                        "Yahtzee: 5 dice showing the same number, worth 50 points\n" +
                        "Chance: Add all 5 dice up, worth the total\n\n" + "Software\n" +
                        "----------\n" + "To roll, the turn player should start by " +
                        "checking all 5 dice checkboxes, then roll and unselect the " +
                        "dice they wish to keep. To score points, click on the players " +
                        "scorecard category button, to scratch click on a category you " +
                        "wish to scratch and you will get 0 points if you do not have it." +
                        "When a player scores, the next player should recheck all 5 dice " +
                        "and roll, this will disable the other players scorecard to" +
                        "prevent cheating. When both players have filled their scorecards " +
                        "click on the score points button to find the winner. This can also " +
                        "be used as a forefit button for the turn player.\n\n" +
                        "Claimer!\n" + "The scoring in this game is open ended as well" +
                        " as rolling dice, this allows for cheating however the game " +
                        "is intended to mirror playing in real life. You would not give yourself " +
                        "free points nor leave your opponents dice as your own, so don't do it here");

                information.show();
            }
        };
        info.addEventFilter(MouseEvent.MOUSE_CLICKED, moreInfo);

        // Event Handler for rolling dice
        EventHandler<MouseEvent> roll = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                // Disableds and enables scorecards based on players turn
                if(turn[0] == 1) {
                    leftCard.setDisable(false);
                    rightCard.setDisable(true);
                }
                else {
                    leftCard.setDisable(true);
                    rightCard.setDisable(false);

                }

                // Keeping track of number of rolls in a turn
                numOfRolls--;

                // Checking if checbox1 is checked and rolling die
                if(checkBox1.isSelected()) {
                    dieObj1.dieValue = dieObj1.rollDie();

                    // Checking what the value of the dice is
                    if(dieObj1.dieValue == 1)
                        dice1.setFill(die1);
                    else if (dieObj1.dieValue == 2)
                        dice1.setFill(die2);
                    else if(dieObj1.dieValue == 3)
                        dice1.setFill(die3);
                    else if (dieObj1.dieValue == 4)
                        dice1.setFill(die4);
                    else if(dieObj1.dieValue == 5)
                        dice1.setFill(die5);
                    else if (dieObj1.dieValue == 6)
                        dice1.setFill(die6);
                }


                // Checking if checbox2 is checked and rolling die
                if(checkBox2.isSelected()) {
                    dieObj2.dieValue = dieObj2.rollDie();

                    // Checking what the value of the dice is
                    if(dieObj2.dieValue == 1)
                        dice2.setFill(die1);
                    else if (dieObj2.dieValue == 2)
                        dice2.setFill(die2);
                    else if(dieObj2.dieValue == 3)
                        dice2.setFill(die3);
                    else if (dieObj2.dieValue == 4)
                        dice2.setFill(die4);
                    else if(dieObj2.dieValue == 5)
                        dice2.setFill(die5);
                    else if (dieObj2.dieValue == 6)
                        dice2.setFill(die6);
                }

                // Checking if checbox3 is checked and rolling die
                if(checkBox3.isSelected()) {
                    dieObj3.dieValue = dieObj3.rollDie();

                    // Checking what the value of the dice is
                    if(dieObj3.dieValue == 1)
                        dice3.setFill(die1);
                    else if (dieObj3.dieValue == 2)
                        dice3.setFill(die2);
                    else if(dieObj3.dieValue == 3)
                        dice3.setFill(die3);
                    else if (dieObj3.dieValue == 4)
                        dice3.setFill(die4);
                    else if(dieObj3.dieValue == 5)
                        dice3.setFill(die5);
                    else if (dieObj3.dieValue == 6)
                        dice3.setFill(die6);
                }

                // Checking if checbox4 is checked and rolling die
                if(checkBox4.isSelected()) {
                    dieObj4.dieValue = dieObj4.rollDie();

                    // Checking what the value of the dice is
                    if(dieObj4.dieValue == 1)
                        dice4.setFill(die1);
                    else if (dieObj4.dieValue == 2)
                        dice4.setFill(die2);
                    else if(dieObj4.dieValue == 3)
                        dice4.setFill(die3);
                    else if (dieObj4.dieValue == 4)
                        dice4.setFill(die4);
                    else if(dieObj4.dieValue == 5)
                        dice4.setFill(die5);
                    else if (dieObj4.dieValue == 6)
                        dice4.setFill(die6);
                }

                // Checking if checbox5 is checked and rolling die
                if(checkBox5.isSelected()) {
                    dieObj5.dieValue = dieObj5.rollDie();

                    // Checking what the value of the dice is
                    if(dieObj5.dieValue == 1)
                        dice5.setFill(die1);
                    else if (dieObj5.dieValue == 2)
                        dice5.setFill(die2);
                    else if(dieObj5.dieValue == 3)
                        dice5.setFill(die3);
                    else if (dieObj5.dieValue == 4)
                        dice5.setFill(die4);
                    else if(dieObj5.dieValue == 5)
                        dice5.setFill(die5);
                    else if (dieObj5.dieValue == 6)
                        dice5.setFill(die6);
                }

                // If no rolls this turn are left
                if(numOfRolls == 0)
                    rollDice.setDisable(true);
            }
        };
        rollDice.addEventFilter(MouseEvent.MOUSE_CLICKED, roll);

        //--------------------------Player One---------------------------------//

        // Event handler for scoring player ones Aces
        EventHandler<MouseEvent> scoreAces1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are ones and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 1, increment count
                    if(dice[i].dieValue == 1)
                        count++;
                }

                // Calculating points and converting to a string
                score = (1 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                acePts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                ace1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        ace1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreAces1);

        // Event handler for scoring player ones Twos
        EventHandler<MouseEvent> scoreTwos1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                leftCard.setDisable(false);

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are twos and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 2, increment count
                    if(dice[i].dieValue == 2)
                        count++;
                }

                // Calculating points and converting to a string
                score = (2 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                twoPts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                two1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        two1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreTwos1);

        // Event handler for scoring player ones Threes
        EventHandler<MouseEvent> scoreThrees1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are threes and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 2, increment count
                    if(dice[i].dieValue == 3)
                        count++;
                }

                // Calculating points and converting to a string
                score = (3 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                threePts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                three1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        three1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreThrees1);

        // Event handler for scoring player ones Fours
        EventHandler<MouseEvent> scoreFours1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are fours and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 4, increment count
                    if(dice[i].dieValue == 4)
                        count++;
                }

                // Calculating points and converting to a string
                score = (4 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                fourPts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                four1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);

            }
        };
        four1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFours1);

        // Event handler for scoring player ones Fives
        EventHandler<MouseEvent> scoreFives1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are fives and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 5, increment count
                    if(dice[i].dieValue == 5)
                        count++;
                }

                // Calculating points and converting to a string
                score = (5 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                fivePts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                five1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        five1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFives1);

        // Event handler for scoring player ones Sixes
        EventHandler<MouseEvent> scoreSixes1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are sixes and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 6, increment count
                    if(dice[i].dieValue == 6)
                        count++;
                }

                // Calculating points and converting to a string
                score = (6 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                sixPts1.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts1.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts1.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                six1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        six1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreSixes1);

        // Event handler for scoring player ones three of a kind
        EventHandler<MouseEvent> scoreThreeOfAKind1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Needs local count so it resets after every check
                    int count = 0;
                    // Loop through all die
                    for(int j = 0; j < 5; j++ )
                    {
                        if(dice[j].dieValue == i)
                            count++;
                    }

                    // If three of a kind
                    if(count >= 3)
                    {
                        // Calculating points
                        for(int k = 0; k < 5; k++)
                            score += dice[k].dieValue;
                    }
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                threeOfAKindPts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                threeOfAKind1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        threeOfAKind1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreThreeOfAKind1);

        // Event handler for scoring player ones four of a kind
        EventHandler<MouseEvent> scoreFourOfAKind1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Needs local count so it resets after every check
                    int count = 0;
                    // Loop through all die
                    for(int j = 0; j < 5; j++ )
                    {
                        if(dice[j].dieValue == i)
                            count++;
                    }

                    // If four of a kind
                    if(count >= 4)
                    {
                        // Calculating points
                        for(int k = 0; k < 5; k++)
                            score += dice[k].dieValue;
                    }
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                fourOfAKindPts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                fourOfAKind1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);

                takeTurn(turn);
            }
        };
        fourOfAKind1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFourOfAKind1);

        // Event handler for scoring player ones full house
        EventHandler<MouseEvent> scoreFullHouse1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Counts if there are three of the same value
                    int countForThree = 0;
                    // Counts if there are two of the same value
                    int countForTwo = 0;
                    // Logic check for a full house
                    if(val[0] == val[1] && val[1] == val[2] && val[3] == val[4] && val[2] != val[3] ||
                            val[0] == val[1] && val[2] == val[3] && val[3] == val[4] && val[1] != val[2])
                        score = 25;
                    else
                        score = 0;
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                fullHousePts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                fullHouse1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        fullHouse1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFullHouse1);

        // Event handler for scoring player ones small straight
        EventHandler<MouseEvent> scoreSmallStraight1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                for(int i = 0; i < 4; i++)
                {
                    // Checks if value is already in array
                    if(val[i + 1] == val[i])
                    {
                        int temp = val[i + 1];
                        // Shifting value to the end
                        for(int j = i; j < 4; j++)
                            val[j] = val[j + 1];

                        // Setting last value in array to the duplicate
                        val[4] = temp;
                    }
                }

                // Logic for checking for a small straight
                if(val[0] == 1 && val[1] == 2 && val[2] == 3 && val[3] == 4||
                        val[0] == 2 && val[1] == 3 && val[2] == 4 && val[3] == 5 ||
                        val[0] == 3 && val[1] == 4 && val[2] == 5 && val[3] == 6 ||
                        val[1] == 1 && val[2] == 2 && val[3] == 3 && val[4] == 4 ||
                        val[1] == 2 && val[2] == 3 && val[3] == 4 && val[4] == 5 ||
                        val[1] == 3 && val[2] == 4 && val[3] == 5 && val[4] == 6)
                    score = 30;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                smallStraightPts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                smallStraight1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        smallStraight1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreSmallStraight1);

        // Event handler for scoring player ones large straight
        EventHandler<MouseEvent> scoreLargeStraight1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                // Logic for checking for a large straight
                if(val[0] == 1 && val[1] == 2 && val[2] == 3 && val[3] == 4 && val[4] == 5 ||
                        val[0] == 2 && val[1] == 3 && val[2] == 4 && val[3] == 5 && val[4] == 6)
                    score = 40;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                largeStraightPts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                largeStraight1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        largeStraight1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreLargeStraight1);

        // Event handler for scoring player ones Yahtzee
        EventHandler<MouseEvent> scoreYahtzee1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 1;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);


                for(int i = 0; i < 4; i++)
                {
                    int value = dice[i].dieValue;
                    // Checks if all dice are the same
                    if(dice[i + 1].dieValue == value)
                        count++;
                }
                // Checks if a Yahtzee
                if(count == 5)
                    score = 50;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                yahtzeePts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                yahtzee1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        yahtzee1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreYahtzee1);

        // Event handler for scoring player ones Chance
        EventHandler<MouseEvent> scoreChance1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts1.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts1.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts1.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts1.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Adding up all dice for a chance
                for(int i = 0; i < 5; i++)
                    score += dice[i].dieValue;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                chancePts1.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts1.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr1Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts1.setText(grandScore1);

                chance1.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        chance1.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreChance1);

        //---------------------------Player Two----------------------------//

        // Event handler for scoring player twos Aces
        EventHandler<MouseEvent> scoreAces2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are ones and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 1, increment count
                    if(dice[i].dieValue == 1)
                        count++;
                }

                // Calculating points and converting to a string
                score = (1 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                acePts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore2 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore2);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                ace2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        ace2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreAces2);

        // Event handler for scoring player twos Twos
        EventHandler<MouseEvent> scoreTwos2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are twos and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 2, increment count
                    if(dice[i].dieValue == 2)
                        count++;
                }

                // Calculating points and converting to a string
                score = (2 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                twoPts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore2 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore2);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                two2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        two2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreTwos2);

        // Event handler for scoring player twos Threes
        EventHandler<MouseEvent> scoreThrees2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are threes and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 2, increment count
                    if(dice[i].dieValue == 3)
                        count++;
                }

                // Calculating points and converting to a string
                score = (3 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                threePts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore2 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore2);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                three2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        three2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreThrees2);

        // Event handler for scoring player twos Fours
        EventHandler<MouseEvent> scoreFours2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are fours and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 4, increment count
                    if(dice[i].dieValue == 4)
                        count++;
                }

                // Calculating points and converting to a string
                score = (4 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                fourPts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore1 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore1);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                four2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        four2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFours2);

        // Event handler for scoring player twos Fives
        EventHandler<MouseEvent> scoreFives2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are fives and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 5, increment count
                    if(dice[i].dieValue == 5)
                        count++;
                }

                // Calculating points and converting to a string
                score = (5 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                fivePts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore2 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore2);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                five2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        five2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFives2);

        // Event handler for scoring player twos Sixes
        EventHandler<MouseEvent> scoreSixes2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;
                int bonus = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Checking which Dice objects are sixes and adding it to total
                for(int i = 0; i < 5; i++)
                {
                    // If die value is 6, increment count
                    if(dice[i].dieValue == 6)
                        count++;
                }

                // Calculating points and converting to a string
                score = (6 * count);
                upperTotal += score;
                String total = Integer.toString(score);

                sixPts2.setText(total);

                // Convert score to string and set to textfield
                String upperScore2 = Integer.toString(upperTotal);
                upperTotalPts2.setText(upperScore2);

                // Checks if user has enough for bonus
                if(upperTotal >= 63) {
                    bonus = 35;
                    bonusPts2.setText("35");
                }

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonus;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                six2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        six2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreSixes2);

        // Event handler for scoring player ones three of a kind
        EventHandler<MouseEvent> scoreThreeOfAKind2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Needs local count so it resets after every check
                    int count = 0;
                    // Loop through all die
                    for(int j = 0; j < 5; j++ )
                    {
                        if(dice[j].dieValue == i)
                            count++;
                    }

                    // If three of a kind
                    if(count >= 3)
                    {
                        // Calculating points
                        for(int k = 0; k < 5; k++)
                            score += dice[k].dieValue;
                    }
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                threeOfAKindPts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore1 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore1);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                threeOfAKind2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        threeOfAKind2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreThreeOfAKind2);

        // Event handler for scoring player twos four of a kind
        EventHandler<MouseEvent> scoreFourOfAKind2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Needs local count so it resets after every check
                    int count = 0;
                    // Loop through all die
                    for(int j = 0; j < 5; j++ )
                    {
                        if(dice[j].dieValue == i)
                            count++;
                    }

                    // If four of a kind
                    if(count >= 4)
                    {
                        // Calculating points
                        for(int k = 0; k < 5; k++)
                            score += dice[k].dieValue;
                    }
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                fourOfAKindPts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                fourOfAKind2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        fourOfAKind2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFourOfAKind2);

        // Event handler for scoring player ones full house
        EventHandler<MouseEvent> scoreFullHouse2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                // Loop through all possible values
                for( int i = 1; i <= 6; i++ )
                {
                    // Counts if there are three of the same value
                    int countForThree = 0;
                    // Counts if there are two of the same value
                    int countForTwo = 0;
                    // Logic check for a full house
                    if(val[0] == val[1] && val[1] == val[2] && val[3] == val[4] && val[2] != val[3] ||
                            val[0] == val[1] && val[2] == val[3] && val[3] == val[4] && val[1] != val[2])
                        score = 25;
                    else
                        score = 0;
                }

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                fullHousePts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                fullHouse2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        fullHouse2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreFullHouse2);

        // Event handler for scoring player twos small straight
        EventHandler<MouseEvent> scoreSmallStraight2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                for(int i = 0; i < 4; i++)
                {
                    // Checks if value is already in array
                    if(val[i + 1] == val[i])
                    {
                        int temp = val[i + 1];
                        // Shifting value to the end
                        for(int j = i; j < 4; j++)
                            val[j] = val[j + 1];

                        // Setting last value in array to the duplicate
                        val[4] = temp;
                    }
                }

                // Logic for checking for a small straight
                if(val[0] == 1 && val[1] == 2 && val[2] == 3 && val[3] == 4||
                        val[0] == 2 && val[1] == 3 && val[2] == 4 && val[3] == 5 ||
                        val[0] == 3 && val[1] == 4 && val[2] == 5 && val[3] == 6 ||
                        val[1] == 1 && val[2] == 2 && val[3] == 3 && val[4] == 4 ||
                        val[1] == 2 && val[2] == 3 && val[3] == 4 && val[4] == 5 ||
                        val[1] == 3 && val[2] == 4 && val[3] == 5 && val[4] == 6)
                    score = 30;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                smallStraightPts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                smallStraight2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        smallStraight2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreSmallStraight2);

        // Event handler for scoring player twos large straight
        EventHandler<MouseEvent> scoreLargeStraight2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 0;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // An array that stores the die objects values
                int[] val = new int[5];
                for(int i = 0; i < 5; i++)
                    val[i] = dice[i].dieValue;

                // Sorting the array of die values
                Arrays.sort(val);

                // Logic for checking for a large straight
                if(val[0] == 1 && val[1] == 2 && val[2] == 3 && val[3] == 4 && val[4] == 5 ||
                        val[0] == 2 && val[1] == 3 && val[2] == 4 && val[3] == 5 && val[4] == 6)
                    score = 40;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                largeStraightPts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore1 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore1);

                largeStraight2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        largeStraight2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreLargeStraight2);

        // Event handler for scoring player twos Yahtzee
        EventHandler<MouseEvent> scoreYahtzee2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int count = 1;
                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);


                for(int i = 0; i < 4; i++)
                {
                    int value = dice[i].dieValue;
                    // Checks if all dice are the same
                    if(dice[i + 1].dieValue == value)
                        count++;
                }
                // Checks if a Yahtzee
                if(count == 5)
                    score = 50;
                else
                    score = 0;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                yahtzeePts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                yahtzee2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        yahtzee2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreYahtzee2);

        // Event handler for scoring player twos Chance
        EventHandler<MouseEvent> scoreChance2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                int score = 0;

                // Retriving text from upper Total and making it an int
                String topTotal = upperTotalPts2.getText();
                int upperTotal = Integer.parseInt(topTotal);

                // Retriving text from lower Total and making it an int
                String bottomTotal = lowerTotalPts2.getText();
                int lowerTotal = Integer.parseInt(bottomTotal);

                // Retriving text from the bonus and making it an int
                String bonusTotal = bonusPts2.getText();
                int bonusScore = Integer.parseInt(bonusTotal);

                // Retriving text from grand total and making it an int
                String grandScore = grandTotalPts2.getText();
                int grandTotal = Integer.parseInt(grandScore);

                // Adding up all dice for a chance
                for(int i = 0; i < 5; i++)
                    score += dice[i].dieValue;

                // Converting to a string
                lowerTotal += score;
                String total = Integer.toString(score);

                chancePts2.setText(total);

                // Convert score to string and set to textfield
                String lowerScore2 = Integer.toString(lowerTotal);
                lowerTotalPts2.setText(lowerScore2);

                // Calculating grand total and setting to string
                grandTotal = upperTotal + lowerTotal + bonusScore;
                plr2Score = grandTotal;
                String grandScore2 = Integer.toString(grandTotal);
                grandTotalPts2.setText(grandScore2);

                chance2.setDisable(true);
                // Make sure that after the turn, the roll button is enabled
                rollDice.setDisable(false);
                takeTurn(turn);
            }
        };
        chance2.addEventFilter(MouseEvent.MOUSE_CLICKED, scoreChance2);

    }


    @Override
    boolean isWin() {

       if(plr1Score > plr2Score)
           return true;
       else
        return false;
    }

    @Override
    void takeTurn(int[] input)
    {
        // Resetting number of rolls per turn
        numOfRolls = 3;

        // Checks whos turn it is
        if (input[0] == 1)
            input[0] = 2;
        else
            input[0] = 1;
    }
}