/**
 * Representerar spelaren i Dragon Treasure.
 * Håller reda på spelarens namn.
 * @author William
 */

public class Player {

    // Privat variabel
    private String name;

    /**
     * Skapar en ny spelare med ett namn.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Hämtar spelarens namn.
     */
    public String getName() {
        return name;
    }

    /**
     * Ändrar spelarens namn (används inte än).
     */
    public void setName(String name) {
        this.name = name;
    }
}
