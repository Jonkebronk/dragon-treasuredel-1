# Dragon Treasure Del 1

Ett textbaserat äventyrsspel där spelaren navigerar genom en dungeon med hjälp av väderstreck.

## Hur man kör spelet

### Windows
Dubbelklicka på `start-windows.bat`

### Mac/Linux
```bash
chmod +x start-mac.sh
./start-mac.sh
```

### Manuellt
```bash
cd src
javac -encoding UTF-8 *.java
java DragonTreasure
```

## Kommandon i spelet
- `n` - Gå norrut (North)
- `s` - Gå söderut (South)
- `e` - Gå österut (East)
- `w` - Gå västerut (West)

## Antaganden

Följande antaganden har gjorts under utvecklingen:

1. **En dörr per riktning** - Det finns aldrig mer än en dörr åt varje väderstreck i ett rum.

2. **Max en låst dörr per rum** - Varje rum kan ha högst en låst dörr.

3. **Konstanta dörrar** - Dörrarna är statiska och förändras inte under spelets gång (ingen magi som skapar eller tar bort dörrar).

4. **null representerar utgång** - När en dörrs `nextRoom` är `null` betyder det att dörren leder ut ur grottan (spelets slut).

5. **Engelska kommandon** - Vi använder engelska riktningskommandon (n, s, e, w) istället för svenska (n, s, ö, v) för bättre kompatibilitet med olika terminaler.

## Projektstruktur

```
dragon-treasuredel-1/
├── README.md
├── .gitignore
├── start-windows.bat
├── start-mac.sh
└── src/
    ├── DragonTreasure.java  - Huvudklass, spelets startpunkt
    ├── Dungeon.java         - Spellogik och navigering
    ├── Room.java            - Rum med beskrivning och dörrar
    ├── Door.java            - Dörr med riktning och låsstatus
    └── Player.java          - Spelarens information
```

## Författare
- Johnny (DragonTreasure, Dungeon)
- Jonas (Door)
- William (Player)
- Arvid (Room)
