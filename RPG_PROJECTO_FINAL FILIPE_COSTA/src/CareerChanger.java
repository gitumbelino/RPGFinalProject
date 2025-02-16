import java.util.Random;   // For random effects in attacks
import java.util.Scanner;  // For reading player input

public class CareerChanger extends Hero {

    // Constructor: creates a CareerChanger with starting stats
    public CareerChanger(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength, level, gold);  // Pass values to Hero constructor
    }

    @Override
    public boolean attack(NPC enemy) {
        Scanner scanner = new Scanner(System.in);         // For reading player choices
        boolean specialAttackUsed = false;               // Track if network attack was used
        Random rand = new Random();                      // For random attack effects

        // Combat loop - continues while both fighter are alive
        while (this.getHp() > 0 && enemy.getHp() > 0) {
            // Display combat menu
            System.out.println("\nChoose your attack:");
            System.out.println("1. Soft Skills Slash");
            if (!specialAttackUsed) {
                System.out.println("2. Professional Network Strike");  // Only if not used yet
            }
            if (!getInventory().isEmpty()) {
                System.out.println("3. LinkedIn Recommendation Blast"); // Only if have items
            }

            // Get player's choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Soft Skills Slash - Basic attack with chance to charm
                    int damage = this.getStrength();
                    if (this.getWeapon() != null) {
                        damage += this.getWeapon().getAttack();  // Add weapon damage
                    }
                    // 20% chance to charm enemy and reduce their strength
                    boolean charmed = rand.nextInt(100) < 20;
                    enemy.reduceHp(damage);
                    System.out.println("You demonstrate your excellent people skills!");
                    System.out.println("Dealt " + damage + " damage!");
                    if (charmed) {
                        System.out.println("The enemy is impressed by your communication skills!");
                        enemy.setStrength(enemy.getStrength() - 2);  // Reduce enemy strength
                    }
                    break;

                case 2: // Professional Network Strike - Special attack that also heals
                    if (!specialAttackUsed) {
                        int specialDamage = this.getStrength() * 2;  // Double damage
                        enemy.reduceHp(specialDamage);
                        int healAmount = 15;                         // Heal from mentorship
                        this.increaseHp(healAmount);
                        specialAttackUsed = true;                    // Mark as used
                        System.out.println("You activate your professional network!");
                        System.out.println("A former colleague vouches for you! Dealt " + specialDamage + " damage!");
                        System.out.println("Their mentorship heals you for " + healAmount + " HP!");
                    } else {
                        System.out.println("You've already used your network this battle!");
                        continue;                                    // Return to attack choice
                    }
                    break;

                case 3: // LinkedIn Recommendation Blast - Random recommendation effects
                    if (!getInventory().isEmpty()) {
                        int recommendationRoll = rand.nextInt(3);   // Random 0-2 for effect
                        switch(recommendationRoll) {
                            case 0:  // Best outcome - high damage
                                System.out.println("A former boss writes a glowing recommendation!");
                                System.out.println("'Best career changer I've ever worked with!'");
                                enemy.reduceHp(40);
                                break;
                            case 1:  // Worst outcome - low damage
                                System.out.println("Someone endorsed you for Microsoft Word...");
                                System.out.println("Well, it's something...");
                                enemy.reduceHp(10);
                                break;
                            case 2:  // Medium outcome - average damage
                                System.out.println("Your old colleague writes 'Great team player!'");
                                System.out.println("Generic but effective!");
                                enemy.reduceHp(25);
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
                System.out.println(enemy.getName() + " questions your lack of traditional CS degree for " + enemyDamage + " damage!");
            }
        }

        // Return true if hero survived (hp > 0), false if died
        return this.getHp() > 0;
    }
}