package testers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the high score window of the game
 *
 */
public class HighScoreController {

    @FXML
    Button highScoreBackButton;
    @FXML
    Pane highScore;

    public void initialize() {
        highScoreBackButton.setOnAction(e -> handleHighScoreBackButton());
    }

    /**
     * button to back to main menu
     */
    public void handleHighScoreBackButton() {
        //set what the buttons do upon action from user
        Stage stage = (Stage) highScore.getScene().getWindow();
        stage.close();
    }


}
