// This will be our super class

public class Character {

    // Attributes

    public double attack;
    public double defense;
    public double speed;
    public int maxHP;

    public Character(double attack, double defense, double speed, int health){
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxHP = health;
    }
    // Increase attack
    public void attackUp(double value){
        attack += value;
    }

    // Decrease attack
    public void attackDown(double value){
        attack -= value;
    }

    // Increase defense
    public void defenseUp(double value){
        defense += value;
    }

    // Decrease defense
    public void defenseDown(double value){
        defense -= value;
    }

    // Increase speed
    public void speedUp(double value){
        speed += value;
    }

    // Decrease speed
    public void speedDown(double value){
        speed -= value;
    }

    // Restore health

}
