import java.util.Scanner;
import java.util.ArrayList;


public class Game {
    private Scanner scanner = new Scanner(System.in);
    private GameMap gameMap;
    private Room currentRoom;


    // Constructor initializes game map and starting room
    public Game() {
        this.gameMap = new GameMap();
        this.currentRoom = gameMap.getStartingRoom();
    }


    // Character creation method
    public Hero createCharacter() {
        System.out.println("=== Create Your Job Seeker ===");
        System.out.println("Choose your class:");
        System.out.println("1. Bootcamp Graduate");
        System.out.println("2. Career Changer");
        System.out.println("3. Self-Taught Developer");

        System.out.print("Enter choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        System.out.println("\nChoose difficulty:");
        System.out.println("1. Easy (300 points)");
        System.out.println("2. Hard (220 points)");
        int difficulty = scanner.nextInt();

        int points = (difficulty == 1) ? 300 : 220;
        int gold = (difficulty == 1) ? 20 : 15;

        return createHeroFromChoices(choice, name, points, gold);
    }


    private void moveToNewRoom(Hero hero) {
        ArrayList<Room> connections = currentRoom.getConnectedRooms();

        for(int i = 0; i < connections.size(); i++) {
            System.out.println((i+1) + ". Go to " + connections.get(i).getName());
        }

        System.out.print("Choose room: ");
        int choice = scanner.nextInt() - 1;

        if(choice >= 0 && choice < connections.size()) {
            currentRoom = connections.get(choice);
            System.out.println("\nMoving to " + currentRoom.getName() + "...");
            currentRoom.triggerRandomEvent(hero);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Main game loop
    public void gameLoop(Hero hero) {
        boolean gameRunning = true;

        while(gameRunning) {
            // Display current room
            currentRoom.describe();

            // Check for enemy
            if(currentRoom.getEnemy() != null) {
                handleCombat(hero, currentRoom.getEnemy());
            }

            // Check for vendor
            if(currentRoom.getVendor() != null) {
                handleVendor(hero, currentRoom.getVendor());
            }

            // Show options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Move to another room");
            System.out.println("2. Use potion");
            System.out.println("3. Show status");
            System.out.println("4. Quit game");

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    moveToNewRoom(hero);  // Now passing hero
                    break;
                case 2:
                    usePotion(hero);
                    break;
                case 3:
                    hero.checkStatus();
                    break;
                case 4:
                    gameRunning = false;
                    break;
            }

            if(hero.getHp() <= 0) {
                System.out.println("Game Over! The job market was too tough...");
                gameRunning = false;
            }
        }
    }

    // Room movement handling
    private void moveToNewRoom() {
        ArrayList<Room> connections = currentRoom.getConnectedRooms();

        for(int i = 0; i < connections.size(); i++) {
            System.out.println((i+1) + ". Go to " + connections.get(i).getName());
        }

        System.out.print("Choose room: ");
        int choice = scanner.nextInt() - 1;

        if(choice >= 0 && choice < connections.size()) {
            currentRoom = connections.get(choice);
            System.out.println("\nMoving to " + currentRoom.getName() + "...");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Combat handling
    private void handleCombat(Hero hero, NPC enemy) {
        System.out.println("\nBattle starts with " + enemy.getName() + "!");
        boolean won = hero.attack(enemy);

        if(won) {
            System.out.println("You defeated " + enemy.getName() + "!");

            // Adiciona recompensas ao vencer o combate
            hero.levelUp(); // Aumenta o nível
            hero.increaseHp(10); // Adiciona 10 pontos de vida
            hero.increaseStrength(1); // Aumenta 1 ponto de força
            int enemyGold = enemy.getGold();
            hero.addGold(enemyGold); // Obtém o ouro do inimigo
            System.out.println("You leveled up!");
            System.out.println("Gained 10 HP and 1 strength point!");
            System.out.println("Looted " + enemyGold + " gold from the enemy!");

            currentRoom.setEnemy(null);

            // After winning combat, check for job offer
            if(enemy.getName().contains("Technical Interview") ||
                    enemy.getName().contains("Senior Developer")) {
                currentRoom.checkForJobOffer(hero);
            }

            // If got job offer, trigger win condition
            if(checkWinCondition(hero)) {
                System.out.println("\nYou've completed your journey into tech!");
                System.out.println("Time to start your new career!");
                // Could add option to continue playing or end game
            }
        }
    }

    // Vendor interaction handling
    private void handleVendor(Hero hero, Vendor vendor) {
        boolean shopping = true;

        while(shopping) {
            System.out.println("\nYour gold: " + hero.getGold());
            System.out.println("\nWould you like to shop?");
            System.out.println("1. View items");
            System.out.println("2. Leave shop");

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    vendor.showShop();  // Shows random 10 items
                    System.out.print("\nEnter item number to buy (0 to cancel): ");
                    int itemChoice = scanner.nextInt() - 1;

                    if(itemChoice >= 0) {
                        vendor.sell(hero, itemChoice);
                    }
                    break;

                case 2:
                    shopping = false;
                    System.out.println("Thanks for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Potion usage handling
    private void usePotion(Hero hero) {
        ArrayList<HeroItem> inventory = hero.getInventory();
        ArrayList<Potion> potions = new ArrayList<>();

        // Find all potions in inventory
        for (HeroItem item : inventory) {
            if (item instanceof Potion) {
                potions.add((Potion) item);
            }
        }

        if (potions.isEmpty()) {
            System.out.println("You don't have any potions!");
            return;
        }

        // Display potions
        System.out.println("\nYour potions:");
        for (int i = 0; i < potions.size(); i++) {
            System.out.print((i+1) + ". ");
            potions.get(i).showDetails();
        }

        // Select potion
        System.out.print("Choose potion (0 to cancel): ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= potions.size()) {
            Potion selected = potions.get(choice-1);
            selected.use(hero);
            inventory.remove(selected);
        }

    }

    // Helper method for character creation
    private Hero createHeroFromChoices(int classChoice, String name, int points, int gold) {
        Hero hero = null;

        System.out.println("\nDistribute your points:");
        System.out.println("Each HP point costs 1 point");
        System.out.println("Each Strength point costs 5 points");

        int hp = getValidHpInput(points);
        int remainingPoints = points - hp;
        int strength = getValidStrengthInput(remainingPoints);

        switch(classChoice) {
            case 1:
                hero = new BootcampGraduate(name, hp, strength, 1, gold);
                break;
            case 2:
                hero = new CareerChanger(name, hp, strength, 1, gold);
                break;
            case 3:
                hero = new SelfTaughtDev(name, hp, strength, 1, gold);
                break;
        }

        return hero;
    }

    private int getValidHpInput(int maxPoints) {
        while(true) {
            System.out.print("Enter HP (max " + maxPoints + "): ");
            int hp = scanner.nextInt();
            if(hp > 0 && hp <= maxPoints) {
                return hp;
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    private int getValidStrengthInput(int remainingPoints) {
        while(true) {
            System.out.print("Enter Strength (max " + (remainingPoints/5) + "): ");
            int strength = scanner.nextInt();
            if(strength > 0 && strength * 5 <= remainingPoints) {
                return strength;
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    // Adventure start method
    public void startAdventure(Hero hero) {
        System.out.println("\n=== The Tech Job Quest Begins ===");
        System.out.println("Armed with determination and a GitHub account,");
        System.out.println("you begin your journey into the tech industry...");

        gameLoop(hero);
    }

    // Main method
    public static void main(String[] args) {
        System.out.println("\n====================================");
        System.out.println("»»»-----------JOB-HUNT!------------>");
        System.out.println("====================================");
        System.out.println("In a dystopian world where 'entry-level' requires 5 years of experience");
        System.out.println("and recruiters have mastered the art of ghosting...");
        System.out.println();
        System.out.println("YOU are a brave soul attempting to break into the tech industry!");
        System.out.println();
        System.out.println("Navigate through the hazardous LinkedIn maze, survive technical");
        System.out.println("interviews that question your existence, and endure algorithm");
        System.out.println("challenges designed by sadistic senior developers.");
        System.out.println();
        System.out.println("Will you triumph over ATS systems and arbitrary hiring processes");
        System.out.println("to obtain the mythical Job Offer?");
        System.out.println();
        System.out.println("Remember: It's not rejection, it's just 'not a culture fit'!");
        System.out.println("====================================");
        System.out.println("\nPress Enter to begin your journey...");

        new Scanner(System.in).nextLine();

        Game game = new Game();
        Hero hero = game.createCharacter();
        game.startAdventure(hero);
    }

    private boolean hasJobOffer(Hero hero) {
        for(HeroItem item : hero.getInventory()) {
            if(item instanceof JobOffer) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWinCondition(Hero hero) {
        if(hasJobOffer(hero)) {
            System.out.println("\n=== CONGRATULATIONS! ===");
            System.out.println("You've successfully landed a job in tech!");
            // Get and show job offer details
            JobOffer offer = findJobOffer(hero);
            offer.showDetails();
            return true;
        }
        return false;
    }

    private JobOffer findJobOffer(Hero hero) {
        for(HeroItem item : hero.getInventory()) {
            if(item instanceof JobOffer) {
                return (JobOffer) item;
            }
        }
        return null;
    }
}