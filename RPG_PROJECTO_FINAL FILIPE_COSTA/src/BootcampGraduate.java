import java.util.Random;   // For random numbers in AI attack
import java.util.Scanner;  // For reading player input

public class BootcampGraduate extends Hero {

    // Constructor: initializes a new BootcampGraduate with basic stats
    public BootcampGraduate(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength, level, gold);  // Calls Hero constructor with these values
    }

    // Main combat method - returns true if hero wins, false if they lose
    @Override
    public boolean attack(NPC enemy) {
        Scanner scanner = new Scanner(System.in);  // Creates input reader
        boolean specialAttackUsed = false;         // Tracks if special attack was used (can only use once)

        // Combat continues while both fighters are alive
        while (this.getHp() > 0 && enemy.getHp() > 0) {
            // Display combat menu
            System.out.println("\nChoose your attack:");
            System.out.println("1. Stack Overflow Copy-Paste");
            if (!specialAttackUsed) {
                System.out.println("2. Framework Magic");    // Only show if not used yet
            }
            if (!getInventory().isEmpty()) {
                System.out.println("3. AI Prompt Engineer"); // Only show if have items
            }

            // Get player's attack choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Handle different attack types
            switch (choice) {
                case 1: // Basic attack - Stack Overflow Copy-Paste
                    int damage = this.getStrength();        // Base damage from strength
                    if (this.getWeapon() != null) {
                        damage += this.getWeapon().getAttack();  // Add weapon damage if have weapon
                    }
                    enemy.reduceHp(damage);                 // Apply damage to enemy
                    // Show attack messages
                    System.out.println("You frantically search Stack Overflow...");
                    System.out.println("Found a solution from 2014!");
                    System.out.println("It somehow works! Dealt " + damage + " damage!");
                    break;

                case 2: // Special attack - Framework Magic
                    if (!specialAttackUsed) {              // Check if available
                        int specialDamage = this.getStrength() * 2;  // Double damage
                        enemy.reduceHp(specialDamage);
                        specialAttackUsed = true;          // Mark as used
                        System.out.println("You summon the power of the latest JavaScript framework!");
                        System.out.println("'But can it scale?' You dealt " + specialDamage + " damage!");
                    } else {
                        System.out.println("You can't keep learning new frameworks - you're already at your limit!");
                        continue;                          // Skip back to attack choice
                    }
                    break;

                case 3: // Item attack - AI Prompt Engineer
                    if (!getInventory().isEmpty()) {
                        Random rand = new Random();
                        int aiRoll = rand.nextInt(3);      // Random number 0-2 for different outcomes
                        switch(aiRoll) {
                            case 0:  // Great success
                                System.out.println("The AI generates perfect, production-ready code!");
                                enemy.reduceHp(50);
                                break;
                            case 1:  // Minor success
                                System.out.println("AI: 'I apologize, but I don't write code. Here's a poem instead.'");
                                enemy.reduceHp(5);
                                break;
                            case 2:  // Backfire
                                System.out.println("AI hallucinates and gives you Assembly code instead of JavaScript");
                                this.reduceHp(10);         // Damage self instead
                                break;
                        }
                    }
                    break;

                default:  // Invalid choice handler
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }

            // Enemy's turn to attack (if still alive)
            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.getStrength();
                this.reduceHp(enemyDamage);
                System.out.println(enemy.getName() + " counter-attacks for " + enemyDamage + " damage!");
            }
        }

        // Return true if hero survived (hp > 0), false if died
        return this.getHp() > 0;
    }
}