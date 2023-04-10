import java.util.Random;

// PART 1
// global variables
public class Warrior {
    private String name;
    private int health;
    private int stamina;
    private int strength;
    private Random rand; // random rand variable which will be used in method and func also added

    // constrcuctor
    public Warrior(String name, int health, int stamina, int strength) {
        this.name = name;
        this.health = health;
        this.stamina = stamina;
        this.strength = strength;
        this.rand = new Random();
    }

    // slash method
    public int slash() {
        int randNum = rand.nextInt(1, 7);
        int damage = randNum;
        damage *= strength;
        stamina--;

        if (stamina <= 0) {
            meditate();
        }

        return damage;
    }

    // meditate func
    public void meditate() {
        int randNum2 = rand.nextInt(1, 4);
        stamina += randNum2; // new stamina increased with random number
    }

    // get & set, and toString methods
    public String toString() {
        return "Warrior [name=" + name + ", health=" + health + ", stamina=" + stamina + ", strength=" + strength + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

}
