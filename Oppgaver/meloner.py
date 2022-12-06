from random import randint


class Melon:
    def __init__(self):
        self._alder = 0
    
    def ook_alder(self):
        self._alder += 1
    
    def hent_alder(self):
        return self._alder


class Gaard:
    def __init__(self):
        self._meloner = []
    
    def legg_til_melon(self, melon):
        self._meloner.append(melon)
    
    def oppdater(self):
        for melon in self._meloner:
            if melon.hent_alder() == 1:
                for i in range(10):
                    if randint(1,100) > 50:
                        self.legg_til_melon(Melon())
                self._meloner.pop(0)
            if melon.hent_alder() == 0:
                melon.ook_alder()
    
    def hent_meloner(self):
        return self._meloner

def hovedprogram(antall_aar):
    gaard = Gaard()
    for i in range(10):
        if randint(1,100) > 50:
            gaard.legg_til_melon(Melon())
    
    for i in range(antall_aar):
        gaard.oppdater()
        print(len(gaard.hent_meloner()))

hovedprogram(10)