package Threads;

public class Respawn extends Thread {

    Character reserve;
    int respawns;
    int respawnsUsed;


    public Respawn(Character inputReserve, int inputRespawns)
    {
        this.respawns = inputRespawns;
        this.reserve = inputReserve;
        this.respawnsUsed = 0;
    }


    public void run(){

        // SemWait(ReseveSpwn)

        //Troop.soldierRespawn
        System.out.println("Created thread for respawn function!");
    }
}
