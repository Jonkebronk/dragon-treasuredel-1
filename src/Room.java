import java.util.ArrayList;

/**
 * Representerar ett rum i dungeon-spelet.
 * Varje rum har en beskrivning och en lista med dörrar som leder till andra rum.
 * @author Arvid
 */
public class Room {
    
    private String roomDesc;            //Beskrivningen av rummet
    private ArrayList<Door> doors;      //En arraylist med dörrar som kommer finnas
    
    
    //Konstruktor för rummen
    public Room(String roomDesc) {
    this.roomDesc = roomDesc;
    this.doors = new ArrayList<>();
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
        for (Door door : doors){        //For-loopen kollar rummen mot arraylistan och jämför med direction
            if (door.getPosition()== direction){ //Om door-klassens getPosition returnerar samma väderstreck
                return door;                //som getDoor:s parameter returneras en dörr (door) och loopen avslutas
            }
        }
        return null;       //Om loopen tar slut utan att någon matchning returneras null som svar på getDoor (finns ingen dörr)
    }
    
    
   //Metod för spelmotorn att anropa när spelaren kommer in i ett nytt rum 
    //OBS: ANTAGANDE: Alla dörrar är konstanta, det finns ingen magi e dyl som kan göra att dörrar dyker upp eller försvinner
   public void doNarrative() {
       System.out.println(roomDesc); //Först skrivs beskrivningen av rummet ut
       
       if (doors.isEmpty()) {  //Kontrollfunktion enligt defensive programming
           System.out.println("Det finns inga dörrar. Detta är en bugg, kräv pengarna tillbaka!");
       } else {
       ArrayList<String> directions = new ArrayList<>(); //Deklaration av en ny arraylist som kommer innehålla de olika väderstrecken, behöver inte finnas utanför den här metoden
           
           for (Door door : doors) { //En for-loop som igen går igenom vilka dörrar som finns
               if (!door.isLocked()){ //Om dörrens metod isLocked inte returnerar true
                   directions.add(door.getDirectionName() + " [" + door.getCommandChar() + "]"); //Så läggs det till en rad i den nya arraylistan som hämtar väderstrecket samt kommandot för att gå dit från door-klassen
               }
           }
           
           for (Door door : doors) {    //En for-loop som går igenom dörrarna igen
               if (door.isLocked()){   //för att kolla om någon låst dörr finns OBS: ANTAGANDE: Max en låst dörr per rum
                   System.out.println("Det finns en dörr " + door.getDirectionName() + ", men den är låst. Hitta nyckeln!"); //Skriver ut i vilket väderstreck dörren finns och att du måste ha nyckeln
               }
            }
           System.out.println("Du kan gå "); //Garanterat kan du gå någonstans
           
          //En for-loop för att hantera utskriften, vi vet ju inte hur många väderstreck det finns dörrar i (1-4)
           for (int i = 0; i < directions.size(); i++) { //En räknare för att hålla koll på hur många gånger vi loopat
               if (i == directions.size() - 1 && directions.size() > 1) { //Om vi är på sista loopen samt antal väderstreck/dörrar är mer än en så avslutas loopen här med "eller [söderut]"
                   System.out.print (" eller " + directions.get(i));
               } else if (i > 0){           //I annat fall (om det finns 3 eller 4 dörrar) skrivs ett komma ut (efter första loopens output) och sedan ett väderstreck
                   System.out.print(", " + directions.get(i));
                       } else {
                   System.out.print(directions.get(i));  //Det som kommer hända i första loopen eftersom villkoren i de båda if-satserna säger att de hoppas över
               }
           }
           System.out.println();  //En tom rad för bättre formatering
           
           }
       }            
   }