import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Character> characterList;
  //  public static ArrayList<Integer> resultArray;

    //Semaphore for soldierSpans
    //

    /**
     * starts the game
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        Scene scene = new Scene(root, 600, 400);
        //scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        characterList = new ArrayList<>();
       // resultArray = new ArrayList<>();

        //Character Fighter Tester

       // Character my1 = new Character("Charon", 50, 50, 50, 400);
       // Character my2 = new Character("RNzo", 50, 50, 50, 400);
//       chosenFighters = new Character[]{my1,my2};
//
        // FightLogic match = new FightLogic();
//
//       System.out.println("Testing fight function");
//
//       match.simulateFight(chosenFighters);
//
//       System.out.println("Finished testing function");

        //Testing Thread Joiners


        // Threads
        //  BattleRunner respawnRunner = new BattleRunner();
        //  respawnRunner.run();

        //WarRunner troopRunner = new WarRunner();
        //troopRunner.run();

        launch(args);

//        System.out.println("Testing multiple fights to ensure randomness");
//
//        match.simulateFight(my1,my2);
//
//        my1 = new Character("Charon",50,50,50,400);
//        my2 = new Character("RNzo",50,55,50,400);
//
//        System.out.println("Remade characters, fighjting again");
//        match.simulateFight(my1,my2);
//
//         my1 = new Character("Charon",50,50,50,400);
//         my2 = new Character("RNzo",50,50,50,400);
//
//         System.out.println("Performing one more test fight");
//         match.simulateFight(my1,my2);

    }

}