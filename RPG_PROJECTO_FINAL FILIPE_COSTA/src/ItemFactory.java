public class ItemFactory {
    // Create weapons
    public static MainWeapon[] createWeapons() {
        return new MainWeapon[] {
                new MainWeapon("Mechanical Keyboard of Speed", 50, 15, 30),
                new MainWeapon("Premium GitHub Account", 75, 20, 40),
                new MainWeapon("Stack Overflow Premium", 100, 25, 50),
                new MainWeapon("Udemy Course Collection", 60, 18, 35),
                new MainWeapon("ChatGPT Premium", 90, 22, 45),
                new MainWeapon("Coding Bootcamp Certificate", 80, 21, 42),
                new MainWeapon("LinkedIn Premium", 70, 19, 38)
        };
    }

    // Create potions
    public static Potion[] createPotions() {
        return new Potion[] {
                new Potion("Coffee", 10, 20, 0),              // Heals 20 HP
                new Potion("Energy Drink", 15, 30, 2),        // Heals 30 HP, +2 strength
                new Potion("Weekend Tutorial", 25, 40, 3),    // Heals 40 HP, +3 strength
                new Potion("Documentation Read", 20, 25, 5),  // Heals 25 HP, +5 strength
                new Potion("Mentor's Advice", 30, 35, 4)      // Heals 35 HP, +4 strength
        };
    }

    // Create combat consumables
    public static CombatConsumable[] createCombatItems() {
        return new CombatConsumable[] {
                new CombatConsumable("Stack Overflow Answer", 30, 40),
                new CombatConsumable("Code Snippet", 20, 25),
                new CombatConsumable("Quick Documentation", 15, 20),
                new CombatConsumable("AI Generated Solution", 35, 45),
                new CombatConsumable("GitHub Copilot Suggestion", 25, 35)
        };
    }
}