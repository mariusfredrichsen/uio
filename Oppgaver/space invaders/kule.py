from distutils.command.build_ext import build_ext


class Kule:
    def __init__(self, bilde, posisjon_venstre, posisjon_topp):
        self._bilde = bilde
        self._posisjon_venstre = posisjon_venstre
        self._posisjon_topp = posisjon_topp
        
        self._lever = True
        
    def hent_posisjon_venstre(self):
        return self._posisjon_venstre
    
    def hent_posisjon_topp(self):
        return self._posisjon_topp
    
    def lever(self):
        return self._lever
    
    def treffer(self):
        self._lever = False
    
    def beveg(self):
        self._posisjon_topp -= 10
    
    def tegn(self, skjerm):
        skjerm.blit(self._bilde, (self._posisjon_venstre, self._posisjon_topp))