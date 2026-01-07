import java.util.ArrayList;

/**
 * Representerar ett rum i dungeon-spelet.
 * Varje rum har en beskrivning och en lista med dörrar som leder till andra rum.
 * @author Arvid
 */
public class Room {

    private String roomDesc; //Beskrivningen av rummet
    private ArrayList<Door> doors; //En arraylist med dörrar som kommer finnas
    private Monster monster; //Monstervariabel
    private Item item; //Itemvariabel
    private boolean showDoors = true; //Om dörrar ska visas i doNarrative

    //Konstruktor för rummen
    public Room(String roomDesc) {
        this.roomDesc = roomDesc;
        this.doors = new ArrayList<>();
        this.monster = null; //Nu kan det finnas monster och items i rummet också
        this.item = null;
    }

    //Setter för att sätta rummets beskrivning
    public void setRoom(String roomDesc){
        this.roomDesc = roomDesc;
    }

    //Getter för att hämta rummets beskrivning
    public String getRoomDesc(){
        return roomDesc;
    }

    //Metod för att lägga till dörrar på arraylistan/till rummen
    public void addDoor(Door door){
        doors.add(door);
    }

    //Getter för att hämta dörrarna från arraylistan till ett visst rum
    public ArrayList<Door> getDoors(){
        return doors;
    }

    /*Getter för att hämta en dörr i ett visst väderstreck. OBS: ANTAGANDE:
    Det kommer aldrig att finnas mer än en dörr åt varje väderstreck i ett rum*/
    public Door getDoor(char direction){
        for (Door door : doors){ //For-loopen kollar rummen mot arraylistan och jämför med direction
            if (door.getPosition() == direction){ //Om door-klassens getPosition returnerar samma väderstreck
                return door; //som getDoor:s parameter returneras en dörr (door) och loopen avslutas
            }
        }
        return null; //Om loopen tar slut utan att någon matchning returneras null som svar på getDoor (finns ingen dörr)
    }

    //Monstergetter
    public Monster getMonster(){
        return monster;
    }

    //Monstersetter
    public void setMonster(Monster monster){
        this.monster = monster;
    }

    //Itemgetter
    public Item getItem(){
        return item;
    }

    //Itemsetter
    public void setItem(Item item){
        this.item = item;
    }

    //Metod för att ta bort monstret när det dött
    public void removeMonster(){
        this.monster = null;
    }

    //Metod för att ta bort en item när den plockats upp
    public void removeItem(){
        this.item = null;
    }

    //Metod för att kontrollera om rummet har ett monster och det lever
    public boolean hasMonster(){
        return monster != null && monster.isAlive();
    }

    //Metod för att kolla om rummet har en item
    public boolean hasItem(){
        return item != null;
    }

    //Setter för att dölja dörrar i doNarrative (används för start-rummet)
    public void setShowDoors(boolean showDoors){
        this.showDoors = showDoors;
    }

    //Metod för spelmotorn att anropa när spelaren kommer in i ett nytt rum
    //OBS: ANTAGANDE: Alla dörrar är konstanta, det finns ingen magi e dyl som kan göra att dörrar dyker upp eller försvinner
    public void doNarrative() {
        doNarrative(null);
    }

    public void doNarrative(Player player) {
        System.out.println(roomDesc); //Först skrivs beskrivningen av rummet ut

        // Om det finns ett monster, visa bara rumsbeskrivningen - monstret attackerar direkt
        if (hasMonster()) {
            return;
        }

        if (hasItem()){ //Kolla om det finns en item i rummet
            System.out.println("Du ser " + item.getName().toLowerCase() + " på golvet, du kan plocka upp den [p]");
        }

        // Om showDoors är false, visa inte dörrar (t.ex. start-rummet har redan instruktioner)
        if (!showDoors) {
            return;
        }

        if (doors.isEmpty()) { //Kontrollfunktion enligt defensive programming
            System.out.println("Det finns inga dörrar. Detta är en bugg, kräv pengarna tillbaka!");
        } else {
            ArrayList<String> directions = new ArrayList<>(); //Deklaration av en ny arraylist som kommer innehålla de olika väderstrecken, behöver inte finnas utanför den här metoden

            for (Door door : doors) { //En for-loop som igen går igenom vilka dörrar som finns
                if (!door.isLocked()){ //Om dörrens metod isLocked inte returnerar true
                    directions.add(door.getDirectionName() + " [" + door.getCommandChar() + "]"); //Så läggs det till en rad i den nya arraylistan som hämtar väderstrecket samt kommandot för att gå dit från door-klassen
                }
            }

            for (Door door : doors) { //En for-loop som går igenom dörrarna igen
                if (door.isLocked()){ //för att kolla om någon låst dörr finns OBS: ANTAGANDE: Max en låst dörr per rum
                    if (player != null && player.hasKey()){ //Kollar om spelaren har en nyckel och låser upp dörren
                        System.out.println("Dörren mot " + door.getDirectionName() + " kan nu låsas upp med din nyckel. Välj [" + door.getCommandChar() + "] för att gå dit.");
                    } else {
                        System.out.println("Du ser en låst dörr " + door.getDirectionName() + ". Hitta nyckeln!"); //Skriver ut i vilket väderstreck dörren finns och att du måste ha nyckeln
                    }
                }
            }

            System.out.print("Du kan gå "); //Garanterat kan du gå någonstans

            //En for-loop för att hantera utskriften, vi vet ju inte hur många väderstreck det finns dörrar i (1-4)
            for (int i = 0; i < directions.size(); i++) { //En räknare för att hålla koll på hur många gånger vi loopat
                if (i == directions.size() - 1 && directions.size() > 1) { //Om vi är på sista loopen samt antal väderstreck/dörrar är mer än en så avslutas loopen här med "eller [söderut]"
                    System.out.print(" eller " + directions.get(i));
                } else if (i > 0){ //I annat fall (om det finns 3 eller 4 dörrar) skrivs ett komma ut (efter första loopens output) och sedan ett väderstreck
                    System.out.print(", " + directions.get(i));
                } else {
                    System.out.print(directions.get(i)); //Det som kommer hända i första loopen eftersom villkoren i de båda if-satserna säger att de hoppas över
                }
            }
            System.out.println(); //En tom rad för bättre formatering
        }
    }

    //Stridsfunktionen
    public boolean doBattle(Player player){
        if(!hasMonster()){ //Om det inte finns ett monster blir det ingen strid
            return true;
        }

        System.out.println(monster.getMonsterDesc()); //Presentera monstret

        while (monster.isAlive() && player.isAlive()){ //Håll igång fajten så länge båda lever
            System.out.println(monster.getAttackText()); //Monstret attackerar
            player.takeDamage(monster.getDamage()); //Och spelaren skadas

            if (player.isAlive()) { //Lever spelaren fortfarande?
                System.out.println("Du attackerar " + monster.getName().toLowerCase() + " och gör " + player.getTotalDamage() + " skada."); //Isåfall motattack
                monster.takeDamage(player.getTotalDamage());
            }
        }

        if (player.isAlive()){
            System.out.println("Du besegrar " + monster.getName().toLowerCase() + "."); //Lever spelaren efter fajten så har monstret besegrats
            removeMonster(); //Och tas bort från rummet
            System.out.println("Du har " + player.getHealthPoints() + " hälsopoäng kvar."); //Visa hur mycket hälsa som finns kvar
            return true; //Lever alltså fortfarande
        } else {
            System.out.println("Du dog. Bättre lycka i nästa liv!");
            return false; //Fortfarande död
        }
    }
}
