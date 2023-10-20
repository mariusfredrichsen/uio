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

antall_simuleringer = 10000

# Variabler som skal oppdateres i oppgave 9:
sum_mål = 0
hjemmeseire = 0
mål_for = {}
mål_mot = {}
for lag in lagliste:
    mål_for[lag] = 0
    mål_mot[lag] = 0

runder = []
nrunder = (len(lagliste) - 1)*2 # runder per sesong
nkamper = len(lagliste)//2      # kamper per runde

# Round-robin-algoritme
# https://en.wikipedia.org/wiki/Round-robin_tournament#Circle_method
lagliste1 = lagliste[:nkamper] # deler laglisten i to
lagliste2 = lagliste[nkamper:] # slik at ett lag fra hver del møtes
for i in range(nrunder):
    runder.append([])
    for j in range(nkamper):
        if i % 2 == 0: # partallsrunde
            kamp = Kamp(lagliste1[j], lagliste2[j]) # laget fra lagliste1 har hjemmekamp
        else: # oddetallsrunde
            kamp = Kamp(lagliste2[j], lagliste1[j]) # laget fra lagliste1 har bortekamp
        runder[i].append(kamp) # legger til kampen i listen
    
    # siste lag fra lagliste1 legges sist i lagliste2
    til_liste1 = lagliste1.pop(-1)
    lagliste2.append(til_liste1)

    # første lag fra lagliste2 legges på indeks 1 i lagliste1
    til_liste2 = lagliste2.pop(0)
    lagliste1.insert(1, til_liste2)

for simulering in range(antall_simuleringer):
    for runde in runder:
        #print()
        for kamp in runde:
            kamp.spill()
            #print(kamp)
            sum_mål += kamp.mål_hjemme() + kamp.mål_borte()
            if kamp.mål_hjemme() > kamp.mål_borte():
                hjemmeseire += 1
            mål_for[kamp.hjemmelag()] += kamp.mål_hjemme()
            mål_for[kamp.bortelag()] += kamp.mål_borte()
            mål_mot[kamp.hjemmelag()] += kamp.mål_borte()
            mål_mot[kamp.bortelag()] += kamp.mål_hjemme()

print()
print("Antall silmuleringer:", antall_simuleringer)
print()

kamper_per_lag = len(runder)*antall_simuleringer
kamper_totalt = len(lagliste)*kamper_per_lag/2      # (det er 2 lag i hver kamp)

poeng = 100
for lag in lagliste:
    snitt_for = round(mål_for[lag]/kamper_per_lag, 2)
    snitt_mot = round(mål_mot[lag]/kamper_per_lag, 2)
    poeng -= ((snitt_for - lag.angrep())**2 + (snitt_mot - lag.forsvar())**2)
    print(lag.navn(), "   ", snitt_for, "   ", snitt_mot)

hjemmeseier_prosent = round(100 * hjemmeseire / kamper_totalt, 2)

print()
print("Snitt mål/kamp: ", round(sum_mål / kamper_totalt, 2))
print("Poeng: ", poeng)
print("Prosent hjemmeseire:", hjemmeseier_prosent)
print()