import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;


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
    public void pressSave(ActionEvent event) throws Exception {
        try {
            String tempName = charName.getText();
            Double tempAttack = Double.parseDouble(charAttack.getText());
            Double tempDefense = Double.parseDouble(charDefense.getText());
            Double tempSpeed = Double.parseDouble(charSpeed.getText());
            Double tempHP = Double.parseDouble(charHP.getText());
            Character temp = new Character(tempAttack, tempDefense, tempSpeed, tempHP);
            temp.setName(tempName);
            System.out.println("Attack = " + temp.getAttack());
            System.out.println("Name = " + temp.getName());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
