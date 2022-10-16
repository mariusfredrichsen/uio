import random as r 

class Sau:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp

        self._fart_fra_venstre = 1
        self._fart_fra_topp = 1

        self._er_spist = False
    
    def sett_posisjon(self, posisjon_venstre, posisjon_topp):
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
    
    def hent_posisjon_venstre(self):
        return self._posisjon_venstre
    
    def hent_posisjon_topp(self):
        return self._posisjon_topp
    
    def hent_fart_fra_venstre(self):
        return self._fart_fra_venstre
    
    def hent_fart_fra_topp(self):
        return self._fart_fra_topp

    def sett_fart(self, ny_fart_venstre, ny_fart_fra_topp):
        self._fart_fra_venstre = ny_fart_venstre
        self._fart_fra_topp = ny_fart_fra_topp
    
    def beveg(self):
        self._posisjon_venstre += self._fart_fra_venstre
        self._posisjon_topp += self._fart_fra_topp
        if self._posisjon_venstre > 900-50 or self._posisjon_venstre < 0 or self._posisjon_topp < 0 or self._posisjon_topp > 700-50:
            self.snu()
        elif r.randint(1,50) == 1:
            self.sett_fart(r.randint(-1,1),r.randint(-1,1))
    
    def snu(self):
        self._fart_fra_venstre *= -1
        self._fart_fra_topp *= -1

    def er_spist(self):
        return self._er_spist

    def blir_spist(self):
        self._er_spist = True

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))

