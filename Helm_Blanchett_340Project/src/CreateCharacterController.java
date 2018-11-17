import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateCharacterController {

    // Buttons
    @FXML
    private javafx.scene.control.Button cancelButton;
    @FXML
    private javafx.scene.control.Button saveButton;
    @FXML
    private javafx.scene.control.Button doneButton;

    // TextFields
    @FXML
    private javafx.scene.control.TextField charName;
    @FXML
    private javafx.scene.control.TextField charAttack;
    @FXML
    private javafx.scene.control.TextField charDefense;
    @FXML
    private javafx.scene.control.TextField charSpeed;
    @FXML
    private javafx.scene.control.TextField charHP;

    // Sends the user back to the main screen stage.
    public void pressFight(ActionEvent event) throws Exception {
        try {
            cancelButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
