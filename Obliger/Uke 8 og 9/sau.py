import random as r

class Sau:
    def __init__(self, posisjon_venstre, posisjon_topp, bilde):
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._bilde = bilde

        self._fart_fra_venstre = 1
        self._fart_fra_topp = 1

        self._er_spist = False

    def sett_posisjon(self, pos_venstre, pos_topp):
        self._posisjon_venstre = pos_venstre
        self._posisjon_topp = pos_topp
    
    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def hent_fart_fra_venstre(self):
        return self._fart_fra_venstre
    
    def hent_fart_fra_topp(self):
        return self._fart_fra_topp

    def sett_fart(self, ny_fart_venstre, ny_fart_topp):
        self._fart_fra_venstre = ny_fart_venstre
        self._fart_fra_topp = ny_fart_topp
    
    def snu(self):
        self._fart_fra_venstre *= -1
        self._fart_fra_topp *= -1

    def beveg(self):
        self._posisjon_venstre += self._fart_fra_venstre
        self._posisjon_topp += self._fart_fra_topp
        if self._posisjon_venstre < 0 and self._posisjon_venstre > 900-50 and self._posisjon_topp < 0 and self._posisjon_topp > 700-50:
            self.snu()
        elif r.randint(0,100) == 1:
            tall = [-1,1]
            self._fart_fra_venstre *= r.choice(tall)
            self._fart_fra_topp *= r.choice(tall)

    def blir_spist(self):
        self._er_spist = True
    
    def er_spist(self):
        return self._er_spist

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))


    
