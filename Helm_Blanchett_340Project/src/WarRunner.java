import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class WarRunner extends Thread {

    int blueScore;
    int redScore;
    int roundNumber = 1;

    int killScore = 50;
    int roundBonus = 10;
    int healthMod = 10;
    int healthBonus = 5;

    boolean DEBUG = true;

    // Label
    @FXML
    public Label results;

    // TextFlow
    @FXML
            private TextFlow display;

    Semaphore scoreLock = new Semaphore(1, true);

    public void run(ArrayList<Character> redTeam, ArrayList<Character> blueTeam) throws InterruptedException {

        // (Erik) Let's test if my ArrayList's are properly initialized.
        System.out.println("Printing the Array Lists from inside the WarRunner thread");
        System.out.println(redTeam);
        System.out.println(blueTeam);

        this.blueScore = 0;
        this.redScore = 0;
        this.roundNumber = 1;

        //Test if the fighting actually works. (it does)
        // fightSim = new FightLogic();
        // fightSim.simulateFight(redTeam.get(0),blueTeam.get(0));
        // System.out.printf("Now back in the main function, Red: %f HP has fought Blue: %f HP",redTeam.get(0).getHP(),blueTeam.get(0).getHP());

        //Logical Overview:

 /*               While (RedTeam || BlueTeam > 0)
                {

                        Get Smallest team size

                        Run fight between opposing members of team (0 vs 0), (1 vs 1), etc. (in new thread) (up to smallest team size)
                        (fightThread)

                                (wait for all to join)

                        Remove all chracter with HP <= 0
                }

                Check which team won (if RedTeam > 0)
*/
        //If an army still has fighters, then the war isn't over!
        while ((redTeam.size() > 0) && (blueTeam.size() > 0))
        {
            int numfights;
            //Get the smaller team size and use it as the number of fights/threads to initialize
            if (redTeam.size() < blueTeam.size()) { numfights = redTeam.size();}
            else {numfights = blueTeam.size();}

            //Create an array to store the threads that are created so that they can be joined.
            BattleRunner[] battleThreads = new BattleRunner[numfights];

            //start up threads that will run fights between the troop members, modifying team score and adjusting character healths
            for (int i = 0; i < numfights; i++) {
                battleThreads[i] = new BattleRunner();
                battleThreads[i].run(redTeam.get(i), blueTeam.get(i), this, i);
                //   System.out.printf("WarRunner Thread has spawned off threads to handle battle No. %d in round %d\n",i,roundNumber);
            }

            //Wait for all the threads to finish their operations
            for (int i = 0; i < numfights; i++) {
                battleThreads[i].join();
                //  System.out.printf("Battle thread number %d has finished.\n",i);
            }

            //Test if the fights actually went through properly.
            //Print the current armies and their status.

            if (DEBUG)
            {
                System.out.println("This is a status check on the teams. ");


                    //display.setAccessibleText("This is working!\n");
                    System.out.printf("Red Team --- %d soldiers remain -----\n", redTeam.size());
                    for (Character fighter : redTeam) {
                        System.out.printf("Red Team Soldier, HP: %f\n", fighter.getHP());
                    }

                    System.out.printf("Blue Team: --- %d soldiers remain -----\n", blueTeam.size());
                    for (Character fighter : blueTeam) {
                        System.out.printf("Blue Team Soldier, HP: %f\n", fighter.getHP());
                    }

                    System.out.printf("Team scores: Red vs. Blue, %d to %d\n", redScore, blueScore);

                }

            //Now that one round has passed, set up for the next one. First increment the round number
            this.roundNumber++;

            //Now take out any fighter of any team that has been defeated

            //Iterator advice taken from: https://stackoverflow.com/questions/18448671/how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr
            // https://codereview.stackexchange.com/questions/64011/removing-elements-on-a-list-while-iterating-through-it

            Iterator<Character> sweeper = redTeam.iterator();

            while (sweeper.hasNext()) {
                Character fighter = sweeper.next();

                if (fighter.getHP() <= 0) {
                    sweeper.remove();
                }
            }

            sweeper = blueTeam.iterator();

            while (sweeper.hasNext()) {
                Character fighter = sweeper.next();

                if (fighter.getHP() <= 0) {
                    sweeper.remove();
                }
            }

            if (DEBUG) System.out.println("This is a status check on the teams after removal of defeated fighters. ");

            System.out.printf("Red Team --- %d soldiers remain -----\n",redTeam.size());
            if (DEBUG) {
                for (Character fighter : redTeam) {
                    System.out.printf("Red Team Soldier, HP: %f\n", fighter.getHP());
                }
            }

            System.out.printf("Blue Team: --- %d soldiers remain -----\n", blueTeam.size());
            if (DEBUG) {
                for (Character fighter : blueTeam) {
                    System.out.printf("Blue Team Soldier, HP: %f\n", fighter.getHP());
                }
            }

        }

        //The while loop has ended, meaning that one of the teams has been completely defeated.
        //results.setText("The war has concluded, with a final score of ");
        System.out.printf("The war has concluded, with a final score of %d (Red) to %d (Blue)\n",redScore,blueScore);

    }

    //A function to increase the team scores in the simulation. Itended to be called from the BattleRunner threads
    //Implements concurrency through a "mutex" semaphore

    //Parameters can be tweaked in the WarRunner variables.
    public void increaseScore(char team, double healthRemain) throws InterruptedException {
        if (team == 'r') {
            this.scoreLock.acquire();
//                        System.out.println("Thread has aquired scorelock");

//                        System.out.printf("Score of %d",redScore);
            //Calculate increment score for a kill
            this.redScore += killScore;
//                        System.out.printf("has been incremented to %d\n",redScore);

//                        System.out.printf("Score of %d",redScore);
            //bonus for round
            this.redScore += (roundBonus * roundNumber);
//                        System.out.printf("has been incremented to %d\n",redScore);


//                        System.out.printf("Score of %d",redScore);
            //Bonus for health remaining
            this.redScore += ((healthRemain % healthMod) * healthBonus);
//                        System.out.printf("has been incremented to %d\n",redScore);


            this.scoreLock.release();
//                        System.out.println("Thread has released scoreLock");
        } else if (team == 'b') {
            this.scoreLock.acquire();
//                        System.out.println("Thread has aquired scorelock");
            //Calculate increment score for a kill

//                        System.out.printf("Score of %d",blueScore);
            this.blueScore += killScore;
//                        System.out.printf("has been incremented to %d\n",blueScore);

//                        System.out.printf("Score of %d",blueScore);
            //bonus for round
            this.blueScore += (roundBonus * roundNumber);
//                        System.out.printf("has been incremented to %d\n",blueScore);

//                        System.out.printf("Score of %d",blueScore);
            //Bonus for health remaining
            this.blueScore += ((healthRemain / healthMod) * healthBonus);
            //System.out.printf("has been incremented to %d\n",blueScore);

            this.scoreLock.release();
            //  System.out.println("Thread has released scoreLock");
        }
    }


}
