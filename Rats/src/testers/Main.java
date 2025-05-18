package testers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main class of the application that will initiate the first window
 *
 */
public class Main extends Application {

    // The dimensions of the window
    private static final int MAIN_WINDOW_WIDTH = 800;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    private static final String WINDOW_TITLE = "Rats!";

    // the dimensions of the new profile window
    public static final int NEW_PROFILE_WINDOW_WIDTH = 400;
    public static final int NEW_PROFILE_WINDOW_HEIGHT = 500;
    public static final String NEW_PROFILE_WINDOW_TITLE = "New Profile";

    // the dimensions of the high score window
    public static final int HIGH_SCORE_WIDTH = 400;
    public static final int HIGH_SCORE_HEIGHT = 400;
    public static final String HIGH_SCORE_WINDOW_TITLE = "High Scores";

    // the dimensions of the high score window
    public static final int LOAD_PROFILE_WIDTH = 400;
    public static final int LOAD_PROFILE_HEIGHT = 600;
    public static final String LOAD_PROFILE_WINDOW_TITLE = "Load Profile";


    // The dimensions of the game window
    public static final int GAME_WINDOW_WIDTH = 1280;
    public static final int GAME_WINDOW_HEIGHT = 920;
    public static final String MAIN_GAME_WINDOW_TITLE = "Playing Rats!";


    /**
     * starts the applications main window
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the main scene.
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
            Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

            // Place the main scene on stage and show it.
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
