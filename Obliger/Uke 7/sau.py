class Sau:
    def __init__(self, navn, posisjon):
        self._navn = navn
        self._posisjon = posisjon
        self._lever = True

    def blir_spist(self):
        self._lever = False
    
    def lever(self):
        if self._lever:
            return True
        else:
            return False
    
    def hent_navn(self):
        return self._navn

    def hent_posisjon(self):
        return self._posisjon

    