import java.util.ArrayList;
import java.util.Random;

public class Vendor extends NPC {
    private ArrayList<HeroItem> shop;
    private Random rand = new Random();
    private VendorType type;

    public enum VendorType {
        COFFEE_SHOP("Need a coding boost? I've got the strongest coffee in tech!"),
        TECH_STORE("Latest tech tools and gadgets! Perfect for your next interview!"),
        BOOTCAMP_VENDOR("Get your learning resources here! Special discount for career changers!");

        private final String greeting;

        VendorType(String greeting) {
            this.greeting = greeting;
        }

        public String getGreeting() {
            return greeting;
        }
    }

    public Vendor(String name, int maxHp, int strength, int gold, VendorType type) {
        super(name, maxHp, strength, gold);
        this.type = type;
        this.shop = new ArrayList<>();
        stockShop();
    }

    private void stockShop() {
        switch(type) {
            case COFFEE_SHOP:
                // More potions, fewer weapons
                addPotionsToShop(0.6);
                addWeaponsToShop(0.3);
                addCombatItemsToShop(0.3);
                break;
            case TECH_STORE:
                // More weapons, fewer potions
                addPotionsToShop(0.3);
                addWeaponsToShop(0.7);
                addCombatItemsToShop(0.5);
                break;
            case BOOTCAMP_VENDOR:
                // Balanced inventory
                addPotionsToShop(0.5);
                addWeaponsToShop(0.5);
                addCombatItemsToShop(0.5);
                break;
        }
    }

    private void addPotionsToShop(double chance) {
        for(Potion potion : ItemFactory.createPotions()) {
            if(rand.nextDouble() < chance) {
                shop.add(potion);
            }
        }
    }

    private void addWeaponsToShop(double chance) {
        for(MainWeapon weapon : ItemFactory.createWeapons()) {
            if(rand.nextDouble() < chance) {
                shop.add(weapon);
            }
        }
    }

    private void addCombatItemsToShop(double chance) {
        for(CombatConsumable item : ItemFactory.createCombatItems()) {
            if(rand.nextDouble() < chance) {
                shop.add(item);
            }
        }
    }

    public void greet() {
        System.out.println("\n" + type.getGreeting());
    }

    // Rest of your existing methods remain the same...
    public void addItem(HeroItem item) {
        shop.add(item);
    }

    public void showShop() {
        greet();  // Add greeting when showing shop
        ArrayList<HeroItem> displayItems = getRandomItems(10);

        System.out.println("\n=== " + getName() + "'s Shop ===");
        int index = 1;
        for (HeroItem item : displayItems) {
            System.out.print(index++ + ". ");
            item.showDetails();
            System.out.println("---------------");
        }
    }

    // Your existing getRandomItems and sell methods remain the same...
}