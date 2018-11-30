import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EditCharacterController {


    // Buttons
    @FXML
    private javafx.scene.control.Button cancelButton;

    @FXML
    private javafx.scene.control.Button nextButton;

    // ChoiceBox
    @FXML
    private ChoiceBox redFighter;

    @FXML
    private ChoiceBox blueFighter;

    // TextFields
    @FXML
    private TextField numSpawns;
    @FXML
    private TextField numTroops;

    ObservableList<Character> fighterList;

    public StringConverter<Character> charConverter = new StringConverter<Character>() {
        @Override
        public String toString(Character object) {
            return object.getName();
        }

        @Override
        public Character fromString(String string) {
            return null;
        }
    };

    public void initialize(){

        int numChars = Main.characterList.size();

        fighterList = FXCollections.observableArrayList();

        for (int i = 0; i < numChars; i++)
        {
            fighterList.add(Main.characterList.get(i));
            // blueFighter.getItems().add(Main.characterList.get(i).getName());
            // redFighter.getItems().add(Main.characterList.get(i).getName());
        }

        redFighter.setItems(fighterList);
        blueFighter.setItems(fighterList);

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

    public void startFight(ActionEvent event) throws Exception
    {
        try
        {
            // Take input from TextFields
            int spawns = Integer.getInteger(numSpawns.getText());
            int troops = Integer.getInteger(numTroops.getText());
             Main.chosenFighters[0] = (Character) redFighter.getValue();
             Main.chosenFighters[1] = (Character) blueFighter.getValue();

             System.out.printf("Selected %s to fight %s", Main.chosenFighters[0].getName(),Main.chosenFighters[1].getName());



        }

        catch(Exception e) {e.printStackTrace();}
    }


}
