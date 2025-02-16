import java.util.ArrayList;
import java.util.Random;

public class Room {
    private String name;
    private String description;
    private ArrayList<Room> connectedRooms;
    private NPC enemy;
    private Vendor vendor;
    private ArrayList<HeroItem> items;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.connectedRooms = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    public void addConnection(Room room) {
        connectedRooms.add(room);
    }

    public void setEnemy(NPC enemy) {
        this.enemy = enemy;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void describe() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);

        if(enemy != null) {
            System.out.println("There's a " + enemy.getName() + " here!");
        }

        if(vendor != null) {
            System.out.println("A vendor is available for trading.");
        }

        if(!items.isEmpty()) {
            System.out.println("You see some items here.");
        }

        System.out.println("\nConnected rooms:");
        for(int i = 0; i < connectedRooms.size(); i++) {
            System.out.println((i+1) + ". " + connectedRooms.get(i).getName());
        }
    }

    public void checkForJobOffer(Hero hero) {
        if(this.getName().contains("Interview") || this.getName().contains("Technical Test")) {
            Random rand = new Random();
            if(rand.nextInt(100) < 25) {  // 25% chance
                String[] companies = {
                        "TechCorp",
                        "StartupDreams",
                        "BigTech Inc.",
                        "Innovation Labs",
                        "CodeMasters"
                };

                String[] positions = {
                        "Junior Developer",
                        "Software Engineer",
                        "Full-Stack Developer",
                        "Frontend Developer",
                        "Backend Developer"
                };

                JobOffer offer = new JobOffer(
                        companies[rand.nextInt(companies.length)],
                        positions[rand.nextInt(positions.length)],
                        70000 + rand.nextInt(30000)  // Random salary between 70k-100k
                );

                hero.addToInventory(offer);
                System.out.println("\n!!! CONGRATULATIONS !!!");
                System.out.println("You've received a job offer!");
                offer.showDetails();
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Room> getConnectedRooms() {
        return connectedRooms;
    }

    public NPC getEnemy() {
        return enemy;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public ArrayList<HeroItem> getItems() {
        return items;
    }


    public void triggerRandomEvent(Hero hero) {
        Random rand = new Random();
        int chance = rand.nextInt(100);  // 0-99

        if(chance < 20) {  // 20% chance of random event
            int eventType = rand.nextInt(3);
            switch(eventType) {
                case 0:
                    linkedInEvent(hero);
                    break;
                case 1:
                    technicalChallengeEvent(hero);
                    break;
                case 2:
                    networkingEvent(hero);
                    break;
            }
        }
    }

    private void linkedInEvent(Hero hero) {
        System.out.println("\n*** Random Event: LinkedIn Algorithm Boost! ***");
        System.out.println("Your post about your coding journey went viral!");
        int goldFound = new Random().nextInt(20) + 10;
        hero.addGold(goldFound);
    }

    private void technicalChallengeEvent(Hero hero) {
        System.out.println("\n*** Random Event: Surprise Technical Challenge! ***");
        System.out.println("A wild coding problem appears!");
        if(new Random().nextBoolean()) {
            System.out.println("You solved it! Health boosted!");
            hero.increaseHp(15);
        } else {
            System.out.println("The problem was too complex! You took damage!");
            hero.reduceHp(10);
        }
    }

    private void networkingEvent(Hero hero) {
        System.out.println("\n*** Random Event: Virtual Meetup! ***");
        System.out.println("You join a tech meetup and make valuable connections.");
        hero.increaseHp(10);
        System.out.println("The supportive community heals you for 10 HP!");
    }

    public void addItem(HeroItem item) {
        items.add(item);
    }

    public void removeItem(HeroItem item) {
        items.remove(item);
    }
}