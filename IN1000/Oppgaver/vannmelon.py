from random import randint
import matplotlib.pyplot as plt

class Melon:
    def __init__(self):
        self._alder = 0
    
    def hent_alder(self):
        return self._alder
    
    def oek_alder(self):
        self._alder += 1
    
class Bondegaard:
    def __init__(self):
        self._meloner = []
    
    def legg_til_melon(self):
        self._meloner.append(Melon())
        
    def fjern_melon(self):
        for melon in self._meloner:
            if melon.hent_alder() == 1:
                self._meloner.remove(melon)
                break
    
    def hent_meloner(self):
        return self._meloner

    def oppdater(self):
        for melon in self._meloner:
            if melon.hent_alder() == 1:
                for i in range(10):
                    if randint(1,100) < 35:
                        self.legg_til_melon()
            if melon.hent_alder() == 0:
                melon.oek_alder()
        print(len(self.hent_meloner()))
    
def hovedprogram(antall_aar):
    bondegaard = Bondegaard()
    for i in range(10):
        bondegaard.legg_til_melon()
    
    for i in range(antall_aar):
        bondegaard.oppdater()
    
    print(len(bondegaard.hent_meloner()))

hovedprogram(10)