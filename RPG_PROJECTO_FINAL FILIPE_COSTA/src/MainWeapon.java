// Class representing the main weapon a hero can equip
public class MainWeapon extends HeroItem {
    private int attack;          // Base attack power
    private int specialAttack;   // Special attack power (used for special moves)

    // Constructor sets name, price and attack values
    public MainWeapon(String name, int price, int attack, int specialAttack) {
        super(name, price);
        this.attack = attack;
        this.specialAttack = specialAttack;
    }

    // Getters for attack values
    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    // Override parent's showDetails to include attack values
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Attack: " + attack);
        System.out.println("Special Attack: " + specialAttack);
    }
}