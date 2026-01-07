# Korrigeringar - Dragon Treasure Del 2

Detta dokument beskriver de korrigeringar som gjordes vid integration av gruppmedlemmarnas kod.

---

## William - Monster.java

### Problem
Filen hade en **dubbel klassdeklaration** och ett extra avslutande klammerparentes.

### Original (felaktig kod)
```java
public class Monster {        // <-- RAD 1: Extra klassdeklaration
/**
 * Representerar ett monster i spelet.
 * @author William
 */
public class Monster {        // <-- RAD 6: Korrekt klassdeklaration
    // ... resten av koden ...
}

}                             // <-- RAD 127-128: Extra }
```

### Korrigering
- **Tog bort rad 1** (`public class Monster {`)
- **Tog bort rad 127-128** (extra `}`)

### Trolig orsak
Copy-paste-fel vid kopiering av koden.

### Lärdom
Kontrollera alltid att varje fil endast har **en** klassdeklaration och att antalet `{` matchar antalet `}`.

---

## Arvid - Room.java

### Problem
Filen hade en **package-deklaration** som inte används i projektet.

### Original (felaktig kod)
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt...
 */

package room;    // <-- RAD 6: Ska tas bort

import java.util.ArrayList;
// ... resten av koden ...
```

### Korrigering
- **Tog bort rad 6** (`package room;`)
- **Tog bort NetBeans-kommentarerna** (rad 1-4)

### Trolig orsak
NetBeans lägger automatiskt till `package`-deklaration när man skapar filer i ett projekt med package-struktur.

### Lärdom
När man delar kod mellan olika projekt, kontrollera att:
1. `package`-deklarationer matchar projektets struktur
2. IDE-specifika kommentarer tas bort
3. Alla klasser kan hittas av kompilatorn

---

## Jonas - Item.java

### Problem
Filen hade en **felaktig javadoc-kommentar** med ett extra tecken.

### Original (felaktig kod)
```java
// === ÖVRIGA METODER ===

/**19                    // <-- RAD 80: "19" ska inte vara där
 *
 * Returnerar text som visas när föremålet kan plockas upp.
```

### Korrigering
- **Ändrade rad 80** från `/**19` till `/**`

### Trolig orsak
Troligtvis tangentbordsmiss vid skrivning.

### Lärdom
Javadoc-kommentarer ska alltid börja med exakt `/**` (snedstreck, asterisk, asterisk).

---

## Sammanfattning av korrigeringar

| Fil | Problem | Korrigering |
|-----|---------|-------------|
| Monster.java | Dubbel klassdeklaration + extra `}` | Ta bort rad 1 och rad 127-128 |
| Room.java | `package room;` deklaration | Ta bort rad 6 |
| Item.java | `/**19` istället för `/**` | Ändra till `/**` |

---

## Tips för framtiden

1. **Kompilera ofta** - Kör `javac *.java` efter varje ändring för att hitta fel tidigt.

2. **Använd konsekvent IDE** - Om gruppen använder samma IDE minskar risken för kompatibilitetsproblem.

3. **Granska kod innan delning** - Läs igenom koden snabbt innan du skickar den.

4. **Ta bort package-deklarationer** - Om projektet inte använder packages, ta bort `package`-raden.

5. **Kontrollera syntaxen** - Se till att alla `{` har matchande `}` och att javadoc börjar med `/**`.
