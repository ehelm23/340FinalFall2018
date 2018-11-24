// This will be our super class

public class Character {

    // Attributes
    public String name;
    public double attack;
    public double defense;
    public double speed;
    public double maxHP;

    public Character(double attack, double defense, double speed, double health){
        this.name = "";
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

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public double getAttack(){
        return this.attack;
    }
    public double getDefense(){
        return this.defense;
    }
    public double getSpeed(){
        return this.speed;
    }
    public double getHP(){
        return this.maxHP;
    }
    // Restore health

}
