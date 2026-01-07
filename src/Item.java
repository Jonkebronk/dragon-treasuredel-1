/**
 * Basklass för alla föremål i spelet.
 * Används för polymorfism - alla items kan hanteras som Item.
 * @author Jonas
 */
public class Item {

    // === ENUM ===
    public enum ItemType {
        KEY,
        POTION,
        WEAPON,
        TREASURE
    }

    // === ATTRIBUT (private) ===
    private String name;
    private String itemDesc;
    private final ItemType itemType;

    // === KONSTRUKTOR ===
    /**
     * Skapar ett nytt föremål.
     * @param name Föremålets namn
     * @param itemDesc Beskrivning av föremålet
     * @param itemType Typ av föremål
     */
    public Item(String name, String itemDesc, ItemType itemType) {
        this.name = name;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
    }

    // === GETTERS ===

    /**
     * Hämtar föremålets namn.
     * @return Namnet
     */
    public String getName() {
        return name;
    }

    /**
     * Hämtar föremålets beskrivning.
     * @return Beskrivningen
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * Hämtar föremålets typ.
     * @return ItemType
     */
    public ItemType getItemType() {
        return itemType;
    }

    // === SETTERS ===

    /**
     * Sätter föremålets namn.
     * @param name Det nya namnet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sätter föremålets beskrivning.
     * @param itemDesc Den nya beskrivningen
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    // === ÖVRIGA METODER ===

    /**
     * Returnerar text som visas när föremålet kan plockas upp.
     * Kan överskridas av subklasser för specifik text.
     *
     * @return En text som beskriver föremålet på marken
     */
    public String getPickupText() {
        return "Du ser " + name.toLowerCase() + " på golvet";
    }

    /**
     * Använd föremålet. Basklassversionen gör ingenting.
     * Subklasser kan override:a denna metod för specifikt beteende.
     *
     * @param player Spelaren som använder föremålet
     * @return Text som ska visas när föremålet används
     */
    public String use(Player player) {
        return "Ingenting händer.";
    }
}
