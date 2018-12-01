package Threads;

import java.util.ArrayList;

public class Troop extends Thread {

        Character redSoldier;
        Character blueSoldier;

        boolean battleComplete = false;

        public void run()
        {

//            while (!battleComplete)
//                {
//
//                    //Make sure that soldiers are loaded in and ready to fight
//                    //Sem_Wait(FightReady)
//
//                    //The characters are loaded and ready, so simulate a fight between them
//                    //Returns the winner of the fight
//
//                    //Based on winner, check the number of respawns (or if one is available)
//
//                        //If respawn is avaiable, then unlock the reswpawner thread
//                        // Sem_Signal(RespawnReady)
//
//                        //If there's no respawns available for defeated character, then the battle is over
//
//                }

        }

        public void spawnRedSoldier(Character reinforce)
        {
            this.redSoldier = reinforce;
        }

        public void spawnBlueSoldier(Character reinforce)
        {
            this.blueSoldier = reinforce;
        }



}
