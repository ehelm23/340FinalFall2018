public class BattleRunner extends Thread
{

    boolean DEBUG = true;
    //Basic thread to run a simulated fight
    //Since the fight is run here, this thread also has the responsibility to initiate writing back the result
    public void run(Character redFighter, Character blueFighter, WarRunner hostingWarRunner, int soldierNumber) throws InterruptedException
    {

        FightLogic simul = new FightLogic();

        //Simulate a fight between the passed in fighters.
        char fightWinner = simul.simulateFight(redFighter, blueFighter);

        //Based on which fighter wins, report it back and adjust the team score.
        if (fightWinner == 'r')
        {
            if (DEBUG) System.out.printf("Red fighter has won battle %d. Health remaining is %f\n", soldierNumber, redFighter.getHP());
            hostingWarRunner.increaseScore(fightWinner, redFighter.getHP());
        }

        else if (fightWinner == 'b')
        {
            if (DEBUG) System.out.printf("Blue fighter has won battle %d. Health remaining is %f\n", soldierNumber, blueFighter.getHP());
            hostingWarRunner.increaseScore(fightWinner, blueFighter.getHP());
        }

        else { if (DEBUG) System.out.printf("An error has occurred in battle %d. Please debug!\n", soldierNumber); }
    }
}
