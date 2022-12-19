class Sau:
    def __init__(self, navn, posisjon):
        self._navn = navn
        self._posisjon = posisjon
        self._lever = True

    def blir_spist(self):
        self._lever = False #Det at sauen lever er usann
    
    def lever(self):
        if self._lever:
            return True
        else:
            return False #Hvis det at sauen lever er sann vil den returnere sann ellers usann
    
    def hent_navn(self):
        return self._navn #Gir navnet

    def hent_posisjon(self):
        return self._posisjon #Gir posisjon

    