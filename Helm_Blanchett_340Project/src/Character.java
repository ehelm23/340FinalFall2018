// This will be our super class

public class Character {

    // Attributes
    private String name;
    private double attack;
    private double defense;
    private double speed;
    private double maxHP;
    private int fatiguePoints;

    public Character(double attack, double defense, double speed, double health){
        this.name = "";
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxHP = health;
        this.fatiguePoints = 0;
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

    //Modify health (Pass in negative to decrease, positive to increase)
    public void modifyHealth(double value){maxHP += value;}

    public void increaseFatigue(){fatiguePoints += 1;}

    public void resetFatigue(){fatiguePoints = 0;}

    //Get Variable values
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
    public int getFatiguePoints(){return this.fatiguePoints;}

    // Restore health

}
