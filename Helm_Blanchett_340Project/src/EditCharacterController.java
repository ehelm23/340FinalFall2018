import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.*;

public class EditCharacterController {

    // Buttons
    @FXML
    private javafx.scene.control.Button cancelButton;

    // ChoiceBox
    @FXML
    private ChoiceBox charBox;
    public void initialize(){
        charBox.getItems().add("Test");
    }


    // Sends the user back to the main stage.
    public void pressCancel(ActionEvent event) throws Exception {
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
