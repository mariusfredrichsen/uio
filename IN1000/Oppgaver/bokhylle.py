import random as r
class Bok:
    def __init__(self, tittel, dato):
        self._tittel = tittel
        self._dato = dato

    def __str__(self):
        return f"Tittel: {self._tittel}\nUt_Dato: {self._dato}"

class Bokhylle:
    def __init__(self, plass):
        self._plass = plass
        self._boker = []
    
    def erLedigPlass(self):
        if len(self._boker) == self._plass:
            return False
        return True

    def leggTilBok(self, bok):
        self._boker.append(bok)    
    
    def finnBok(self, tittel, ut_dato):
        for boker in self._boker:
            if boker._tittel == tittel and boker._dato == ut_dato: 
                return boker
    
    def skriv_ut(self):
        for i in self._boker:
            print(i)

liste = ["Albert", "1984", "Data", "Pult", "Stol", "Historien om kopper", "Fysikk 1", "Bruksanvisning til høyttalere", "Jakker og klær",  "Kul bok"]
bokhylle1 = Bokhylle(11)
for i in liste:
    bok = Bok(i, r.randint(1900,2022))
    bokhylle1.leggTilBok(bok)

bok1 = Bok("Anker", 2001)
bok2 = Bok("Båter", r.randint(1900,2022))
bokhylle1.skriv_ut()
if bokhylle1.erLedigPlass():
    bokhylle1.leggTilBok(bok1)
else:
    print("Er ikke mer plass")
if bokhylle1.erLedigPlass():
    bokhylle1.leggTilBok(bok2)
else:
    print("Er ikke mer plass")

if bokhylle1.finnBok("Anker", 2000):
    print("fant bok")
else:
    print("fant ikke bok")