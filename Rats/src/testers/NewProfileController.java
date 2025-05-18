package testers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the New Profile window of the game
 */

public class NewProfileController {
    @FXML Button newProfileConfirmButton;
    @FXML Button newProfileBackButton;
    @FXML Pane newProfileRootPane;
    @FXML TextField newProfileTextField;

    /**
     * Initialize the controller.
     * This method is called automatically. The following happen in this order:
     * 1) First an instance of the controller is created (the constructor is called),
     * 2) Next the @FXML variables are bound to the GUI components.
     * 3) Finally, this initializes method is called.
     * This means we cannot bind actions to the buttons in the constructor, but we can in this method.
     */
    public void initialize() {

        newProfileBackButton.setOnAction(e -> closeWindow());
        newProfileConfirmButton.setOnAction((e -> handleNewProfileConfirmButtonAction()));

    }

    /**
     * closes current stage scene window
     */
    public void closeWindow() {
        //set what the back button does when pressed
        Stage stage = (Stage) newProfileRootPane.getScene().getWindow();
        stage.close();
    }


    /**
     * handles the new profile confirm button when pressed
     */
    public void handleNewProfileConfirmButtonAction() {
        //set what the confirm button does when pressed
        LoadProfileViewerController loadProfileViewerController = new LoadProfileViewerController();
        loadProfileViewerController.addProfile(newProfileTextField.getText());
        loadProfileViewerController.saveProfileToFile();
        closeWindow();
    }

}
