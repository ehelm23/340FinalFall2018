import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.text.DecimalFormat;
import java.util.Random;


public class CreateCharacterController {

    // Buttons
    @FXML
    private javafx.scene.control.Button saveButton;
    @FXML
    private javafx.scene.control.Button doneButton;
    @FXML
    private javafx.scene.control.Button randomButton;

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

    // Labels
    @FXML
    private javafx.scene.control.Label displayChar;

    Random statGen = new Random();

    double maxAttk = 100;
    double minAttk = 45;
    double maxSpd = 100;
    double minSpd = 45;
    double maxDef = 100;
    double minDef = 45;

    double maxHP = 500;
    double minHP = 200;

    // Sends the user back to the main screen stage.
    public void pressFight(ActionEvent event) throws Exception {
        try {
            doneButton.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Creates and saves the Character.
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
            Main.characterList.add(temp);

            System.out.println("Character has been saved in the list");

            displayChar.setText(tempName + " has been created and saved!");

            charName.setText("");
            charAttack.setText("");
            charDefense.setText("");
            charSpeed.setText("");
            charHP.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Randomize the stats chosen for the fighter
    public void randomStats(ActionEvent event) throws Exception {

        try {
            double rngAttk = minAttk + (maxAttk - minAttk) * statGen.nextDouble();
            double rngDef = minDef + (maxDef - minDef) * statGen.nextDouble();
            double rngSpd = minDef + (maxDef - minDef) * statGen.nextDouble();
            double rngHP = minHP + (maxHP - minHP) * statGen.nextDouble();

            //Formatter (for nicer UX) referenced from https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
            DecimalFormat df = new DecimalFormat("#.##");

            charAttack.setText(df.format(rngAttk));
            charDefense.setText(df.format(rngDef));
            charSpeed.setText(df.format(rngSpd));
            charHP.setText(df.format(rngHP));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
