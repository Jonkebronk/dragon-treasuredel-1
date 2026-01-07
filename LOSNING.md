# Dragon Treasure Del 2 - Lösning

## Vinnande väg (komplett genomgång)

```
ö → n → p → ö → (odjur) → s → v → p → ö → p → d → ö → (drake) → v → n → ö
```

## Steg-för-steg guide

### 1. Starta spelet
```
Du står utanför en grotta...
> ö
```

### 2. Gå till döda kroppen och plocka upp svärdet
```
När du går in i grottan kollapsar ingången...
> n

Du ser en död kropp på golvet.
Du ser svärd på golvet, du kan plocka upp den [p]
> p
Du tog upp svärd.
```

### 3. Möt odjuret i fackla-rummet
```
> ö

Du ser en brinnande fackla...
Ett odjur dyker upp!
[Strid sker automatiskt - du vinner med 6 HP kvar]
Du besegrar odjur.
Du har 6 hälsopoäng kvar.
```

### 4. Hämta nyckel och hälsodryck
```
> s
Du kommer in i ett fuktigt rum...

> v
Du kommer in i ett rymligt bergrum...
Du ser nyckel på golvet...
> p
Du tog upp nyckel.

> ö
Du kommer in i ett fuktigt rum...
Du ser hälsodryck på golvet...
Dörren mot österut kan nu låsas upp med din nyckel.
> p
Du tog upp hälsodryck.
```

### 5. Drick hälsodryck och möt draken
```
> d
Du dricker hälsodrycken och återfår 6 hälsopoäng.
[Nu har du 12 HP]

> ö
Du låser upp dörren med nyckeln.
Du kommer in i en enorm grotta...
[Drake ASCII-art visas]
En arg drake dyker upp!
[Strid - du vinner!]
Du besegrar draken och samlar skatten.
```

### 6. Lämna grottan som vinnare
```
> v
> n
> ö

[Skatt ASCII-art visas]
Du lämnar grottan med skatten. Grattis, du vann!
```

## Karta

```
            [DEAD BODY] ---- Ö ----> [TORCH] =======> EXIT
            (svärd)                  (odjur)
                 |                       |
                 N                       S
                 |                       |
[OUTSIDE] --Ö--> [ENTRANCE]          [WET ROOM] ==Ö==> [DRAGON ROOM]
 (start)         (ljus)              (dryck)    låst    (drake+skatt)
                 |                       |
                 S                       V
                 |                       |
            [CAVE ROOM] ----- Ö ---------+
            (nyckel)
```

## Tips

1. **Plocka upp svärdet först** - Det ökar din skada från 1 till 2
2. **Hämta nyckeln innan du går österut i wetRoom** - Dörren till draken är låst
3. **Drick hälsodrycken innan du möter draken** - Du behöver extra HP för att överleva
4. **Drake har 18 HP** - Med svärd (2 skada) tar det 9 attacker att besegra den
