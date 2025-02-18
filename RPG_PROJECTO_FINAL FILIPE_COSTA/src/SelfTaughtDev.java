import java.util.Random;   // For random attack outcomes
import java.util.Scanner;  // For player input

public class SelfTaughtDev extends Hero {

    public SelfTaughtDev(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength, level, gold);
    }

    @Override
    public boolean attack(NPC enemy) {
        Scanner scanner = new Scanner(System.in);
        boolean specialAttackUsed = false;    // Track 3 AM debugging usage
        Random rand = new Random();           // For random attack outcomes

        while (this.getHp() > 0 && enemy.getHp() > 0) {
            // Combat menu
            System.out.println("\nChoose your attack:");
            System.out.println("1. Commit & Pray");
            if (!specialAttackUsed) {
                System.out.println("2. 3 AM Debugging Fury");
            }
            if (!getInventory().isEmpty()) {  // Changed from inventory.isEmpty()
                System.out.println("3. YouTube Tutorial Roulette");
            }

            // Get player choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Commit & Pray - 50-50 chance of success/failure
                    int damage = this.getStrength();
                    if (this.getWeapon() != null) {
                        damage += this.getWeapon().getAttack();
                    }
                    boolean commitSuccess = rand.nextBoolean();  // 50% chance
                    if (commitSuccess) {
                        damage *= 2;  // Double damage on successful commit
                        enemy.reduceHp(damage);
                        System.out.println("Git push successful! Critical hit!");
                        System.out.println("Dealt " + damage + " damage!");
                    } else {
                        System.out.println("Merge conflict! The attack backfires!");
                        this.reduceHp(damage/2);  // Damage self on failure
                    }

                    // Status display after hero's attack
                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();

                    break;

                case 2: // 3 AM Debugging - High damage but with a cost
                    if (!specialAttackUsed) {
                        int specialDamage = this.getStrength() * 3;
                        enemy.reduceHp(specialDamage);
                        specialAttackUsed = true;
                        System.out.println("You chug an energy drink and enter the debugging zone!");
                        System.out.println("Maximum focus achieved! Dealt " + specialDamage + " damage!");
                        System.out.println("But you'll crash later...");
                        this.reduceHp(5);  // Energy drink crash damage
                    } else {
                        System.out.println("You're too tired for another debugging session!");
                        continue;
                    }

                    // Status display after hero's attack
                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();

                    break;

                case 3: // YouTube Tutorial - Random tutorial effectiveness
                    if (!getInventory().isEmpty()) {  // Changed from inventory.isEmpty()
                        int tutorialRoll = rand.nextInt(3);  // 3 possible outcomes
                        switch(tutorialRoll) {
                            case 0:  // Best outcome - Indian YouTuber
                                System.out.println("You found an Indian YouTuber's perfect solution!");
                                System.out.println("'Hello frands! Today we solve this problem!'");
                                enemy.reduceHp(45);
                                break;
                            case 1:  // Worst outcome - Deprecated code
                                System.out.println("The tutorial uses deprecated methods...");
                                System.out.println("Warning: This approach is no longer supported");
                                this.reduceHp(15);
                                break;
                            case 2:  // Medium outcome - Old but working code
                                System.out.println("10-year-old video but the code still works!");
                                System.out.println("Old but gold!");
                                enemy.reduceHp(30);
                                break;
                        }
                    }

                    // Status display after hero's attack
                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();

                    break;

                default:  // Invalid input handler
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }

            // Enemy turn
            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.getStrength();
                this.reduceHp(enemyDamage);
                System.out.println(enemy.getName() + " questions your self-taught credentials for " + enemyDamage + " damage!");

                // Status display after enemy's attack
                System.out.println("\nHealth after enemy attack:");
                this.showHp();
                enemy.showHp();

            }
        }

        return this.getHp() > 0;  // Return true if survived
    }
}