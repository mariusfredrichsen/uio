from sauehjerne import Sauehjerne

class Sau:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp, spillbrett):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._fart_fra_venstre = 2
        self._fart_fra_topp = 2
        self._score = 0
        self._er_spist = False
        self._spillbrett = spillbrett
        self._rute_venstre = self._posisjon_venstre // 50
        self._rute_topp = self._posisjon_topp // 50
        self._retning = ""

        self._sauehjerne = Sauehjerne(self, spillbrett)

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def sett_posisjon(self, ny_posisjon_venstre, ny_posisjon_topp):
        self._posisjon_venstre = ny_posisjon_venstre
        self._posisjon_topp = ny_posisjon_topp

    def sett_fart(self, ny_fart_fra_venstre, ny_fart_fra_topp):
        self._fart_fra_venstre = ny_fart_fra_venstre
        self._fart_fra_topp = ny_fart_fra_topp

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))

    def __repr__(self):
        return "Sau %d,%d, er_spist: %s" % (self._posisjon_venstre, self._posisjon_topp, self._er_spist)

    def retning(self):
        return self._retning

    def tenk(self):
        self._retning = self._sauehjerne.velg_retning()

    def spis(self):
        self._score += 1

    def blir_spist(self):
        self._er_spist = True

    def er_spist(self):
        return self._er_spist

    def rute_venstre(self):
        return self._rute_venstre

    def rute_topp(self):
        return self._rute_topp

    def sett_rute_venstre(self, ny):
        self._rute_venstre = ny

    def sett_rute_topp(self, ny):
        self._rute_topp = ny

    def score(self):
        return self._score

    def legg_til_score(self, s):
        self._score += s

    def sauehjerne(self):
        return self._sauehjerne