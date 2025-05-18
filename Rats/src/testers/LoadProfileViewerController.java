package testers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for the Load Profile window of the game
 *
 */
public class LoadProfileViewerController {
    @FXML
    BorderPane loadProfilePane;
    @FXML
    ListView<String> loadProfileList;
    @FXML
    Button loadProfileBackButton;
    @FXML
    Button loadProfileLoadButton;
    @FXML
    Button loadProfileDeleteButton;

    // The main list that will store all our countries.
    private ArrayList<Profile> profiles = new ArrayList<>();

    public static final String PROFILES_DATA_FILE_LOCATION = "Rats/resources/SavedProfiles.txt";

    /**
     * Initialize the controller.
     * This method is called automatically. The following happen in this order:
     * 1) First an instance of the controller is created (the constructor is called),
     * 2) Next the @FXML variables are bound to the GUI components.
     * 3) Finally, this initializes method is called.
     * This means we cannot bind actions to the buttons in the constructor, but we can in this method.
     */

    public void initialize() {
        //placeholder for the loading of profiles from a proper textfile once implemented
        profiles.add(new Profile("Mo"));
        loadProfileBackButton.setOnAction(e -> handleLoadProfileBackButtonAction());

        refreshProfilesList();
    }

    /**
     * Refresh the list of profiles in view
     */
    private void refreshProfilesList() {
        // Clear the displayed list
        loadProfileList.getItems().clear();

        // Add each profile to the profiles list
        for (Profile p : profiles) {
            loadProfileList.getItems().add(p.getProfileDescription());
        }
    }

    /**
     * close current window
     */
    public void handleLoadProfileBackButtonAction() {
        //set what the buttons do upon action from user, in this case closes the borderPane scene window
        Stage stage = (Stage) loadProfilePane.getScene().getWindow();
        stage.close();
    }

    /**
     * makes a new profile and adds it to a list of profiles
     *
     * @param profile the profile name
     */
    public void addProfile(String profile) {
        this.profiles.add(new Profile(profile));
    }

    /**
     * deletes a profile from a list of profiles
     *
     * @param profile the profile name
     */
    public void deleteProfile(String profile) {
        //TODO
    }

    /**
     * saves the list of profiles to a dedicated text file
     */
    public void saveProfileToFile() {
        try {
            FileWriter writer = new FileWriter(PROFILES_DATA_FILE_LOCATION, true);
            for (Profile p : profiles) {
                writer.write(p.getProfileDescription() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
