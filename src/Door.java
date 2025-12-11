/**
 * Representerar en dörr mellan två rum i dungeon-spelet.
 * En dörr har en riktning (north, south, east, west), kan vara låst
 * och leder till ett specifikt rum.
 * @author Jonas
 */
public class Door {

    /**
     * Riktningar som en dörr kan ha.
     * Varje riktning har ett kommandotecken (n, s, e, w)
     * och ett engelskt namn (north, south, east, west).
     */
    public enum Direction {
        NORTH('n', "north"),
        SOUTH('s', "south"),
        EAST('e', "east"),
        WEST('w', "west");

        private final char commandChar;  // Tecken spelaren använder
        private final String name;       // Svenskt namn på riktningen

        /**
         * Skapar en riktning med kommandotecken och namn.
         *
         * @param commandChar tecknet spelaren skriver (n, s, ö, v)
         * @param name det svenska namnet på riktningen
         */
        Direction(char commandChar, String name) {
            this.commandChar = commandChar;
            this.name = name;
        }

        /**
         * Hämtar kommandotecknet som spelaren ska skriva.
         *
         * @return kommandotecknet (n, s, ö, v)
         */
        public char getCommandChar() {
            return commandChar;
        }

        /**
         * Hämtar det svenska namnet på riktningen.
         *
         * @return det svenska riktningens namn
         */
        public String getName() {
            return name;
        }

        /**
         * Översätter ett tecken till motsvarande riktning.
         *
         * @param c tecknet spelaren skrev (n, s, ö, v)
         * @return motsvarande Direction
         * @throws IllegalArgumentException om tecknet är ogiltigt
         */
        public static Direction fromChar(char c) {
            for (Direction d : values()) {
                if (d.commandChar == c) return d;
            }
            throw new IllegalArgumentException("Invalid direction: " + c);
        }
    }

    private final Direction direction;  // Vilket håll dörren ligger åt
    private boolean locked;             // Om dörren är låst
    private Room leadsTo;               // Rummet dörren leder till

    /**
     * Skapar en ny dörr.
     *
     * @param direction dörrens riktning (north, south, east, west)
     * @param locked om dörren är låst
     * @param leadsTo rummet som dörren leder till
     */
    public Door(Direction direction, boolean locked, Room leadsTo) {
        this.direction = direction;
        this.locked = locked;
        this.leadsTo = leadsTo;
    }

    /**
     * Skapar en ny dörr med riktning angiven som tecken.
     *
     * @param directionChar riktning som tecken (n, s, ö, v)
     * @param locked om dörren är låst
     * @param leadsTo rummet som dörren leder till
     */
    public Door(char directionChar, boolean locked, Room leadsTo) {
        this(Direction.fromChar(directionChar), locked, leadsTo);
    }

    /**
     * Hämtar dörrens riktning.
     *
     * @return riktningen som en Direction-enum
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Kontrollerar om dörren är låst.
     *
     * @return true om dörren är låst, annars false
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Låser eller låser upp dörren.
     *
     * @param locked true för att låsa dörren, false för att låsa upp den
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * Hämtar rummet som dörren leder till.
     *
     * @return rummet dörren leder till
     */
    public Room getLeadsTo() {
        return leadsTo;
    }

    /**
     * Ändrar vilket rum dörren leder till.
     *
     * @param leadsTo det nya rummet dörren ska leda till
     */
    public void setLeadsTo(Room leadsTo) {
        this.leadsTo = leadsTo;
    }

    // === ADAPTER-METODER för kompatibilitet med Room och Dungeon ===

    /**
     * Hämtar dörrens riktning som tecken (för Room-klassen).
     *
     * @return riktningen som tecken (n, s, ö, v)
     */
    public char getPosition() {
        return direction.getCommandChar();
    }

    /**
     * Hämtar riktningens namn (för Room-klassen).
     *
     * @return riktningens svenska namn
     */
    public String getDirectionName() {
        return direction.getName();
    }

    /**
     * Hämtar kommandotecknet (för Room-klassen).
     *
     * @return kommandotecknet (n, s, ö, v)
     */
    public char getCommandChar() {
        return direction.getCommandChar();
    }

    /**
     * Hämtar nästa rum (alias för getLeadsTo, för Dungeon-klassen).
     *
     * @return rummet dörren leder till
     */
    public Room getNextRoom() {
        return leadsTo;
    }
}