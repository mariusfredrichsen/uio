class Ulv:
    def __init__(self, posisjon_venstre, posisjon_topp, bilde, brett):
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._bilde = bilde
        self._brett = brett

        self._fart_fra_venstre = 1
        self._fart_fra_topp = 1
    
    def hent_posisjon_venstre(self):
        return self._posisjon_venstre

    def hent_posisjon_topp(self):
        return self._posisjon_topp

    def finn_naermeste_sau(self, sauer):
        distanser = []
        for i in sauer:
            if not i.er_spist():
                distanser.append(((self._posisjon_venstre - i.hent_posisjon_venstre())**2 + (self._posisjon_topp - i.hent_posisjon_topp())**2)**0.5)
            else:
                distanser.append(100000)
        naermest = distanser[0]
        for i in distanser:
            if naermest > i:
                naermest = i
        if self._posisjon_venstre > sauer[distanser.index(naermest)].hent_posisjon_venstre():
            self._fart_fra_venstre = -1
        else:
            self._fart_fra_venstre = 1
        if self._posisjon_topp > sauer[distanser.index(naermest)].hent_posisjon_topp():
            self._fart_fra_topp = -1
        else:
            self._fart_fra_topp = 1
        return sauer[distanser.index(naermest)]

    def snu(self):
        self._fart_fra_venstre *= -1
        self._fart_fra_topp *= -1

    def beveg(self):
        if self._posisjon_venstre < 0 and self._posisjon_venstre > 900-50 and self._posisjon_topp < 0 and self._posisjon_topp > 700-50:
            self.snu()
        self.finn_naermeste_sau(self._brett.hent_sauer())
        self._posisjon_venstre += self._fart_fra_venstre
        self._posisjon_topp += self._fart_fra_topp

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))
    

