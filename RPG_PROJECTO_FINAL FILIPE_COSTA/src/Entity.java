abstract class Entity {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int strength;

    public Entity(String name, int maxHp, int strength) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;      // Starting hp equals maxHp
        this.strength = strength;
    }

    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getHp() { return hp; }
    public int getStrength() { return strength; }

    // Add these three methods
    public void reduceHp(int amount) {
        this.hp = Math.max(0, this.hp - amount);
    }

    public void increaseHp(int amount) {
        this.hp = Math.min(maxHp, this.hp + amount);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void checkStatus() {
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Strength: " + strength);
    }
}