import java.util.ArrayList;
import java.util.Random;
import Enums.VendorType;


public class Vendor extends NPC {
    private ArrayList<HeroItem> shop;
    private Random rnd = new Random();
    private VendorType type;



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
            if(rnd.nextDouble() < chance) {
                shop.add(potion);
            }
        }
    }

    private ArrayList<HeroItem> getRandomItems(int count) {
        ArrayList<HeroItem> displayItems = new ArrayList<>();

        // If shop has less items than requested, return all
        if (shop.size() <= count) {
            return new ArrayList<>(shop);
        }

        // Get random items
        while (displayItems.size() < count) {
            int index = rnd.nextInt(shop.size());
            HeroItem item = shop.get(index);
            if (!displayItems.contains(item)) {
                displayItems.add(item);
            }
        }
        return displayItems;
    }

    private void addWeaponsToShop(double chance) {
        for(MainWeapon weapon : ItemFactory.createWeapons()) {
            if(rnd.nextDouble() < chance) {
                shop.add(weapon);
            }
        }
    }

    private void addCombatItemsToShop(double chance) {
        for(CombatConsumable item : ItemFactory.createCombatItems()) {
            if(rnd.nextDouble() < chance) {
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
    public void sell(Hero buyer, int itemIndex) {
        if (itemIndex < 0 || itemIndex >= shop.size()) {
            System.out.println("Invalid item selection!");
            return;
        }

        HeroItem item = shop.get(itemIndex);

        if (buyer.getGold() >= item.getPrice()) {
            if (item instanceof MainWeapon) {
                buyer.setWeapon((MainWeapon) item);
            } else if (item instanceof Consumable) {
                buyer.addToInventory((Consumable) item);
            }

            buyer.reduceGold(item.getPrice());
            System.out.println("Purchased " + item.getName() + "!");
        } else {
            System.out.println("Not enough gold!");
        }
    }

}