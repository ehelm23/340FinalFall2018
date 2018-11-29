import java.util.Random;

public class FightLogic
{
    //How many turns in a row a character can attack before becoming "fatigued" and taking a penalty
    int fatigueLimit = 3;
    Random dice = new Random();

    //General fight logic and simulation for a single fight
    public void simulateFight(Character[] fighterArray)
    {
        Character[] fighters = fighterArray;

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

            double damage;
            if (rolledNumber < rollPoint)
            {
                //The faster fighter is attacking

                fighters[fasterFighter].increaseFatigue();
                fighters[otherFighter].resetFatigue();
                lastAttack = fasterFighter;

                damage = determineDmg(fighters[fasterFighter],fighters[otherFighter]);
                fighters[otherFighter].modifyHealth(damage * -1);
            }

            else
            {
                //the slower fighter won the dice roll and is attacking.

                fighters[otherFighter].increaseFatigue();
                fighters[fasterFighter].resetFatigue();
                lastAttack = otherFighter;

                damage = determineDmg(fighters[otherFighter],fighters[fasterFighter]);
                fighters[fasterFighter].modifyHealth(damage * -1);
            }

            //Now that damage has been dealt see if someone was defeated
            if ((fighters[0].getHP() <= 0) || (fighters[1].getHP() <= 0))
            {
                //Figure out which fighter won.
                if (fighters[0].getHP() <= 0)
                {
                    //The first fighter won.

                    //Locking Variable
                    //Write 0 into the result list.
                    //Unlock Variable
                }
                else
                {
                    //One of them is under 0 and if it isn't the first fighter, its the second

                    //Lock
                    //Write
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
