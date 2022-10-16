class Stein:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre
    
    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))