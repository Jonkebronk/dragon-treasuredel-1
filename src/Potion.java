/**
 * En hälsodryck som helar spelaren.
 * Ärver från Item.
 * @author Jonas
 */
public class Potion extends Item {

    // === EXTRA ATTRIBUT ===
    private int healing;

    // === KONSTRUKTOR ===
    /**
     * Skapar en hälsodryck.
     * @param name Namn, t.ex. "Hälsodryck"
     * @param itemDesc Beskrivning
     * @param healing Antal HP som återställs (t.ex. 6)
     */
    public Potion(String name, String itemDesc, int healing) {
        // Anropa superklassens konstruktor med ItemType.POTION
        super(name, itemDesc, ItemType.POTION);
        this.healing = healing;
    }

    // === GETTERS ===
    /**
     * Hämtar hur mycket drycken helar.
     * @return Antal HP
     */
    public int getHealing() {
        return healing;
    }

    // === SETTERS ===
    /**
     * Sätter hur mycket drycken helar.
     * @param healing Antal HP
     */
    public void setHealing(int healing) {
        this.healing = healing;
    }

    // === ÖVRIGA METODER ===
    /**
     * Använd hälsodrycken på spelaren.
     * Återställer spelarens HP och returnerar text.
     */
    @Override
    public String use(Player player) {
        player.heal(healing);
        return "Du dricker hälsodrycken och återfår " + healing + " hälsopoäng";
    }
}
