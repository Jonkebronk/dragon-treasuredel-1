import java.util.Scanner;
import java.util.ArrayList;

/**
 * Huvudklass för Dragon Treasure-spelet.
 * Hanterar uppstart och avslut av spelet.
 * @author Johnny
 */
public class DragonTreasure {
    private Dungeon dungeon;
    private Scanner scanner;
    private ArrayList<Room> rooms;

    /**
     * Konstruktor
     */
    public DragonTreasure() {
        this.scanner = new Scanner(System.in);
        this.rooms = new ArrayList<>();
    }

    /**
     * Sätter upp spelet: skapar rum, dörrar, kopplingar och spelare.
     */
    public void setupGame() {
        // Skapa välkomstmeddelande och dungeon
        String welcomeMessage = "Välkommen till Dragon Treasure";
        dungeon = new Dungeon(welcomeMessage);

        // Hämta spelarens namn
        System.out.println(welcomeMessage);
        System.out.println("Skriv ditt namn och tryck på [Enter] för att starta ett nytt spel...");
        String playerName = scanner.nextLine();

        // Skapa spelare
        Player player = new Player(playerName);
        dungeon.setPlayer(player);

        // === SKAPA RUM ===
        Room outside = new Room("Du står utanför en grotta. Det luktar svavel från öppningen.\nGrottöppningen är österut. Skriv \"ö\" och tryck på [Enter] för att komma in i grottan");
        Room entrance = new Room("När du går in i grottan kollapsar ingången bakom dig.\nRummet är upplyst av några ljus som sitter på ett bord framför dig.");
        Room deadBody = new Room("Du ser en död kropp på golvet.");
        Room torch = new Room("Du ser en brinnande fackla i rummets ena hörn och känner en motbjudande stank.");
        Room wetRoom = new Room("Du kommer in i ett fuktigt rum med vatten sipprandes längs den västra väggen.");
        Room caveRoom = new Room("Du kommer in i ett rymligt bergrum med en ljusstrimma sipprandes genom en spricka i den östra väggen.");

        // Lagra rum i ArrayList enligt uppgiftskrav
        rooms.add(outside);
        rooms.add(entrance);
        rooms.add(deadBody);
        rooms.add(torch);
        rooms.add(wetRoom);
        rooms.add(caveRoom);

        // === SKAPA DÖRRAR OCH KOPPLINGAR ===
        // Från outside
        outside.addDoor(new Door('ö', false, entrance));

        // Från entrance
        entrance.addDoor(new Door('n', false, deadBody));
        entrance.addDoor(new Door('s', false, caveRoom));

        // Från deadBody
        deadBody.addDoor(new Door('s', false, entrance));
        deadBody.addDoor(new Door('ö', false, torch));

        // Från torch (har utgång österut)
        torch.addDoor(new Door('v', false, deadBody));
        torch.addDoor(new Door('s', false, wetRoom));
        torch.addDoor(new Door('ö', false, null)); // null = utgång

        // Från wetRoom (har låst dörr österut)
        wetRoom.addDoor(new Door('n', false, torch));
        wetRoom.addDoor(new Door('v', false, caveRoom));
        wetRoom.addDoor(new Door('ö', true, null)); // Låst dörr till skatten

        // Från caveRoom
        caveRoom.addDoor(new Door('n', false, entrance));
        caveRoom.addDoor(new Door('ö', false, wetRoom));

        // Sätt startrum
        dungeon.setCurrentRoom(outside);
    }

    /**
     * Avslutar spelet och städar upp resurser.
     */
    public void endGame() {
        System.out.println("Tack för att du spelade Dragon Treasure!");
        scanner.close();
    }

    /**
     * Main-metoden - spelets startpunkt
     */
    public static void main(String[] args) {
        DragonTreasure game = new DragonTreasure();
        game.setupGame();
        game.dungeon.playGame();
        game.endGame();
    }
}
