class Monster:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp, antall_liv):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._antall_liv = antall_liv
        self._retning = 1
        self._lever = True

    def lever(self):
        return self._lever
    
    def blir_skutt(self):
        self._antall_liv -= 1
        if self._antall_liv == 0:
            self._lever = False

    def beveg(self):
        if self._retning == 1:
            self._posisjon_venstre += 4
            if self._posisjon_venstre >= 900 - 64:
                self._posisjon_topp += 64
                self._retning = -1
        else:
            self._posisjon_venstre -= 4
            if self._posisjon_venstre <= 0:
                self._retning = 1
                self._posisjon_topp += 64

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
       return self._posisjon_topp