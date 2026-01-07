import java.util.ArrayList;

/**
 * Representerar spelaren i Dragon Treasure.
 * UTÖKAD med health, damage och inventory.
 * @author William
 */
public class Player {

    // === ATTRIBUT (private) ===
    private String name;
    private int healthPoints;
    private int damage;
    private ArrayList<Item> inventory;

    // === KONSTRUKTOR ===

    /**
     * Skapar en ny spelare med namn, start-HP och baseskada.
     * @param name Spelarens namn
     */
    public Player(String name) {
        // Standardvärden enligt uppgiftens regler
        this.name = name;
        this.healthPoints = 10;          // spelaren startar med 10 HP
        this.damage = 1;                 // baseskada utan vapen
        this.inventory = new ArrayList<>();
    }

    // === GETTERS ===

    /**
     * Hämtar spelarens namn.
     * @return Namnet
     */
    public String getName() {
        return name;
    }

    /**
     * Hämtar spelarens hälsopoäng.
     * @return HP
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Hämtar spelarens baseskada (utan vapen).
     * @return baseskada
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Hämtar spelarens inventory.
     * @return ArrayList med items
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // === SETTERS ===

    /**
     * Sätter spelarens namn.
     * @param name Det nya namnet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sätter spelarens hälsopoäng.
     * @param healthPoints Nya HP
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Sätter spelarens baseskada.
     * @param damage Ny baseskada
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    // === INVENTORY-HANTERING ===

    /**
     * Lägger till ett föremål i inventory.
     * @param item Föremålet som ska läggas till
     */
    public void addItem(Item item) {
        if (item != null) {
            inventory.add(item);
        }
    }

    /**
     * Tar bort ett föremål från inventory.
     * @param item Föremålet som ska tas bort
     */
    public void removeItem(Item item) {
        inventory.remove(item);
    }

    /**
     * Kontrollerar om spelaren har en nyckel.
     * @return true om inventory innehåller ett Key-objekt
     */
    public boolean hasKey() {
        for (Item item : inventory) {
            if (item instanceof Key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hämtar spelarens potion (om sådan finns).
     * @return Potion-objektet eller null
     */
    public Potion getPotion() {
        for (Item item : inventory) {
            if (item instanceof Potion) {
                return (Potion) item;
            }
        }
        return null;
    }

    /**
     * Hämtar spelarens vapen (om sådant finns).
     * @return Weapon-objektet eller null
     */
    public Weapon getWeapon() {
        for (Item item : inventory) {
            if (item instanceof Weapon) {
                return (Weapon) item;
            }
        }
        return null;
    }

    /**
     * Kontrollerar om spelaren har skatten.
     * @return true om inventory innehåller ett Treasure-objekt
     */
    public boolean hasTreasure() {
        for (Item item : inventory) {
            if (item instanceof Treasure) {
                return true;
            }
        }
        return false;
    }

    // === COMBAT-METODER ===

    /**
     * Beräknar total skada (baseskada + vapenbonus).
     * @return Total skada spelaren gör
     */
    public int getTotalDamage() {
        int total = damage;
        Weapon weapon = getWeapon();
        if (weapon != null) {
            total += weapon.getIncreaseDamage();
        }
        return total;
    }

    /**
     * Spelaren tar skada.
     * @param amount Mängd skada
     */
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0;
        }
    }

    /**
     * Spelaren helar sig.
     * @param amount Mängd HP som återställs
     */
    public void heal(int amount) {
        healthPoints += amount;
        // Om ni har ett max-HP (t.ex. 10), kan ni klampa här:
        // if (healthPoints > 10) healthPoints = 10;
    }

    /**
     * Kontrollerar om spelaren lever.
     * @return true om healthPoints > 0
     */
    public boolean isAlive() {
        return healthPoints > 0;
    }

    /**
     * Dricker en potion från inventory (om sådan finns).
     * Helar spelaren och tar bort potion från inventory.
     * @return true om potion dracks, false om ingen fanns
     */
    public boolean drinkPotion() {
        Potion potion = getPotion();
        if (potion == null) {
            return false;
        }
        // Antag att Potion har en metod getHealing()
        heal(potion.getHealing());
        removeItem(potion);
        return true;
    }
}
