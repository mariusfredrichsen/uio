from lag import Lag
from kamp import Kamp

# dette er listen over lag som skal spille (representert ved Lag-objekter)
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

# Gjennomsnittlig 3.04 mål per kamp gjør at et gjennomsnittlig lag blir slik:
snitt = Lag("SNITT", 1.52, 1.52)

antall_simuleringer = 100000

print()
print("Antall silmuleringer:", antall_simuleringer)
print()
sum_mål = 0
poeng = 100
for lag in lagliste:
    sum_for = 0
    sum_mot = 0
    for i in range(antall_simuleringer):
        kampliste = [Kamp(lag, snitt),
                    Kamp(snitt, lag)]
    
        for kamp in kampliste:
            kamp.spill()
            if antall_simuleringer == 1:
                print(kamp)

        sum_for += kampliste[0].mål_hjemme() + kampliste[1].mål_borte()  
        sum_mot += kampliste[0].mål_borte() + kampliste[1].mål_hjemme()

    snitt_for = round(0.5*sum_for/antall_simuleringer, 2)
    snitt_mot = round(0.5*sum_mot/antall_simuleringer, 2)

    poeng -= ((snitt_for - lag.angrep())**2 + (snitt_mot - lag.forsvar())**2)
    
    print(lag.navn(), "   ", snitt_for, "   ", snitt_mot)

    sum_mål += sum_for + sum_mot
    sum_kamper = len(lagliste)*2*antall_simuleringer
print()
print("Snitt mål/kamp: ", round(sum_mål / sum_kamper, 2))
print("Poeng: ", poeng)