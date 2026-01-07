/**
 * En skatt som spelaren kan samla.
 * Ärver från Item.
 * @author Jonas
 */
public class Treasure extends Item {

    // === EXTRA ATTRIBUT ===
    private int goldValue;

    // === KONSTRUKTOR ===
    /**
     * Skapar en skatt.
     * @param name Namn, t.ex. "Skattkista"
     * @param itemDesc Beskrivning
     * @param goldValue Värde i guld
     */
    public Treasure(String name, String itemDesc, int goldValue) {
        // Anropa superklassens konstruktor med ItemType.TREASURE
        super(name, itemDesc, ItemType.TREASURE);
        this.goldValue = goldValue;
    }

    // === GETTERS ===
    /**
     * Hämtar skattens värde.
     * @return Värde i guld
     */
    public int getGoldValue() {
        return goldValue;
    }

    // === SETTERS ===
    /**
     * Sätter skattens värde.
     * @param goldValue Värde i guld
     */
    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }

    // === ÖVRIGA METODER ===
    /**
     * Använd skatt.
     *
     * @param player Spelaren som använder skatten
     * @return Text som visas när skatten försöker användas
     */
    @Override
    public String use(Player player) {
        return "Du kan inte använda skatten, men den är värdefull.";
    }
}
