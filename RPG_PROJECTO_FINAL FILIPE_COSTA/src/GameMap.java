import java.util.Random;
import Enums.VendorType;

public class GameMap {
    private Room startingRoom;
    private Random rand = new Random();

    public GameMap() {
        createAndConnectRooms();
    }

    public NPC createInterviewNPC() {
        String[] interviewers = {
                "Senior Technical Interviewer",
                "Lead System Architect",
                "Hiring Manager",
                "Algorithm Expert",
                "Full-Stack Veteran"
        };

        return new NPC(
                interviewers[rand.nextInt(interviewers.length)],
                120,  // HP
                25,   // Strength
                100   // Gold reward
        );
    }

    public NPC createSeniorInterviewNPC() {
        return new NPC(
                "Principal Software Architect",
                200,  // More HP
                35,   // More Strength
                250   // More Gold reward
        );
    }

    private void createAndConnectRooms() {
        // Create all rooms
        Room linkedInLobby = new Room("LinkedIn Lobby",
                "A vast hall filled with professionals posting motivational quotes.");
        linkedInLobby.setVendor(new Vendor("LinkedIn Premium Seller", 50, 10, 100, VendorType.TECH_STORE));

        Room technicalRoom = new Room("Technical Interview Chamber",
                "Whiteboards cover every wall. The air is thick with algorithm anxiety.");
        technicalRoom.setEnemy(createInterviewNPC());

        Room systemDesignRoom = new Room("System Design Challenge",
                "A room full of architecture diagrams and scalability questions.");
        systemDesignRoom.setEnemy(createInterviewNPC());

        Room coffeeRoom = new Room("Coffee Break Room",
                "A place to rest and prepare. The coffee machine looks inviting.");
        coffeeRoom.setVendor(new Vendor("Caffeinated Coder", 50, 10, 100, VendorType.COFFEE_SHOP));

        Room zoomRoom = new Room("Remote Interview Portal",
                "A virtual space where 'You're on mute' echoes eternally.");
        zoomRoom.setEnemy(createInterviewNPC());

        Room githubRoom = new Room("Portfolio Review Chamber",
                "Your GitHub projects float in the air, awaiting judgment.");
        githubRoom.setEnemy(createInterviewNPC());

        Room bootcampRoom = new Room("Bootcamp Corner",
                "A place filled with intensive learning resources and quick tutorials.");
        bootcampRoom.setVendor(new Vendor("Bootcamp Mentor", 50, 10, 100, VendorType.BOOTCAMP_VENDOR));

        Room finalRoom = new Room("Final Interview",
                "The last challenge before potentially getting an offer!");
        finalRoom.setEnemy(createSeniorInterviewNPC());  // Tougher final boss

        // Connect rooms
        linkedInLobby.addConnection(technicalRoom);
        linkedInLobby.addConnection(coffeeRoom);
        linkedInLobby.addConnection(zoomRoom);
        linkedInLobby.addConnection(bootcampRoom);

        technicalRoom.addConnection(linkedInLobby);
        technicalRoom.addConnection(systemDesignRoom);
        technicalRoom.addConnection(githubRoom);

        systemDesignRoom.addConnection(technicalRoom);
        systemDesignRoom.addConnection(finalRoom);

        coffeeRoom.addConnection(linkedInLobby);
        coffeeRoom.addConnection(finalRoom);

        zoomRoom.addConnection(linkedInLobby);
        zoomRoom.addConnection(githubRoom);

        bootcampRoom.addConnection(linkedInLobby);
        bootcampRoom.addConnection(technicalRoom);

        githubRoom.addConnection(zoomRoom);
        githubRoom.addConnection(technicalRoom);
        githubRoom.addConnection(finalRoom);

        startingRoom = linkedInLobby;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }
}