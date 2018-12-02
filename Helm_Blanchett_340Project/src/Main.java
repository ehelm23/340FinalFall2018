import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Character> characterList;
    public static ArrayList<Integer> resultArray;

    //Semaphore for soldierSpans
    //

    /**
     * starts the game
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        Scene scene= new Scene(root, 600, 400);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {

       characterList = new ArrayList<>();
       resultArray = new ArrayList<>();

       //Character Fighter Tester

//       Character my1 = new Character("Charon",50,50,50,400);
//       Character my2 = new Character("RNzo",75,35,45,450);
//       chosenFighters = new Character[]{my1,my2};
//
//       FightLogic match = new FightLogic();
//
//       System.out.println("Testing fight function");
//
//       match.simulateFight(chosenFighters);
//
//       System.out.println("Finished testing function");

        //Testing Thread Joiners



       // Threads
     //  Respawn respawnRunner = new Respawn();
     //  respawnRunner.run();

       //Troop troopRunner = new Troop();
       //troopRunner.run();

        launch(args);
    }

}