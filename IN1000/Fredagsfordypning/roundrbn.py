from kamp import Kamp
from lag import Lag





def main():
    lagliste = []
    lagliste.append(Lag("Bodø/Glimt  ", 2.6, 1.2))
    lagliste.append(Lag("Brann       ", 1.8, 1.1))
    lagliste.append(Lag("HamKam      ", 1.4, 2.1))
    lagliste.append(Lag("Haugesund   ", 0.9, 1.4))
    lagliste.append(Lag("Molde       ", 2.2, 1.1))
    lagliste.append(Lag("Lillestrøm  ", 1.7, 1.5))
    lagliste.append(Lag("Odd         ", 1.2, 1.3))
    lagliste.append(Lag("Rosenborg   ", 1.3, 1.7))
    lagliste.append(Lag("Sandefjord  ", 1.5, 1.9))
    lagliste.append(Lag("Sarpsborg 08", 1.9, 1.6))
    lagliste.append(Lag("Stabæk      ", 1.0, 1.5))
    lagliste.append(Lag("Strømsgodset", 1.1, 1.3))
    lagliste.append(Lag("Tromsø      ", 1.5, 1.0))
    lagliste.append(Lag("Viking      ", 2.1, 1.4))
    lagliste.append(Lag("Vålerenga   ", 1.4, 1.7))
    lagliste.append(Lag("Aalesund    ", 0.8, 2.2))
    i = len(lagliste)//2
    
    
    runder = []
    lagliste1 = lagliste[:i]
    lagliste2 = lagliste[i:]
    for r in range(30):
        runde = []
        for j in range(len(lagliste1)):
            if r % 2 == 0:
                runde.append(Kamp(lagliste1[j], lagliste2[j]))
            else:
                runde.append(Kamp(lagliste2[j], lagliste1[j]))
        
        lagliste1.insert(1, lagliste2.pop(0))
        lagliste2.append(lagliste1.pop())
        
        runder.append(runde)
    
    r = 1
    for runde in runder:
        print(f"Runde {r}:")
        r += 1
        for kamp in runde:
            kamp.spill()
            print(kamp)
        print("\n")
    
    for lag in lagliste:
        lag.n()
main()