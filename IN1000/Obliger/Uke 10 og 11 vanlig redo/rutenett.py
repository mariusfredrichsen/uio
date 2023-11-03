from random import randint
from celle import Celle

class Rutenett:
    def __init__(self, rader, kolonner):
        self._ant_rader = rader
        self._ant_kolonner = kolonner
        self._rutenett = self._lag_tomt_rutenett()

    def _lag_tomt_rutenett(self):
        return [self._lag_tom_rad() for _ in range(self._ant_rader)]

    def _lag_tom_rad(self):
        return [None for _ in range(self._ant_kolonner)]

    def fyll_med_tilfeldige_celler(self):
        for rad in range(self._ant_rader):
            for kol in range(self._ant_kolonner):
                self.lag_celle(rad, kol)

    def lag_celle(self, rad, kol):
        self._rutenett[rad][kol] = Celle()
        if not randint(0,2):
            self._rutenett[rad][kol].sett_levende()

    def hent_celle(self, rad, kol):
        if rad < 0 or rad > self._ant_rader - 1 or kol < 0 or kol > self._ant_kolonner - 1:
            return None
        return self._rutenett[rad][kol]

    def tegn_rutenett(self):
        for _ in range(10):
            print()
        for rad in self._rutenett:
            for celle in rad:
                print(celle, end = " ")
            print()

    def _sett_naboer(self, rad, kol):
        for x in range(-1, 2):
            for y in range(-1, 2):
                if abs(x) + abs(y) != 0:
                    celle = self.hent_celle(rad + x, kol + y)
                    if celle:
                        self._rutenett[rad][kol].legg_til_nabo(celle)

    def koble_celler(self):
        for rad in range(self._ant_rader):
            for kol in range(self._ant_kolonner):
                self._sett_naboer(rad, kol)

    def hent_alle_celler(self):
        return [row for kol in self._rutenett for row in kol]

    def antall_levende(self):
        ant_levende = 0
        for celle in self.hent_alle_celler():
            if celle.er_levende():
                ant_levende += 1
        return ant_levende

    def __str__(self):
        self.tegn_rutenett()
        return ""