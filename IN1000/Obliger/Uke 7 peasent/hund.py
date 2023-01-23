class Hund:
    def __init__(self, alder, vekt):
        self._alder = alder
        self._vekt = vekt
        self._metthet = 10
    
    def hent_alder(self):
        return self._alder
    
    def hent_vekt(self):
        return self._vekt
    
    def spring(self):
        if self._metthet < 5:
            self._vekt -= 1
        else:
            self._metthet -= 1
        
    def spis(self, mengde):
        self._metthet += mengde
        if self._metthet > 7:
            self._vekt += 1
            
        