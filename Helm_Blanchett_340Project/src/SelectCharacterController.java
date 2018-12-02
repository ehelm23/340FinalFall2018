import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.*;

public class SelectCharacterController {


    // Buttons
    @FXML
    private javafx.scene.control.Button cancelButton;

    @FXML
    private javafx.scene.control.Button nextButton;

    // ChoiceBox
    @FXML
    public ChoiceBox redFighter;

    @FXML
    public ChoiceBox blueFighter;

    // TextFields
//    @FXML
//    public javafx.scene.control.TextField numSpawns;
    @FXML
    public javafx.scene.control.TextField numTroops;

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

    public void initialize() {

        int numChars = Main.characterList.size();

        fighterList = FXCollections.observableArrayList();

        for (int i = 0; i < numChars; i++) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFight(ActionEvent event) throws Exception {
        try {
            // (Erik) Let's grab the number of troops
            int troops = Integer.parseInt(numTroops.getText());
            System.out.println("Troops = " + troops);

            // (Erik) Let's initialize our arrayLists
            ArrayList<Character> redTeam = new ArrayList<>();
            ArrayList<Character> blueTeam = new ArrayList<>();

            // (Erik) Initialize the teams.
            for (int i = 0; i < troops; i++) {
                redTeam.add(((Character) redFighter.getValue()).dupCharacter());
                blueTeam.add(((Character) blueFighter.getValue()).dupCharacter());
            }
            // (Erik) Let's test if my ArrayList's are properly initialized.
            System.out.println(redTeam);
            System.out.println(blueTeam);

            // (Erik) Let's test if the stats are all correct.
            System.out.println("Red Team's Name/Attk/Def/Spd/HP");
            System.out.println(redTeam.get(0).getName());
            System.out.println(redTeam.get(0).getAttack());
            System.out.println(redTeam.get(0).getDefense());
            System.out.println(redTeam.get(0).getSpeed());
            System.out.println(redTeam.get(0).getHP());
            //System.out.printf("Selected %s to fight %s", Main.chosenFighters[0].getName(),Main.chosenFighters[1].getName());

            //(Erik) Let's call our troop thread
            //(WB) This handles all the battles and operations with the characters selected
            WarRunner warRunnerRunner = new WarRunner();
            warRunnerRunner.run(redTeam, blueTeam);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
