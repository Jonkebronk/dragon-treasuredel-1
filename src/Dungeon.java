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
     * Huvudloopen för spelet. Hanterar navigering, pickup, combat och drink.
     */
    public void playGame() {
        System.out.println("Välkommen " + player.getName() + " till din skattjakt. Akta dig för draken!");

        boolean playing = true;

        while (playing && player.isAlive()) {
            // Visa rummets beskrivning och tillgängliga dörrar
            currentRoom.doNarrative(player);

            // Kontrollera om det finns ett monster - strid!
            if (currentRoom.hasMonster()) {
                // Visa drake-ASCII om det är en drake
                if (currentRoom.getMonster().getName().equalsIgnoreCase("Drake")) {
                    printDragon();
                }

                boolean survived = currentRoom.doBattle(player);
                if (!survived) {
                    // Spelaren dog
                    playing = false;
                    continue;
                }

                // Om spelaren överlevde och det finns en skatt, plocka upp den automatiskt
                if (currentRoom.hasItem() && currentRoom.getItem() instanceof Treasure) {
                    Item treasure = currentRoom.getItem();
                    player.addItem(treasure);
                    currentRoom.removeItem();
                    System.out.println("Du besegrar draken och samlar skatten.");
                }
            }

            // Visa tips om hälsodryck om spelaren har låg hälsa och har en potion
            if (player.getHealthPoints() < 10 && player.getPotion() != null) {
                System.out.println("Du har " + player.getHealthPoints() + " hälsopoäng kvar. Kan vara en bra idé att dricka den där hälsodrycken [d]");
            }

            // Läs spelarens val
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.isEmpty()) {
                continue;
            }

            char command = input.charAt(0);

            // Hantera kommandon
            if (command == 'p') {
                // === PICKUP ===
                if (currentRoom.hasItem()) {
                    Item item = currentRoom.getItem();
                    player.addItem(item);
                    currentRoom.removeItem();
                    System.out.println("Du tog upp " + item.getName().toLowerCase() + ".");
                } else {
                    System.out.println("Det finns inget att plocka upp här.");
                }
            } else if (command == 'd') {
                // === DRINK POTION ===
                Potion potion = player.getPotion();
                if (potion != null) {
                    int healAmount = potion.getHealing();
                    player.heal(healAmount);
                    player.removeItem(potion);
                    System.out.println("Du dricker hälsodrycken och återfår " + healAmount + " hälsopoäng.");
                } else {
                    System.out.println("Du har ingen hälsodryck att dricka.");
                }
            } else {
                // === NAVIGATION (n, s, e, w) ===
                Door door = currentRoom.getDoor(command);

                if (door == null) {
                    System.out.println("Det finns ingen dörr i den riktningen.");
                } else if (door.isLocked()) {
                    // Dörren är låst - kolla om spelaren har nyckel
                    if (player.hasKey()) {
                        door.setLocked(false);
                        System.out.println("Du låser upp dörren med nyckeln.");
                        currentRoom = door.getNextRoom();
                    } else {
                        System.out.println("Dörren är låst. Du behöver hitta en nyckel.");
                        printTreasure();
                    }
                } else {
                    Room nextRoom = door.getNextRoom();
                    if (nextRoom == null) {
                        // Utgång - spelet slutar
                        if (player.hasTreasure()) {
                            // Vann med skatt!
                            printTreasure();
                            System.out.println("Du lämnar grottan med skatten. Grattis, du vann!");
                        } else {
                            System.out.println("Du lämnar grottan med livet i behåll. Grattis, du förlorade inte!");
                        }
                        playing = false;
                    } else {
                        currentRoom = nextRoom;
                    }
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
        System.out.println(
            "                                                  .~))>>\n"+
            "                                                 .~)>>\n"+
            "                                               .~))))>>>\n"+
            "                                             .~))>>             ___\n"+
            "                                           .~))>>)))>>      .-~))>>\n"+
            "                                         .~)))))>>       .-~))>>)>\n"+
            "                                       .~)))>>))))>>  .-~)>>)>\n"+
            "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
            "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
            "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
            "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
            "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
            "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
            "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
            "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
            "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
            "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
            "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
            " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
            " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
            "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
            "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
            "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
            "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
            "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
            "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
            "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
            "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
            "               ((((~~              / /'              _.'~bb.--~\n"+
            "                                  ((((          __.-~bb.-~\n"+
            "                                              .'  b .~~\n"+
            "                                              :bb ,' \n"+
            "                                              ~~~~\n");
    }
}
