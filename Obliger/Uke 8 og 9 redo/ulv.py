

class Ulv:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp, brett):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        self._brett = brett

        self._fart_fra_venstre = 1
        self._fart_fra_topp = 1

    def hent_posisjon_venstre(self):
        return self._posisjon_venstre
    
    def hent_posisjon_topp(self):
        return self._posisjon_topp
    
    def finn_naermeste_sau(self, sauer):
        levende_sauer = []
        for sau in sauer:
            if not sau.er_spist():
                levende_sauer.append(sau)
        
        distanser = []
        for sau in levende_sauer:
            distanser.append(((sau.hent_posisjon_venstre()-self._posisjon_venstre)**2 + (sau.hent_posisjon_topp()-self._posisjon_topp)**2)**0.5)
        
        naermeste = distanser[0]
        for i in distanser:
            if i < naermeste:
                naermeste = i
        
        return levende_sauer[distanser.index(naermeste)]

    def beveg(self):
        if self._posisjon_venstre > 900-50 or self._posisjon_venstre < 0 or self._posisjon_topp < 0 or self._posisjon_topp > 700-50:
            self.snu()
        naermeste_sau = self.finn_naermeste_sau(self._brett.hent_sauer())
        if naermeste_sau.hent_posisjon_venstre() < self._posisjon_venstre:
            self._fart_fra_venstre = -1
        elif naermeste_sau.hent_posisjon_venstre() > self._posisjon_venstre:
            self._fart_fra_venstre = 1
        else:
            self._fart_fra_venstre = 0
        if naermeste_sau.hent_posisjon_topp() < self._posisjon_topp:
            self._fart_fra_topp = -1
        elif naermeste_sau.hent_posisjon_topp() > self._posisjon_topp:
            self._fart_fra_topp = 1
        else:
            self._fart_fra_topp = 0

        self._posisjon_venstre += self._fart_fra_venstre
        self._posisjon_topp += self._fart_fra_topp

    def snu(self):
        self._fart_fra_venstre *= -1
        self._fart_fra_topp *= -1

    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))