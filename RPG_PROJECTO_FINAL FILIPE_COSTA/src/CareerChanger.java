import java.util.Random;   // For random effects in attacks
import java.util.Scanner;  // For reading player input


/**
 * há 3 tipos de herói possíveis:
 * bootcamp graduate
 * career changer
 * Self Taught Dev
 */

public class CareerChanger extends Hero {

    /**
     * @param name Nome
     * @param maxHp Vida máxima inicial
     * @param strength Força inicial
     * @param level Nível inicial
     * @param gold Quantidade inicial de ouro
     */

    // Constructor: creates a CareerChanger with starting stats
    public CareerChanger(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength, level, gold);  // Pass values to Hero constructor
    }

    /**
     * Metodo de ataque para o Career Changer.
     * três tipos de ataques diferentes:
     * 1. Soft Skills Slash (ataque normal)
     * 2. Professional Network Strike (ataque especial, uso único)
     * 3. LinkedIn Recommendation Blast (ataque com item, requer inventário)
     *
     * @param enemy O NPC inimigo a ser atacado
     * @return true se o herói vencer, false se perder
     */

    @Override
    public boolean attack(NPC enemy) {
        Scanner scanner = new Scanner(System.in);
        boolean specialAttackUsed = false;
        Random rand = new Random();

        while (this.getHp() > 0 && enemy.getHp() > 0) {

            System.out.println("\nChoose your attack:");
            System.out.println("1. Soft Skills Slash");
            if (!specialAttackUsed) {
                System.out.println("2. Professional Network Strike");  // Only if not used yet
            }
            if (!getInventory().isEmpty()) {
                System.out.println("3. LinkedIn Recommendation Blast"); // Only if have items
            }

            System.out.print("\nEnter your choice: ");
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

                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();

                    break;

                case 2: // Professional Network Strike, ataque especial que tbm cura
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
                        continue;
                    }

                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();

                    break;

                case 3: // LinkedIn Recommendation Blast
                    if (!getInventory().isEmpty()) {
                        int recommendationRoll = rand.nextInt(3);   // Random 0-2 for effect
                        switch(recommendationRoll) {
                            case 0:  // Melhor Hipotese, dano maximo
                                System.out.println("A former boss writes a glowing recommendation!");
                                System.out.println("'Best career changer I've ever worked with!'");
                                enemy.reduceHp(40);
                                break;
                            case 1:  // Pior Hipotese, dan minimo
                                System.out.println("Someone endorsed you for Microsoft Word...");
                                System.out.println("Well, it's something...");
                                enemy.reduceHp(10);
                                break;
                            case 2:  // Hipotese média  - dano intermedio
                                System.out.println("Your old colleague writes 'Great team player!'");
                                System.out.println("Generic but effective!");
                                enemy.reduceHp(25);
                                break;
                        }
                    }

                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }


            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.getStrength();
                this.reduceHp(enemyDamage);
                System.out.println(enemy.getName() + " questions your lack of traditional CS degree for " + enemyDamage + " damage!");


                System.out.println("\nHealth after enemy attack:");
                this.showHp();
                enemy.showHp();

            }
        }

        return this.getHp() > 0;
    }
}