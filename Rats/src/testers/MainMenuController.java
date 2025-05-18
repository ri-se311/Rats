package testers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Controller for the Main Menu window of the game
 *
 */
public class MainMenuController {

    //button names must match those set in the id of the fxml!
    @FXML
    Button mainMenuPlayButton;
    @FXML
    Button mainMenuNewProfileButton;
    @FXML
    Button mainMenuLoadProfileButton;
    @FXML
    Button mainMenuHighScoreButton;
    @FXML
    Button mainMenuQuitButton;
    @FXML
    TextField mainMenuMOTDTextField;


    /**
     * Initialize the controller.
     * This method is called automatically. The following happen in this order:
     * 1) First an instance of the controller is created (the constructor is called),
     * 2) Next the @FXML variables are bound to the GUI components.
     * 3) Finally, this initializes method is called.
     * This means we cannot bind actions to the buttons in the constructor, but we can in this method.
     */
    public void initialize() {
        //set what the buttons do upon action from user
        mainMenuPlayButton.setOnAction(e -> handlePlayButtonAction());

        mainMenuNewProfileButton.setOnAction(e -> handleNewProfileButtonAction());

        mainMenuLoadProfileButton.setOnAction(e -> handleLoadProfileButtonAction());

        mainMenuHighScoreButton.setOnAction(e -> handleHighScoreButtonAction());

        mainMenuQuitButton.setOnAction(e -> handleQuitButtonAction());

        try {
            //returns the puzzle string to the main menu MOTD text field at the bottom
            mainMenuMOTDTextField.setText(MOTDPuzzleSolver.solvePuzzle().split("\\(")[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * handles what the play button does when pressed
     */
    public void handlePlayButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainGame.fxml"));

            // Create a scene based on the loaded FXML scene graph
            BorderPane mainGameRootPane;
            mainGameRootPane = fxmlLoader.load();
            MainGameController mainGameController = fxmlLoader.getController();

            GameBoard board = new GameBoard("Rats/resources/SampleGrid.txt", mainGameController.mainGameCanvas);
            board.getRatManager().addRat(new AntagonistRat(new Position(1, 1), board, true));
            Timeline timeline = new Timeline();
            //sets the tickrate for the gameboard
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), (e) -> board.tick()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            Scene mainGameScene = new Scene(mainGameRootPane, Main.GAME_WINDOW_WIDTH, Main.GAME_WINDOW_HEIGHT);

            // Create a new stage (i.e., window) to hold the main game.
            Stage mainGameStage = new Stage();
            mainGameStage.setScene(mainGameScene);
            mainGameStage.setTitle(Main.MAIN_GAME_WINDOW_TITLE);

            // Make the stage a modal window.
            // This means that it must be closed before you can interact with any other window from this application.
            mainGameStage.initModality(Modality.APPLICATION_MODAL);

            // Show scene and wait for it to be closed
            mainGameStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * handles the new profile button from main menu when pressed
     */
    public void handleNewProfileButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewProfile.fxml"));

            // Create a scene based on the loaded FXML scene graph
            BorderPane newProfileRoot;
            newProfileRoot = fxmlLoader.load();
            NewProfileController newProfileController = fxmlLoader.getController();

            Scene newProfileScene = new Scene(newProfileRoot, Main.NEW_PROFILE_WINDOW_WIDTH, Main.NEW_PROFILE_WINDOW_HEIGHT);

            // Create a new stage (i.e., window) based on the new profile scene
            Stage newProfileStage = new Stage();
            newProfileStage.setScene(newProfileScene);
            newProfileStage.setTitle(Main.NEW_PROFILE_WINDOW_TITLE);

            // Make the stage a modal window.
            // This means that it must be closed before you can interact with any other window from this application.
            newProfileStage.initModality(Modality.APPLICATION_MODAL);

            // Show the edit scene and wait for it to be closed
            newProfileStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }


    }

    /**
     * handles when the quit button is pressed
     */
    public void handleQuitButtonAction() {
        System.exit(0);
    }

    /**
     * handles when the high score button is pressed
     */
    public void handleHighScoreButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HighScore.fxml"));

            // Create a scene based on the loaded FXML scene graph
            AnchorPane highScoreRoot;
            highScoreRoot = fxmlLoader.load();
            HighScoreController highScoreController = fxmlLoader.getController();

            Scene newProfileScene = new Scene(highScoreRoot, Main.HIGH_SCORE_WIDTH, Main.HIGH_SCORE_HEIGHT);

            // Create a new stage (i.e., window) based on the new profile scene
            Stage highScoreStage = new Stage();
            highScoreStage.setScene(newProfileScene);
            highScoreStage.setTitle(Main.HIGH_SCORE_WINDOW_TITLE);

            // Make the stage a modal window.
            // This means that it must be closed before you can interact with any other window from this application.
            highScoreStage.initModality(Modality.APPLICATION_MODAL);

            // Show the edit scene and wait for it to be closed
            highScoreStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

    /**
     * handles when the load profile button is pressed
     */
    public void handleLoadProfileButtonAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoadProfileViewer.fxml"));

            // Create a scene based on the loaded FXML scene graph
            BorderPane loadProfileRoot;
            loadProfileRoot = fxmlLoader.load();
            LoadProfileViewerController loadProfileViewerController = fxmlLoader.getController();

            Scene loadProfileScene = new Scene(loadProfileRoot, Main.LOAD_PROFILE_WIDTH, Main.LOAD_PROFILE_HEIGHT);

            // Create a new stage (i.e., window) based on the new profile scene
            Stage loadProfileStage = new Stage();
            loadProfileStage.setScene(loadProfileScene);
            loadProfileStage.setTitle(Main.LOAD_PROFILE_WINDOW_TITLE);

            // Make the stage a modal window.
            // This means that it must be closed before you can interact with any other window from this application.
            loadProfileStage.initModality(Modality.APPLICATION_MODAL);

            // Show the edit scene and wait for it to be closed
            loadProfileStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            // Quit the program (with an error code)
            System.exit(-1);
        }
    }

}




