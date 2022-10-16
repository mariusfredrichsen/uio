class Romskip:
    def __init__(self):
        self._posisjon_venstre = int(900/2)-64
        self._posisjon_topp = 620
        self._level = 1
        self._bilde = "romskip1"
        
    def beveg_hoyre(self):
        if self._posisjon_venstre < 900-64:
            self._posisjon_venstre += 5
    
    def beveg_venstre(self):
        if self._posisjon_venstre > 0:
            self._posisjon_venstre -= 5
    
    def hent_posisjon_venstre(self):
        return self._posisjon_venstre
    
    def hent_posisjon_topp(self):
        return self._posisjon_topp
    
    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))
    
    