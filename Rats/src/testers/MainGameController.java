package testers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main game window class
 */
public class MainGameController {

    @FXML BorderPane mainGameRootPane;
    @FXML ProgressBar mainGameProgressBar;
    @FXML VBox mainGameInventoryRegion;
    @FXML VBox mainGameVBoxRegionLeft;
    @FXML Button mainGameQuitButton;
    @FXML Canvas mainGameCanvas;
    @FXML ImageView poisonIconImage;

    DraggableMaker draggableMaker = new DraggableMaker();

    public void initialize() {

        mainGameQuitButton.setOnAction(e -> closeWindow());
        draggableMaker.makeDraggable(poisonIconImage);
        draggableMaker.makeDraggable(mainGameCanvas);
        draggableMaker.makeDraggable(mainGameInventoryRegion);

    }

    /**
     * closes current stage scene window
     */
    public void closeWindow() {
        //set what the back button does when pressed
        Stage stage = (Stage) mainGameRootPane.getScene().getWindow();
        stage.close();
    }

}
