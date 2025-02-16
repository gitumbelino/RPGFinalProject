public class RoomBuilder {
    public static Room createStartingRoom() {
        return new Room(
                "LinkedIn Lobby",
                "A vast hall filled with professionals posting motivational quotes. A recruiter bot scans the area."
        );
    }

    public static Room createInterviewRoom() {
        Room room = new Room(
                "Technical Interview Chamber",
                "Whiteboards line the walls. The air is thick with algorithm anxiety."
        );
        room.setEnemy(new NPC("Senior Developer", 100, 20, 50));
        return room;
    }

    public static Room createStartupRoom() {
        Room room = new Room(
                "Startup Valley",
                "'We're like a family here!' echoes through the ping-pong filled space."
        );
        room.setVendor(new Vendor("Coffee-Driven Developer", 50, 10, 100));
        return room;
    }

    public static Room createZoomRoom() {
        return new Room(
                "Remote Interview Void",
                "'You're muted!' shouts someone in the distance. Connection seems unstable."
        );
    }

    public static Room createGhostingChamber() {
        Room room = new Room(
                "The Ghosting Chamber",
                "Hundreds of unread application status emails float in the air."
        );
        room.setEnemy(new NPC("Ghosting Recruiter", 80, 15, 30));
        return room;
    }
}