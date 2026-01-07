/**
 * En nyckel som kan öppna låsta dörrar.
 * Ärver från Item.
 * @author Jonas
 */
public class Key extends Item {

    // === KONSTRUKTOR ===
    /**
     * Skapar en nyckel.
     * @param name Nyckelns namn, t.ex. "Nyckel"
     * @param itemDesc Beskrivning, t.ex. "En rostig nyckel"
     */
    public Key(String name, String itemDesc) {
        // Anropa superklassens konstruktor med ItemType.KEY
        super(name, itemDesc, ItemType.KEY);
    }

    // === ÖVRIGT ===
    /**
     * Använd nyckeln.
     * @param player Spelaren som använder nyckeln
     * @return Text som ska visas när nyckeln används
     */
    @Override
    public String use(Player player) {
        return "Du försöker använda nyckeln.";
    }
}
