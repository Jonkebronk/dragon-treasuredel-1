import java.util.Scanner;

/**
 * Dungeon-klassen hanterar spellogiken och navigering mellan rum.
 * @author Johnny
 */
public class Dungeon {
    private Room currentRoom;
    private String welcomeMessage;
    private Player player;
    private Scanner scanner;

    /**
     * Konstruktor för Dungeon
     * @param welcomeMessage Välkomstmeddelande som visas vid spelstart
     */
    public Dungeon(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
        this.scanner = new Scanner(System.in);
    }

    // Getters
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    // Setters
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Huvudloopen för spelet. Hanterar navigering mellan rum.
     */
    public void playGame() {
        System.out.println("Välkommen " + player.getName() + " till din skattjakt.");

        boolean playing = true;

        while (playing) {
            // Visa rummets beskrivning och tillgängliga dörrar
            currentRoom.doNarrative();

            // Läs spelarens val
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.isEmpty()) {
                continue;
            }

            char direction = input.charAt(0);

            // Försök hämta dörr i vald riktning
            Door door = currentRoom.getDoor(direction);

            if (door == null) {
                System.out.println("Det finns ingen dörr i den riktningen.");
            } else if (door.isLocked()) {
                System.out.println("Du har ingen nyckel som passar.");
                System.out.println("Du kikar genom nyckelhålet och ser en skattkista full med guld.");
                printTreasure();
            } else {
                Room nextRoom = door.getNextRoom();
                if (nextRoom == null) {
                    // Utgång - spelet slutar
                    printDragon();
                    System.out.println("Du lämnar grottan med livet i behåll. Grattis, du förlorade inte!");
                    playing = false;
                } else {
                    currentRoom = nextRoom;
                }
            }
        }
    }

    /**
     * Skriver ut ASCII-art av skattkistan
     */
    private void printTreasure() {
        System.out.println("                  _.--.");
        System.out.println("              _.-'_:-'||");
        System.out.println("          _.-'_.-::::'||");
        System.out.println("     _.-:'_.-::::::'  ||");
        System.out.println("   .'`-.-:::::::'     ||");
        System.out.println("  /.'`;|:::::::'      ||_");
        System.out.println(" ||   ||::::::'      _.;._'-._");
        System.out.println(" ||   ||:::::'   _.-!oo @.!-._'-.");
        System.out.println(" \\'.  ||:::::.-!() oo @!()@.-'_.|");
        System.out.println("   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||");
        System.out.println("     `>'-.!@%()@'@_%-'_.-o _.|'||");
        System.out.println("      ||-._'-.@.-'_.-' _.-o  |'||");
        System.out.println("      ||=[ '-._.-\\U/.-'    o |'||");
        System.out.println("      || '-.]=|| |'|      o  |'||");
        System.out.println("      ||      || |'|        _| ';");
        System.out.println("      ||      || |'|    _.-'_.-'");
        System.out.println("      |'-._   || |'|_.-'_.-'");
        System.out.println("      '-._'-.|| |' `_.-'");
        System.out.println("           '-.||_/.-'");
    }

    /**
     * Skriver ut ASCII-art av draken
     */
    private void printDragon() {
        System.out.println("                                                  .~))>>");
        System.out.println("                                                 .~)>>");
        System.out.println("                                               .~))))>>>");
        System.out.println("                                             .~))>>             ___");
        System.out.println("                                           .~))>>)))>>      .-~))>>");
        System.out.println("                                         .~)))))>>       .-~))>>)>");
        System.out.println("                                       .~)))>>))))>>  .-~)>>)>");
        System.out.println("                   )                 .~))>>))))>>  .-~)))))>>)>");
        System.out.println("                ( )@@*)             //)>))))))  .-~))))>>)>");
        System.out.println("              ).@(@@               //))>>))) .-~))>>)))))>>)>");
        System.out.println("            (( @.@).              //))))) .-~)>>)))))>>)>");
        System.out.println("          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>");
        System.out.println("       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>");
        System.out.println("      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>");
        System.out.println("    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>");
        System.out.println("     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>");
        System.out.println("   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._");
        System.out.println("    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.");
        System.out.println(" ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb");
        System.out.println(" ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,");
        System.out.println("   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,");
        System.out.println("     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.");
        System.out.println("       (*.@*              / ((((        \\| | |  \\       .       b `.");
        System.out.println("                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;");
        System.out.println("                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;");
        System.out.println("                       /   /   `(((((()    )    (((((~      `,  b  ;");
        System.out.println("                     _/  _/      `\"\"\"/   /'                  ; b   ;");
        System.out.println("                 _.-~_.-~           /  /'                _.'~bb _.'");
        System.out.println("               ((((~~              / /'              _.'~bb.--~");
        System.out.println("                                  ((((          __.-~bb.-~");
        System.out.println("                                              .'  b .~~");
        System.out.println("                                              :bb ,'");
        System.out.println("                                              ~~~~");
    }
}
