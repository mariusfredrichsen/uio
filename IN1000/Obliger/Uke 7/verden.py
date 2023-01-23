from sau import Sau
from ulv import Ulv
import random as r

class Verden:
    def __init__(self):
        self._sauer = []
        self._ulver = [] #Lister for å holde på alle objektene

    def opprett_dyr(self, type, navn, posisjon):
        if type == "sau":
            sau = Sau(navn, posisjon)
            self._sauer.append(sau)
        if type == "ulv":
            ulv = Ulv(navn, posisjon)
            self._ulver.append(ulv) #Lager sauer og ulver og legger dem til i listene

    def beskriv(self):
        print("Sauer:")
        for i in self._sauer:
            print(i.hent_navn(), i.hent_posisjon()) 
        print("\nUlver:")
        for i in self._ulver:
            print(i.hent_navn(), i.hent_posisjon()) #Printer ut alle navna og posisjonene til sauene og ulvene

    def antall_sauer(self):
        n = 0 #Lager en teller
        for i in self._sauer:
            if i.lever():
                n += 1 #Øker telleren med 1 hvis det a sauen lever er sann
        return n
    
    def antall_ulver(self):
        return len(self._ulver) #Det å telle ulver kan man bare gjøre med lengen av listen

    def oppdater(self):
        sjansj = r.randint(0,1) 
        for i in self._ulver:
            if sjansj == 0:
                i.beveg_hoyre()
            else:
                i.beveg_venstre() #Lager en 50/50 sjansj for at den går til høyre eller venstre

        for sau in self._sauer:
            for ulv in self._ulver:
                if sau.hent_posisjon() == ulv.hent_posisjon() and sau.lever() == True:
                    ulv.spis_sau(sau)
                    print(f"Ulven {ulv.hent_navn()} spiser sausen {sau.hent_navn()} på posisjon {ulv.hent_posisjon()}")
        #Går igjennom en og en sau der hvor den går igjennom hver ulv
        #Hvis posisjonen til sauen og ulven er lik så vil den påkalle at ulven spiser sauen

    def finn_feiteste_ulv(self):
        ulven = self._ulver[0] #Har den "største" ulven som første elementet
        for i in self._ulver:
            if ulven.hent_vekt() < i.hent_vekt(): #Hvis den "minste" ulven er mindre enn den ulven for loopen går igjennom får man en ny "største" ulv
                ulven = i
        
        return ulven