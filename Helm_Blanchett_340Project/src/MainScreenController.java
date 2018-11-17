import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScreenController {
    // Buttons
    @FXML
    private javafx.scene.control.Button fightButton;
    @FXML
    private javafx.scene.control.Button characterButton;

    // Sends the user to the choose character stage.
    public void pressFight(ActionEvent event) throws Exception {
        try {
            fightButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCharacter.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    // Sends the user to the character creation stage.
    public void pressCharacter(ActionEvent event) throws Exception {
        try {
            characterButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCharacter.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
