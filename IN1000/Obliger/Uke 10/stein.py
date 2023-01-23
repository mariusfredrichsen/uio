
class Stein:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp):
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._bilde = bilde
        self._rute_venstre = self._posisjon_venstre // 50
        self._rute_topp = self._posisjon_topp // 50

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))

    def rute_venstre(self):
        return self._rute_venstre

    def rute_topp(self):
        return self._rute_topp
