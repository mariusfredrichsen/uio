from random import randint
from celle import Celle

class Rutenett:
    def __init__(self, rader, kolonner):
        self._ant_rader = rader
        self._ant_kolonner = kolonner
        self._rutenett = self._lag_tomt_rutenett()

    def _lag_tomt_rutenett(self):
        noested_liste = []
        for i in range(self._ant_rader):
            noested_liste.append(self._lag_tom_rad())
        return noested_liste

    def _lag_tom_rad(self):
        liste = []
        for i in range(self._ant_kolonner):
            liste.append(None)
        return liste

    def fyll_med_tilfeldige_celler(self):
        for y in range(len(self._rutenett)):
            for x in range(len(self._rutenett[y])):
                self.lag_celle(y, x)

    def lag_celle(self, rad, kol):
        celle = Celle()
        self._rutenett[rad][kol] = celle
        if randint(0,2) == 0:
            celle.sett_levende()

    def hent_celle(self, rad, kol):
        if rad < 0 or rad > len(self._rutenett)-1 or kol < 0 or kol > len(self._rutenett[rad])-1:
            return None
        return self._rutenett[rad][kol]

    def tegn_rutenett(self):
        for i in range(10):
            print()
        for y in range(len(self._rutenett)):
            for x in range(len(self._rutenett[y])):
                print(self._rutenett[y][x].hent_status_tegn(), end = "")
            print("\n", end = "")

    def _sett_naboer(self, rad, kol):
        hoved_celle = self.hent_celle(rad, kol)
        for y in range(-1,2):
            for x in range(-1,2):
                if (rad + y, kol + x) != (rad, kol):
                    if self.hent_celle(rad + y, kol + x) != None:
                        hoved_celle.legg_til_nabo(self.hent_celle(rad + y, kol + x))

    def koble_celler(self):
        for y in range(len(self._rutenett)):
            for x in range(len(self._rutenett[y])):
                self._sett_naboer(y, x)

    def hent_alle_celler(self):
        liste = []
        for y in range(len(self._rutenett)):
            for x in range(len(self._rutenett)):
                liste.append(self.hent_celle(y, x))
        return liste
                

    def antall_levende(self):
        antall_levende = 0
        for celle in self.hent_alle_celler():
            if celle.er_levende():
                antall_levende += 1
        return antall_levende
