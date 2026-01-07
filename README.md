# Dragon Treasure Del 2

Ett textbaserat äventyrsspel där spelaren navigerar genom en dungeon med hjälp av väderstreck, bekämpar monster och samlar skatter.

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
- `p` - Plocka upp föremål (Pickup)
- `d` - Drick hälsodryck (Drink)

## Nya funktioner i Del 2

### Monster
- **Odjur** (8 HP, 1 skada) - Finns i fackla-rummet
- **Drake** (18 HP, 1 skada) - Vaktar skatten bakom den låsta dörren

### Föremål (Items)
- **Svärd** - Ökar skadan med 1 (totalt 2 skada)
- **Nyckel** - Öppnar låsta dörrar
- **Hälsodryck** - Återställer 6 HP
- **Skatt** - Målet med spelet

### Spelmekanik
- Spelaren startar med 10 HP och 1 baseskada
- Strid sker automatiskt när man går in i ett rum med monster
- Spelaren och monstret attackerar varandra tills en dör
- Samla nyckel för att öppna den låsta dörren till draken

## Antaganden

Följande antaganden har gjorts under utvecklingen:

1. **En dörr per riktning** - Det finns aldrig mer än en dörr åt varje väderstreck i ett rum.

2. **Max en låst dörr per rum** - Varje rum kan ha högst en låst dörr.

3. **Konstanta dörrar** - Dörrarna är statiska och förändras inte under spelets gång.

4. **null representerar utgång** - När en dörrs `nextRoom` är `null` betyder det att dörren leder ut ur grottan.

5. **Engelska kommandon** - Vi använder engelska riktningskommandon (n, s, e, w) för bättre kompatibilitet.

6. **Automatisk strid** - Strider startar automatiskt när spelaren går in i ett rum med ett levande monster.

7. **En nyckel öppnar alla låsta dörrar** - Det finns ingen specifik nyckel för varje dörr.

## Projektstruktur

```
dragon-treasure-del-2/
├── README.md
├── .gitignore
├── start-windows.bat
├── start-mac.sh
└── src/
    ├── DragonTreasure.java  - Huvudklass, spelets startpunkt (Johnny)
    ├── Dungeon.java         - Spellogik och navigering (Johnny)
    ├── Room.java            - Rum med monster och items (Arvid)
    ├── Door.java            - Dörr med riktning och låsstatus (Jonas)
    ├── Player.java          - Spelarens HP, skada och inventory (William)
    ├── Monster.java         - Monster med HP och skada (William)
    ├── Item.java            - Basklass för föremål (Jonas)
    ├── Key.java             - Nyckel för låsta dörrar (Jonas)
    ├── Potion.java          - Hälsodryck som helar (Jonas)
    ├── Treasure.java        - Skatt att samla (Jonas)
    └── Weapon.java          - Vapen som ökar skada (Jonas)
```

## Klassdiagram

```
                    ┌─────────────┐
                    │   Monster   │
                    │─────────────│
                    │-name        │
                    │-healthPoints│
                    │-damage      │
                    │-monsterDesc │
                    └─────────────┘
                          │ 0..1
    ┌──────────────┐      │      ┌──────────┐
    │DragonTreasure│──1───┼──1───│  Dungeon │
    │──────────────│      │      │──────────│
    │+setupGame()  │      │      │+playGame()│
    │+endGame()    │      │      └──────────┘
    └──────────────┘      │            │
                          │            │ 1
                    ┌─────┴─────┐      │
                    │   Room    │──────┘
                    │───────────│
                    │-roomDesc  │──1..*──┌──────────┐
                    │-monster   │        │   Door   │
                    │-item      │        │──────────│
                    │+doBattle()│        │-position │
                    │+doNarrative()│     │-locked   │
                    └───────────┘        └──────────┘
                          │ 0..1
                    ┌─────┴─────┐
                    │   Item    │◄───────┬────────┬────────┬────────┐
                    │───────────│        │        │        │        │
                    │-name      │     ┌──┴──┐ ┌───┴──┐ ┌───┴───┐ ┌──┴───┐
                    │-itemDesc  │     │ Key │ │Potion│ │Treasure│ │Weapon│
                    └───────────┘     └─────┘ └──────┘ └───────┘ └──────┘
                          │ 0..*
                    ┌─────┴─────┐
                    │  Player   │
                    │───────────│
                    │-name      │
                    │-healthPoints│
                    │-damage    │
                    │-inventory │
                    └───────────┘
```

## Författare
- Johnny (DragonTreasure, Dungeon) - Spellogik och integration
- Jonas (Door, Item, Key, Potion, Treasure, Weapon) - Item-hierarki
- William (Player, Monster) - Spelarklass och monster
- Arvid (Room) - Rum-funktionalitet med doBattle()
