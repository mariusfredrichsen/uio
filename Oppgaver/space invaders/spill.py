from romskip import Romskip
from monster import Monster
from kule import Kule
import random as r

liste_monster = ["monster1", "monster2", "monster3", "monster4", "monster5"]
liste_romskip = ["romskip1", "romskip2", "romskip3"]

class Spill:
    def __init__(self):
        self._monstre = []
        self._oppdatering = 0
        self._forrige_skudd = 1
        self._romskip = Romskip(liste_romskip[0])
        self._kuler = []
        self._score = 0

    def oppdater(self, keyboard):
        # Sjekk for trykk på keyboard
        if keyboard.left:
            self._romskip.beveg_venstre()
        elif keyboard.right:
            self._romskip.beveg_hoyre()

        if self._score > 100:
            self._romskip = Romskip([2])
        elif self._score > 50:
            self._romskip = Romskip([1])

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
            monster = Monster(r.choice(liste_monster), 0, 0, r.randint(3,10))
            self._monstre.append(monster)

        for monster in self._monstre:
            if not monster.lever():
                continue

            for kule in self._kuler:
                if not kule.lever():
                    continue
                if kule.hent_posisjon_venstre() >= monster.hent_posisjon_venstre() and kule.hent_posisjon_venstre() < monster.hent_posisjon_venstre() + 64 - 24:
                    if kule.hent_posisjon_topp() > monster.hent_posisjon_topp() and kule.hent_posisjon_topp() < monster.hent_posisjon_topp() + 64:
                        monster.blir_truffet_av_kule()
                        kule.treffer()
                        if monster.lever() == False:
                            self._score += 1
                            print(f"ny score: {self._score}")

        self._oppdatering += 1

    def skyt(self):
        kule = Kule("kule1", self._romskip.hent_posisjon_venstre() + 32 - 12, self._romskip.hent_posisjon_topp() - 24)
        self._kuler.append(kule)
        return

    def tegn(self, skjerm):
        self._romskip.tegn(skjerm)
        for kule in self._kuler:
            if kule.lever():
                kule.tegn(skjerm)
        for monster in self._monstre:
            if monster.lever():
                monster.tegn(skjerm)

