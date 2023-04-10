// PART 3
public class Arena {
    private int maxTurns;

    public Arena(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    // get & set
    public int getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    // startBattle method
    public String startBattle(Warrior w, DummyTarget d) {
        int turns = 1;

        while (turns < maxTurns && d.getHealth() > 0) {
            int damage = w.slash();
            d.setHealth(d.getHealth() - damage);
            System.out.println(w.getName() + " uses slash, " + d.getName() + " took " + damage + " damage!");
            d.cry();
            System.out.println(w);
            System.out.println(d);
            turns++;
        }
        if (d.getHealth() <= 0) {
            return "YOU DIED";
        } else { // if maxTurns has been reached the top
            return "Maximum turns reached.";
        }
    }

    // main method
    public static void main(String[] args) {
        Warrior w = new Warrior("Aragorn", 30, 5, 5);
        DummyTarget d = new DummyTarget("Sauron", 40);
        Arena arena = new Arena(6);
        System.out.println(arena.startBattle(w, d));
    }
}
