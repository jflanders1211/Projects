package Project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        //create a stage and set width and height
        primaryStage.setTitle("GameSMASH");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setResizable(true);

        //-----------------CREATE THE SCENE FOR THE LOADING SCREEN-----------------\\

        //create a Grid Pane
        GridPane loadGridpane = new GridPane();
        loadGridpane.setAlignment(Pos.TOP_CENTER);
        loadGridpane.setStyle("-fx-background-color: white");

        //Setting the padding
        loadGridpane.setPadding(new Insets(100, 100, 100, 100));

        //set horizontal gap and vertical gap
        loadGridpane.setHgap(0);
        loadGridpane.setVgap(75);

        //creating the text and buttons for the loading screen
        Image gamesmashlogo = new Image("gamesmash.png", 700, 85, false, false);
        Button loadingPlay = new Button("Play");
        Button loadingExit = new Button("Exit");
        Font bigFont = new Font(40);
        loadingPlay.setFont(bigFont);
        loadingExit.setFont(bigFont);

        Text creatorText = new Text("GameSMASH is a collaborative work made by Ryan Cherry, Jarrod Flanders, and Ryan Sievert");

        HBox creatorhbox = new HBox();
        creatorhbox.setAlignment(Pos.CENTER);
        creatorhbox.getChildren().addAll(creatorText);

        HBox loadinghbox = new HBox();
        loadinghbox.getChildren().addAll(loadingPlay, loadingExit);
        loadinghbox.setAlignment(Pos.CENTER);
        loadinghbox.setSpacing(20);

        //assign everything to the gridpane
        loadGridpane.add(new ImageView(gamesmashlogo), 1, 0);
        loadGridpane.add(loadinghbox, 1, 1);
        loadGridpane.add(creatorhbox, 1, 5);
        Scene loadingScene = new Scene(loadGridpane);

        //-------------------FINISH SCENE FOR THE LOADING SCREEN-------------------\\


        //-------------------CREATE THE SCENE FOR THE MENU SCREEN-------------------\\

        //create a Grid Pane
        GridPane menuGridpane = new GridPane();
        menuGridpane.setAlignment(Pos.TOP_CENTER);

        //Setting the padding
        menuGridpane.setPadding(new Insets(10, 10, 10, 10));

        //set horizontal gap and vertical gap
        menuGridpane.setHgap(40);
        menuGridpane.setVgap(10);

        //create all the buttons and text
        Button returnToLoadingScreen = new Button("Loading Screen");
        returnToLoadingScreen.setPrefWidth(100);
        Button menuExit = new Button("Exit");
        menuExit.setPrefWidth(100);
        Button loadingHelp = new Button("Info");
        loadingHelp.setStyle("-fx-background-radius: 15px;");

        //create connect four button with image
        Image connectfourart = new Image("connect4art.png");
        Button connectfourPlay = new Button("Connect Four", new ImageView(connectfourart));

        //create tic tac toe button with image
        Image tictactoeart = new Image("tictactoeart.png");
        Button tictactoePlay = new Button("Tic Tac Toe", new ImageView(tictactoeart));

        //create yahtzee button with image
        Image yahtzeeart = new Image("yahtzeeart.png");
        Button yahtzeePlay = new Button("Yahtzee", new ImageView(yahtzeeart));

        //create border pane and hbox pane to put "Loading Screen" and "Exit" at the top
        BorderPane menuBorderPane = new BorderPane();
        HBox hbox = new HBox();
        //put Loading Screen and Exit buttons inside the hbox pane
        hbox.setSpacing(1065);
        hbox.getChildren().addAll(returnToLoadingScreen, menuExit);
        //add hbox to the top of the border pane
        menuBorderPane.setTop(hbox);

        Text player1Name = new Text("Player One's Name");
        Text player2Name = new Text("Player Two's Name");

        TextField player1NameData = new TextField("player1");
        TextField player2NameData = new TextField("player2");

        //assign everything to the gridpane
        menuGridpane.add(player1Name, 2, 5);
        menuGridpane.add(player2Name, 2, 7);
        menuGridpane.add(player1NameData, 2, 6);
        menuGridpane.add(player2NameData, 2, 8);
        menuGridpane.add(connectfourPlay, 1, 9);
        menuGridpane.add(tictactoePlay, 2, 9);
        menuGridpane.add(yahtzeePlay, 3, 9);
        menuGridpane.add(loadingHelp, 2, 10);

        //add the gridpane to the center of the border pane
        menuBorderPane.setCenter(menuGridpane);

        Scene menuScene = new Scene(menuBorderPane);

        //---------------------FINISH SCENE FOR THE MENU SCREEN---------------------\\

        primaryStage.setScene(loadingScene);
        primaryStage.show();

        //Switch to Menu Screen when the "Play" button on the Loading Screen is clicked
        EventHandler<MouseEvent> clickedLoadingPlayButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                primaryStage.setScene(menuScene);
                primaryStage.show();
            }
        };
        //when "Play is clicked on the loading Screen, load the new Menu Screen scene
        loadingPlay.addEventFilter(MouseEvent.MOUSE_CLICKED, clickedLoadingPlayButton);

        //Switch to Loading Screen when the "Loading Screen" button on the Menu Screen is clicked
        EventHandler<MouseEvent> returnToLoading = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                primaryStage.setScene(loadingScene);
                primaryStage.show();
            }
        };
        returnToLoadingScreen.addEventFilter(MouseEvent.MOUSE_CLICKED, returnToLoading);

        //Close the application if an "Exit" button from any screen is clicked
        EventHandler<MouseEvent> exitProgram = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Platform.exit();
            }
        };
        loadingExit.addEventFilter(MouseEvent.MOUSE_CLICKED, exitProgram);
        menuExit.addEventFilter(MouseEvent.MOUSE_CLICKED, exitProgram);

        //Dialog popup when the "Help" button is clicked on the LoadingScreen
        EventHandler<MouseEvent> loadingScreenHelpDialog = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Welcome to GameSMASH! Players may choose their names using the text fields. " +
                        "To begin playing a game, click the desired game button on this screen.");

                alert.showAndWait();
            }
        };
        loadingHelp.addEventFilter(MouseEvent.MOUSE_CLICKED, loadingScreenHelpDialog);

        //Run the Connect Four game when the "Connect Four" button on the Menu Screen is clicked
        EventHandler<MouseEvent> playConnectFour = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ConnectFour connectfour = new ConnectFour(new Player(player1NameData.getText()), new Player(player2NameData.getText()));
                connectfour.play(primaryStage);
            }
        };
        connectfourPlay.addEventFilter(MouseEvent.MOUSE_CLICKED, playConnectFour);

        //Run the TicTacToe game when the "TicTacToe" button on the Menu Screen is clicked
        EventHandler<MouseEvent> playTicTacToe = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TicTacToe tictactoe = new TicTacToe(new Player(player1NameData.getText()), new Player(player2NameData.getText()));
                tictactoe.play(primaryStage);
            }
        };
        tictactoePlay.addEventFilter(MouseEvent.MOUSE_CLICKED, playTicTacToe);

        //Run the Yahtzee game when the "Yahtzee" button on the Menu Screen is clicked
        EventHandler<MouseEvent> playYahtzee = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Yahtzee yahtzee = new Yahtzee(new Player(player1NameData.getText()), new Player(player2NameData.getText()));
                yahtzee.play(primaryStage);
            }
        };
        yahtzeePlay.addEventFilter(MouseEvent.MOUSE_CLICKED, playYahtzee);
    }

    public static void main(String args[]) {
        launch(args);
    }
}
