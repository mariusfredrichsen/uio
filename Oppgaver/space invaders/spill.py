from romskip import Romskip
from monster import Monster
from kule import Kule
import random as r

liste_monster = ["monster1", "monster2", "monster3", "monster4", "monster5"]

class Spill:
    def __init__(self):
        self._monstre = []
        self._oppdatering = 0
        self._forrige_skudd = 1
        self._romskip = Romskip()
        self._kuler = []

    def oppdater(self, keyboard):
        # Sjekk for trykk på keyboard
        if keyboard.left:
            self._romskip.beveg_venstre()
        elif keyboard.right:
            self._romskip.beveg_hoyre()

        if keyboard.space and self._oppdatering - self._forrige_skudd > 10:
            self.skyt()
            self._forrige_skudd = self._oppdatering

        self._oppdatering += 1

        
        for monster in self._monstre:
            if monster.lever() == True:
                monster.beveg()
        
        for kule in self._kuler:
            kule.beveg()
        
        if r.randint(1,20) == 1:
            monster = Monster(r.choice(liste_monster), 0, 0, r.randint(1,5))

        self._oppdatering += 1

    def skyt(self):
        kule = Kule("kule1", self._romskip.hent_posisjon_venstre() + 32 - 12, self._romskip.hent_posisjon_topp() - 24)
        self._kuler.append(kule)
        return

    def tegn(self, skjerm):
        self._romskip.tegn(skjerm)
        for kule in self._kuler:
            kule.tegn(skjerm)
        for monster in self._monstre:
            monster.tegn(skjerm)