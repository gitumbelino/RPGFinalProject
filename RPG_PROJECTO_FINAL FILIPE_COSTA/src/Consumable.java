// Abstract class for consumable items (potions, combat items)
public abstract class Consumable extends HeroItem {

    // Constructor passes name and price to parent
    public Consumable(String name, int price) {
        super(name, price);
    }

    // Abstract method that subclasses must implement
    public abstract void use(Hero hero);
}