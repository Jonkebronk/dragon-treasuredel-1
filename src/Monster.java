/**
 * Representerar ett monster i spelet.
 * @author William
 */
public class Monster {

    // === ATTRIBUT (private) ===
    private String name;
    private int healthPoints;
    private int damage;
    private String monsterDesc;

    // === KONSTRUKTOR ===
    /**
     * Skapar ett nytt monster.
     * @param name Monstrets namn, t.ex. "Odjur" eller "Drake"
     * @param healthPoints Antal HP (Odjur: 8, Drake: 18)
     * @param damage Skada per attack (normalt 1)
     * @param monsterDesc Beskrivning, t.ex. "Ett odjur attackerar dig!"
     */
    public Monster(String name, int healthPoints, int damage, String monsterDesc) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.monsterDesc = monsterDesc;
    }

    // === GETTERS ===

    /**
     * Hämtar monstrets namn.
     * @return Namnet
     */
    public String getName() {
        return name;
    }

    /**
     * Hämtar monstrets hälsopoäng.
     * @return HP
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Hämtar monstrets skada.
     * @return Skada per attack
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Hämtar monstrets beskrivning.
     * @return Beskrivningen
     */
    public String getMonsterDesc() {
        return monsterDesc;
    }

    // === SETTERS ===

    /**
     * Sätter monstrets namn.
     * @param name Det nya namnet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sätter monstrets hälsopoäng.
     * @param healthPoints Nya HP
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Sätter monstrets skada.
     * @param damage Ny skada
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sätter monstrets beskrivning.
     * @param monsterDesc Ny beskrivning
     */
    public void setMonsterDesc(String monsterDesc) {
        this.monsterDesc = monsterDesc;
    }

    // === COMBAT-METODER ===

    /**
     * Monstret tar skada.
     * @param amount Mängd skada som tas
     */
    public void takeDamage(int amount) {
        healthPoints -= amount;
        if (healthPoints < 0) {
            healthPoints = 0; // undvik negativa HP
        }
    }

    /**
     * Kontrollerar om monstret lever.
     * @return true om healthPoints > 0
     */
    public boolean isAlive() {
        return healthPoints > 0;
    }

    /**
     * Returnerar attacktext för utskrift i striden.
     * @return T.ex. "Ett odjur attackerar dig och gör 1 skada"
     */
    public String getAttackText() {
        return "Ett " + name.toLowerCase() + " attackerar dig och gör " + damage + " skada";
    }
}
