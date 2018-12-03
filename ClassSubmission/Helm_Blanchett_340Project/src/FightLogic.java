import java.text.DecimalFormat;
import java.util.Random;

public class FightLogic {
    //How many turns in a row a character can attack before becoming "fatigued" and taking a penalty
    int fatigueLimit = 3;
    int fatiguePenalty = 10;
    Random dice = new Random();
    boolean DEBUG = false;
    DecimalFormat df = new DecimalFormat("#.##");

    //Simulates a fight between two characters. Returns 'r' for red character victory, 'b' for blue character victory
    public char simulateFight(Character red, Character blue) {
        Character fasterFighter;
        Character slowerFighter;
        char fightWinner = 'u';

        if (red.getSpeed() > blue.getSpeed()) {
            fasterFighter = red;
            slowerFighter = blue;
        } else {
            fasterFighter = blue;
            slowerFighter = red;
        }

        boolean victory = false;

        while (!victory) {
            //Remember who last attacked so we can determine if fatigue points matter
            Character lastAttack = fasterFighter;

            //Determine the shift in roll balance (the point at which the roll favor shifts
            double rollPoint = 50;

            //Adjust the roll balance for speed difference.
            double spdDiff = Math.abs(fasterFighter.getSpeed() - slowerFighter.getSpeed());

            if (spdDiff > 25) {
                rollPoint += 25;
            } else {
                rollPoint += spdDiff;
            }

            //Adjust for fatigue difference.
            //Fatigue takes effect if a character has attacked the last few times in a row
            if (lastAttack.getFatiguePoints() >= fatigueLimit) {
                //System.out.println("ALERT ---- FATIGUE PENALTY ------");
                rollPoint -= fatiguePenalty;
            }

            //Now the rolling point has been set. If a random number generated is smaller, then the faster fighter attacks
            //Else the slower fighter attacks.

            //Referenced random bounding from https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
            double rolledNumber = 1 + 99 * dice.nextDouble();

            double damage;

            if (rolledNumber < rollPoint) {
                //The faster fighter is attacking

                fasterFighter.increaseFatigue();
                slowerFighter.resetFatigue();
                lastAttack = fasterFighter;

                //Damage returns as a positive number.
                damage = determineDmg(fasterFighter, slowerFighter);
                slowerFighter.modifyHealth(damage * -1);

                if (DEBUG) {
                    System.out.printf("%s attacked and dealt %s points of damage.\n", fasterFighter.getName(), df.format(damage));
                }
            } else {
                //the slower fighter won the dice roll and is attacking.
                slowerFighter.increaseFatigue();
                fasterFighter.resetFatigue();
                lastAttack = slowerFighter;

                damage = determineDmg(slowerFighter, fasterFighter);
                fasterFighter.modifyHealth(damage * -1);

                if (DEBUG) {
                    System.out.printf("%s attacked and dealt %s points of damage.\n", slowerFighter.getName(), df.format(damage));
                }
            }

            if (DEBUG) {
                System.out.printf("%s - HP: %s.\n %s - HP: %s\n", red.getName(), df.format(red.getHP()), blue.getName(), df.format(blue.getHP()));
            }

            //Now that damage has been dealt see if someone was defeated
            if ((red.getHP() <= 0) || (blue.getHP() <= 0)) {
                victory = true;
                //Figure out which fighter won.
                if (red.getHP() <= 0) {
                    //If red's health is below zero, then Blue wins.
                    System.out.printf("%s has won the fight with %s HP remaining. \n", blue.getName(), df.format(blue.getHP()));
                    fightWinner = 'b';

                    //Locking Variable
                    //Write 0 into the result list.
                    // Main.resultArray.add(1);
                    //Unlock Variable
                } else {
                    //If blue's health is below zero, then red wins
                    System.out.printf("%s has won the fight with %s HP remaining. \n", red.getName(), df.format(red.getHP()));
                    fightWinner = 'r';
                    //One of them is under 0 and if it isn't the first fighter, its the second

                    //Lock
                    //Write
                    //Main.resultArray.add(0);
                    //Unlock
                }
            }

        }
        return fightWinner;
    }

    public double determineDmg(Character attacker, Character defender) {
        double attackMin = attacker.getAttack() * .75;
        double attackMax = attacker.getAttack() * 1.25;


        double attackValue = attackMin + (attackMax - attackMin) * dice.nextDouble();

        double defMin = defender.getDefense() * .50;
        double defMax = defender.getDefense() * .95;

        double defenseValue = defMin + (defMax - defMin) * dice.nextDouble();

        return attackValue - defenseValue;

    }
}
