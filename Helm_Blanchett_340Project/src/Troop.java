import java.util.ArrayList;

public class Troop extends Thread {

         //public ArrayList<Character> redTeam = new ArrayList<>();




        public void run(ArrayList<Character> redTeam, ArrayList<Character> blueTeam)
        {
                // (Erik) Let's test if my ArrayList's are properly initialized.
                System.out.println("Printing the Array Lists from inside the Troop thread");
                System.out.println(redTeam);
                System.out.println(blueTeam);

                int blueScore;
                int RedScore;


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

        }




}
