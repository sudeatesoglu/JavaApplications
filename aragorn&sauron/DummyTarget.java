// PART 2
public class DummyTarget {
    private String name;
    private int health;

    public DummyTarget(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // cry method
    public void cry() {
        System.out.println("Why would you do this to me? :'[");
    }

    // get & set, and toString methods
    public String toString() {
        return "DummyTarget [name=" + name + ", health= " + health + "]";
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

}
