import java.util.Random;   // For random numbers in AI attack
import java.util.Scanner;  // For reading player input


/**
 * há 3 tipos de herói possíveis:
 * bootcamp graduate
 * career changer
 * Self Taught Dev
 */

public class BootcampGraduate extends Hero {

    /**
     * Construtor para criar o Bootcamp Graduate.
     *
     * @param name     Nome do herói
     * @param maxHp    Vida máxima inicial
     * @param strength Força inicial
     * @param level    Nível inicial
     * @param gold     Quantidade inicial de ouro
     */

    public BootcampGraduate(String name, int maxHp, int strength, int level, int gold) {
        super(name, maxHp, strength, level, gold);
    }

    /**
     * metodo de ataque do BootcampGraduate
     * três tipos de ataques diferentes:
     * 1. Stack Overflow Copy-Paste (ataque normal)
     * 2. Framework Magic (ataque especial, uso único)
     * 3. AI Prompt Engineer (ataque com item, requer inventário)
     *
     * @param enemy O NPC inimigo a ser atacado
     * @return true se o herói vencer, false se perder
     */

    @Override
    public boolean attack(NPC enemy) {
        Scanner scanner = new Scanner(System.in);
        boolean specialAttackUsed = false;

        // o combate dá-se até um deles perder
        while (this.getHp() > 0 && enemy.getHp() > 0) {

            // Menu do combate, onde se faz a escolha do ataque e se pode aceder ao inventário.
            System.out.println("\nChoose your attack:");
            System.out.println("1. Stack Overflow Copy-Paste");
            if (!specialAttackUsed) {
                System.out.println("2. Framework Magic");
            }
            if (!getInventory().isEmpty()) {
                System.out.println("3. AI Prompt Engineer");
            }
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Ataque normal - Stack Overflow Copy-Paste
                    int damage = this.getStrength();
                    if (this.getWeapon() != null) {
                        damage += this.getWeapon().getAttack();
                    }
                    enemy.reduceHp(damage);

                    System.out.println("You frantically search Stack Overflow...");
                    System.out.println("Found a solution from 2014!");
                    System.out.println("It somehow works! Dealt " + damage + " damage!");

                    // Status display after hero's attack
                    System.out.println("\nHealth after enemy attack:");
                    this.showHp();
                    enemy.showHp();
                    break;

                case 2:
                    // Ataque especial - Framework Magic
                    if (!specialAttackUsed) {
                        int specialDamage = this.getStrength() * 2;
                        enemy.reduceHp(specialDamage);
                        specialAttackUsed = true;
                        System.out.println("You summon the power of the latest JavaScript framework!");
                        System.out.println("'But can it scale?' You dealt " + specialDamage + " damage!");

                        // Status display after hero's special attack
                        System.out.println("\nHealth after enemy attack:");
                        this.showHp();
                        enemy.showHp();

                    } else {
                        System.out.println("You can't keep learning new frameworks - you're already at your limit!");
                        continue;
                    }
                    break;

                case 3:
                    // Ataque Consumível - AI Prompt Engineer
                    //com 3 niveis de ataque e efeito, sorteados aleatoriamente ao declarar o ataque:
                    //com sucesso, da todo o dano
                    //mais ou menos, mais para menos :D, dano muito reduzido
                    //dá uam soluçao errada e dá dano ao proprio heroi

                    if (!getInventory().isEmpty()) {
                        Random rand = new Random();
                        int aiRoll = rand.nextInt(3);
                        switch (aiRoll) {
                            case 0:
                                System.out.println("The AI generates perfect, production-ready code!");
                                enemy.reduceHp(50);
                                break;
                            case 1:
                                System.out.println("AI: 'I apologize, but I don't write code. Here's a poem instead.'");
                                enemy.reduceHp(5);
                                break;
                            case 2:
                                System.out.println("AI hallucinates and gives you Python instead of JavaScript");
                                this.reduceHp(10);
                                break;
                        }

                        // Status display after AI attack (regardless of outcome)
                        System.out.println("\nHealth after enemy attack:");
                        this.showHp();
                        enemy.showHp();
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
                    continue;
            }

            if (enemy.getHp() > 0) {
                int enemyDamage = enemy.getStrength();
                this.reduceHp(enemyDamage);
                System.out.println(enemy.getName() + " counter-attacks for " + enemyDamage + " damage!");

                // Status display after enemy's attack
                System.out.println("\nHealth after enemy attack:");
                this.showHp();
                enemy.showHp();
            }
        }

        return this.getHp() > 0;
    }
}