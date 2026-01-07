/**
 * Ett vapen som ökar spelarens skada.
 * Ärver från Item.
 * @author Jonas
 */
public class Weapon extends Item {

    // === EXTRA ATTRIBUT ===
    private int increaseDamage;

    // === KONSTRUKTOR ===
    /**
     * Skapar ett vapen.
     * @param name Namn, t.ex. "Svärd"
     * @param itemDesc Beskrivning
     * @param increaseDamage Extra skada vapnet ger (läggs till baseskada)
     */
    public Weapon(String name, String itemDesc, int increaseDamage) {
        // Anropa superklassens konstruktor med ItemType.WEAPON
        super(name, itemDesc, ItemType.WEAPON);
        this.increaseDamage = increaseDamage;
    }

    // === GETTERS ===
    /**
     * Hämtar vapnets extra skada.
     * @return Extra skada
     */
    public int getIncreaseDamage() {
        return increaseDamage;
    }

    // === SETTERS ===
    /**
     * Sätter vapnets extra skada.
     * @param increaseDamage Extra skada
     */
    public void setIncreaseDamage(int increaseDamage) {
        this.increaseDamage = increaseDamage;
    }

    // === ÖVRIGA METODER ===
    /**
     * Använd vapnet.
     *
     * @param player Spelaren som använder vapnet
     * @return Text som visas när vapnet används
     */
    @Override
    public String use(Player player) {
        return "Du svingar vapnet men inget händer (använd i strid).";
    }
}
