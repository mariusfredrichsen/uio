import math
import numpy as np
from collections import defaultdict
from random import shuffle, seed
seed(1)

class Ulv():
    def __init__(self, bilde, posisjon_venstre, posisjon_topp, spillbrett):
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._fart_fra_venstre = 0
        self._fart_fra_topp = 0
        self._spillbrett = spillbrett
        self._bredde = 18
        self._hoyde = 14

        self.lag_grid()
        self._korteste_stier = {}
        self._korteste_avstander = np.zeros((18, 14)) + 10000
        self._naboer = defaultdict(list)
        self._parents = {}
        self.finn_alle_naboer()

        self._bilde = bilde
        self.rute_x = self._posisjon_venstre // 50
        self.rute_y = self._posisjon_topp // 50
        self._rute_venstre = self._posisjon_venstre // 50
        self._rute_topp = self._posisjon_topp // 50
        self._retning = ""

    def rute_tilgjengelig(self, x, y):
        return self._grid[x][y] != 1

    def retning(self):
        return self._retning

    def finn_alle_naboer(self):
        for x in range(0, self._bredde):
            for y in range(0, self._hoyde):
                self.finn_naboer_til_rute(x, y)

    def korteste_sti_fra_til(self, fra_x, fra_y, til_x, til_y):
        return self._korteste_stier[(fra_x, fra_y, til_x, til_y)]

    def finn_alle_korteste_stier(self):
        i = 0
        for fra_x in range(0, self._bredde):
            for fra_y in range(0, self._hoyde):
                self.start_sok_fra_rute(fra_x, fra_y)
                i += 1

    def naboer(self, x, y):
        return self._naboer[x, y]

    def finn_naboer_til_rute(self, x, y):
        for i in range(x - 1, x + 2):
            for j in range(y - 1, y + 2):
                if i == x and y == j:
                    continue

                if j != y  and i != x:
                    # Ikke tillatt skrå
                    continue

                if i < 0 or i >= self._bredde or j < 0 or j >= self._hoyde:
                    continue

                if not self.rute_tilgjengelig(i, j):
                    continue

                self._naboer[(x, y)].append((i, j))

    def finn_naermeste_sau(self):
        # Denne er ikke implementert, vi henter bare ut den første sauen i stedet
        return self._spillbrett._sauer[0]

    def tenk(self):
        self._retning = "hoeyre"
        naermeste_sau = self.finn_naermeste_sau()
        if naermeste_sau is None:
            return

        fra_x = round(self._posisjon_venstre // 50)
        fra_y = round(self._posisjon_topp // 50)

        self.start_sok_fra_rute(fra_x, fra_y)

        til_x = round(naermeste_sau.hent_posisjon_venstre() // 50)
        til_y = round(naermeste_sau.hent_posisjon_topp() // 50)
        nokkel = (fra_x, fra_y, til_x, til_y)
        if not nokkel in self._korteste_stier:
            return

        sti = self._korteste_stier[nokkel]
        if len(sti) == 1:
            return
        else:
            neste_rute = sti[1]
            til_x = neste_rute[0] * 50
            til_y = neste_rute[1] * 50
            self._fart_fra_venstre = 0
            if til_x > self._posisjon_venstre:
                self._retning = "hoeyre"
            elif til_x < self._posisjon_venstre:
                self._retning = "venstre"
            elif til_y > self._posisjon_topp:
                self._retning = "ned"
            elif til_y < self._posisjon_topp:
                self._retning = "opp"

    def sett_fart(self, ny_fart_fra_venstre, ny_fart_fra_topp):
        self._fart_fra_venstre = ny_fart_fra_venstre
        self._fart_fra_topp = ny_fart_fra_topp

    def sett_posisjon(self, ny_posisjon_venstre, ny_posisjon_topp):
        self._posisjon_venstre = ny_posisjon_venstre
        self._posisjon_topp = ny_posisjon_topp

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (int(self._posisjon_venstre), int(self._posisjon_topp)))

    def __repr__(self):
        return "Ulv %d,%d" % (self._posisjon_venstre, self._posisjon_topp)

    def _backtrace_fra(self, fra_x, fra_y, til_x, til_y):
        x = fra_x
        y = fra_y

        sti = [(fra_x, fra_y)]
        while x != til_x or y != til_y:
            if (x, y) not in self._parents:
                break

            parent = self._parents[(x, y)]
            sti.append(parent)
            x = parent[0]
            y = parent[1]

        self._korteste_stier[(til_x, til_y, fra_x, fra_y)] = sti[::-1]

    def start_sok_fra_rute(self, x, y):
        self._korteste_avstander = defaultdict(int)
        self._har_besokt = set()

        # Finn korteste avstand til alle ruter
        self.sok_fra_rute(x, y, 0)

        for i in range(0, self._bredde):
            for j in range(0, self._hoyde):
                if i == x and j == y:
                    continue
                if self.rute_tilgjengelig(i, j):
                    self._backtrace_fra(i, j, x, y)

    def sok_fra_rute(self, x, y, avstand_hit):
        # Gå til alle naboruter som kan besøkes fra dennne ruten, stopp hvis avstanden til nå er større enn tidligere funnet avstand
        self._har_besokt.add((x, y))
        self._korteste_avstander[(x, y)] = avstand_hit
        naboer = self.naboer(x, y)

        if len(naboer) == 0:
            return

        # Legg inn en shuffle slik at det blir litt veksling mellom retningene
        shuffle(naboer)

        for nabo in naboer:
            avstand_til_nabo = avstand_hit + math.sqrt((nabo[0] - x)**2 + (nabo[1] - y)**2)
            if nabo in self._har_besokt and self._korteste_avstander[nabo] <= avstand_til_nabo:
                # Ikke fortsett søk via denne naboen, vi har vært der før med kortere avstand
                continue
            else:
                self._parents[(nabo)] = (x, y)
                self.sok_fra_rute(nabo[0], nabo[1], avstand_til_nabo)

    def lag_grid(self):
        grid = np.zeros((18, 14))

        # Fyll inn stein
        for stein in self._spillbrett._steiner:
            x = stein.hent_posisjon_venstre() // 50
            y = stein.hent_posisjon_topp() // 50
            grid[x, y] = 1

        self._grid = grid

    def rute_venstre(self):
        return self._rute_venstre

    def rute_topp(self):
        return self._rute_topp

    def sett_rute_venstre(self, ny):
        self._rute_venstre = ny

    def sett_rute_topp(self, ny):
        self._rute_topp = ny
