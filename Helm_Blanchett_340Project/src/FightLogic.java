import java.util.Random;

public class FightLogic
{
    //How many turns in a row a character can attack before becoming "fatigued" and taking a penalty
    int fatigueLimit = 3;
    int fatiguePenalty = 10;
    Random dice = new Random();
    boolean DEBUG = true;

    //Simulates a fight between two characters. Returns 'r' for red character victory, 'b' for blue character victory
    public char simulateFight(Character red, Character blue)
    {
        Character fasterFighter;
        Character slowerFighter;
        char fightWinner = 'u';

        if (red.getSpeed() > blue.getSpeed())
        {
           fasterFighter = red;
           slowerFighter = blue;
        }
        else
        {
            fasterFighter = blue;
            slowerFighter = red;
        }

        boolean victory = false;

        while (!victory)
        {
            //Remember who last attacked so we can determine if fatigue points matter
            Character lastAttack = fasterFighter;

            //Determine the shift in roll balance (the point at which the roll favor shifts
            double rollPoint = 50;

            //Adjust the roll balance for speed difference.
            double spdDiff = Math.abs(fasterFighter.getSpeed() - slowerFighter.getSpeed());

            if (spdDiff > 25){rollPoint += 25;}
            else {rollPoint += spdDiff;}

            //Adjust for fatigue difference.
            //Fatigue takes effect if a character has attacked the last few times in a row
            if (lastAttack.getFatiguePoints() >= fatigueLimit)
            {
                System.out.println("ALERT ---- FATIGUE PENALTY ------");
                rollPoint -= fatiguePenalty;
            }

            //Now the rolling point has been set. If a random number generated is smaller, then the faster fighter attacks
            //Else the slower fighter attacks.

            //Referenced random bounding from https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
            double rolledNumber = 1 + 99 * dice.nextDouble();

            double damage;

            if (rolledNumber < rollPoint)
            {
                //The faster fighter is attacking

                fasterFighter.increaseFatigue();
                slowerFighter.resetFatigue();
                lastAttack = fasterFighter;

                //Damage returns as a positive number.
                damage = determineDmg(fasterFighter,slowerFighter);
                slowerFighter.modifyHealth(damage * -1);

                if (DEBUG) {System.out.printf("%s attacked and dealt %f points of damage.\n", fasterFighter.getName(), damage);}
            }
            else
            {
                //the slower fighter won the dice roll and is attacking.
                slowerFighter.increaseFatigue();
                fasterFighter.resetFatigue();
                lastAttack = slowerFighter;

                damage = determineDmg(slowerFighter,fasterFighter);
                fasterFighter.modifyHealth(damage * -1);

                if (DEBUG) {System.out.printf("%s attacked and dealt %f points of damage.\n", slowerFighter.getName(), damage);}
            }

            if (DEBUG) {System.out.printf("%s - HP: %f.\n %s - HP: %f\n", red.getName(), red.getHP(), blue.getName(), blue.getHP());}

            //Now that damage has been dealt see if someone was defeated
            if ((red.getHP() <= 0) || (blue.getHP() <= 0))
            {
                victory = true;
                //Figure out which fighter won.
                if (red.getHP() <= 0)
                {
                    //If red's health is below zero, then Blue wins.
                    if (DEBUG) {System.out.printf("%s has won the fight with %f HP remaining. \n",blue.getName(),blue.getHP());}
                    fightWinner = 'b';

                    //Locking Variable
                    //Write 0 into the result list.
                   // Main.resultArray.add(1);
                    //Unlock Variable
                }
                else
                {
                    //If blue's health is below zero, then red wins
                    if (DEBUG) {System.out.printf("%s has won the fight with %f HP remaining. \n",red.getName(),red.getHP());}
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

    //General fight logic and simulation for a single fight
    public void simulateFight(Character[] fighterArray)
    {
        Character[] fighters = fighterArray;

        if (DEBUG) {System.out.printf("Starting fight between %s and %s\n", fighters[0].getName(),fighters[1].getName());}

        int fasterFighter;
        int otherFighter;

        if (fighters[0].getSpeed() > fighters[1].getSpeed())
        {
            fasterFighter = 0;
            otherFighter = 1;
        }
        else
        {
            otherFighter = 0;
            fasterFighter = 1;
        }

        boolean victory = false;

        while (!victory) {
            //Remember who last attacked so we can determine if fatigue points matter
            int lastAttack = fasterFighter;

            //Determine the shift in roll balance (the point at which the roll favor shifts
            double rollPoint = 50;

            //Adjust the roll balance for speed difference.
            double spdDiff = Math.abs(fighters[fasterFighter].getSpeed() - fighters[otherFighter].getSpeed());

            if (spdDiff > 25){rollPoint += 25;}
            else {rollPoint += spdDiff;}

            //Adjust for fatigue difference

            if (fighters[lastAttack].getFatiguePoints() > fatigueLimit)
            {rollPoint -= 10;}

            //Now the rolling point has been set. If a random number generated is smaller, then the faster fighter attacks
            //Else the slower fighter attacks.

            //Referenced random bounding from https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range
            double rolledNumber = 1 + 99 * dice.nextDouble();

            if (DEBUG) {System.out.printf("The tipping point is %f, the dice has rolled %f.\n", rollPoint, rolledNumber);}

            double damage;

            if (rolledNumber < rollPoint)
            {
                //The faster fighter is attacking

                fighters[fasterFighter].increaseFatigue();
                fighters[otherFighter].resetFatigue();
                lastAttack = fasterFighter;

                damage = determineDmg(fighters[fasterFighter],fighters[otherFighter]);
                fighters[otherFighter].modifyHealth(damage * -1);

                if (DEBUG) {System.out.printf("%s attacked and dealt %f points of damage.\n", fighters[fasterFighter].getName(), damage);}
            }

            else
            {
                //the slower fighter won the dice roll and is attacking.

                fighters[otherFighter].increaseFatigue();
                fighters[fasterFighter].resetFatigue();
                lastAttack = otherFighter;

                damage = determineDmg(fighters[otherFighter],fighters[fasterFighter]);
                fighters[fasterFighter].modifyHealth(damage * -1);

                if (DEBUG) {System.out.printf("%s attacked and dealt %f points of damage.\n", fighters[otherFighter].getName(), damage);}
            }

            if (DEBUG) {System.out.printf("%s - HP: %f.\n %s - HP: %f\n", fighters[0].getName(), fighters[0].getHP(),fighters[1].getName(), fighters[1].getHP());}

            //Now that damage has been dealt see if someone was defeated
            if ((fighters[0].getHP() <= 0) || (fighters[1].getHP() <= 0))
            {
                victory = true;
                //Figure out which fighter won.
                if (fighters[0].getHP() <= 0)
                {
                    if (DEBUG) {System.out.printf("%s has won the fight. \n",fighters[1].getName());}
                    //The first fighter won.

                    //Locking Variable
                    //Write 0 into the result list.
                    Main.resultArray.add(1);
                    //Unlock Variable
                }
                else
                {
                    if (DEBUG) {System.out.printf("%s has won the fight. \n",fighters[0].getName());}
                    //One of them is under 0 and if it isn't the first fighter, its the second

                    //Lock
                    //Write
                    Main.resultArray.add(0);
                    //Unlock
                }
            }



            } //One character has proven victorious. End fight loop

        // End of function (and hopefully thread)
        // Command thread to kill itself somehow?
        }


    public double determineDmg(Character attacker, Character defender)
    {
        double attackMin = attacker.getAttack() * .75;
        double attackMax = attacker.getAttack() * 1.25;


        double attackValue = attackMin + (attackMax - attackMin) * dice.nextDouble();

        double defMin = defender.getDefense() * .50;
        double defMax = defender.getDefense() * .95;

        double defenseValue = defMin + (defMax - defMin) * dice.nextDouble();

        return attackValue - defenseValue;

    }
}
