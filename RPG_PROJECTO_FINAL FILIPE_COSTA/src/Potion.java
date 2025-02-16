// Potions that can heal HP and boost strength
public class Potion extends Consumable {
    private int healAmount;      // Amount of HP restored
    private int strengthBoost;   // Amount of strength increased

    public Potion(String name, int price, int healAmount, int strengthBoost) {
        super(name, price);
        this.healAmount = healAmount;
        this.strengthBoost = strengthBoost;
    }

    @Override
    public void use(Hero hero) {
        if (healAmount > 0) {
            hero.increaseHp(healAmount);
            System.out.println("Healed for " + healAmount + " HP!");
        }
        if (strengthBoost > 0) {
            hero.increaseStrength(strengthBoost);  // Now using the new method
        }
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Heal Amount: " + healAmount);
        System.out.println("Strength Boost: " + strengthBoost);
    }

    public int getHealAmount() {
        return healAmount;
    }

    public int getStrengthBoost() {
        return strengthBoost;
    }
}