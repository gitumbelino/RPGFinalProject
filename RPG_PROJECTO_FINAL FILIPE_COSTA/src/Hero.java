import java.util.ArrayList;

public abstract class Hero extends Entity {
    private int level;
    private int gold;
    private MainWeapon weapon;
    private ArrayList<HeroItem> inventory;

    // Constructor
    public Hero(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength);
        this.level = level;
        this.gold = gold;
        this.inventory = new ArrayList<>();
    }

    // Abstract method that each hero type must implement
    public abstract boolean attack(NPC enemy);

    // Weapon management
    public void setWeapon(MainWeapon newWeapon) {
        if (this.weapon != null) {
            System.out.println("Replaced " + this.weapon.getName() + " with " + newWeapon.getName());
        } else {
            System.out.println("Equipped " + newWeapon.getName());
        }
        this.weapon = newWeapon;
    }

    public MainWeapon getWeapon() {
        return weapon;
    }

    // Inventory management
    public void addToInventory(HeroItem item) {
        inventory.add(item);
        System.out.println(item.getName() + " added to inventory");
    }

    public ArrayList<HeroItem> getInventory() {
        return inventory;
    }

    // Gold management
    public void addGold(int amount) {
        gold += amount;
        System.out.println("Gained " + amount + " gold. Total: " + gold);
    }

    public void reduceGold(int amount) {
        if (amount <= gold) {
            gold -= amount;
            System.out.println("Spent " + amount + " gold. Remaining: " + gold);
        } else {
            System.out.println("Not enough gold!");
        }
    }

    public int getGold() {
        return gold;
    }

    // Level management
    public void levelUp() {
        level++;
        System.out.println(getName() + " reached level " + level + "!");
    }

    public int getLevel() {
        return level;
    }

    // Status display
    @Override
    public void checkStatus() {
        super.checkStatus();
        System.out.println("Level: " + level);
        System.out.println("Gold: " + gold);
        System.out.println("Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("Inventory items: " + inventory.size());
    }

    // Strength management
    public void increaseStrength(int amount) {
        setStrength(getStrength() + amount);
        System.out.println("Strength increased by " + amount + "!");
    }

    public void decreaseStrength(int amount) {
        setStrength(getStrength() - amount);
        System.out.println("Strength decreased by " + amount);
    }
}