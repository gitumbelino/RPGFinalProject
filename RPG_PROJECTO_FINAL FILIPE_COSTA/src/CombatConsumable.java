// Combat consumable items that deal instant damage
public class CombatConsumable extends Consumable {
    private int instantDamage;    // Amount of damage this item deals

    public CombatConsumable(String name, int price, int instantDamage) {
        super(name, price);
        this.instantDamage = instantDamage;
    }

    public int getInstantDamage() {
        return instantDamage;
    }

    @Override
    public void use(Hero hero) {
        // Implementation will handle instant damage effect
        System.out.println(name + " deals " + instantDamage + " instant damage!");
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Instant Damage: " + instantDamage);
    }
}

