import numpy as np

class Kamp:
    def __init__(self, hjemmelag, bortelag):
        self.hjemmelag = hjemmelag
        self.bortelag = bortelag
        self.mål_hjemme = None
        self.mål_borte = None

    @property
    def hjemmelag(self):
        return self._hjemmelag
    
    @hjemmelag.setter
    def hjemmelag(self, hjemmelag):
        self._hjemmelag = hjemmelag
    
    @property
    def bortelag(self):
        return self._bortelag
    
    @bortelag.setter
    def bortelag(self, bortelag):
        self._bortelag = bortelag
    
    @property
    def mål_hjemme(self):
        return self._mål_hjemme
    
    @mål_hjemme.setter
    def mål_hjemme(self, xGh):
        if xGh == None:
            self._mål_hjemme = None
        else:
            self._mål_hjemme = np.random.poisson(xGh)
    
    @property
    def mål_borte(self):
        return self._bortemål
    
    @mål_borte.setter
    def mål_borte(self, xGb):
        if xGb == None:
            self._bortemål = None
        else:
            self._bortemål = np.random.poisson(xGb)
    
    def spill(self):
        # Henter hjemmefordel fra Eliteserien 2023 (tom runde 24), 58% av målene var mål_hjemme og beregner forventet antall mål_hjemme basert på hjemmelagets angrep og bortelagets forsvar
        hjemmefordel = 1.17
        xGh = (self.hjemmelag.angrep()+self.bortelag.forsvar()) / 2 * hjemmefordel

        # Som for hjemme, bare motsatt
        bortefordel = 0.83
        xGb = (self.bortelag.angrep()+self.hjemmelag.forsvar()) / 2 * bortefordel

        # Antar at målene er poisson-fordelt og randomiserer med forventet hjemme- og mål_borte.
        self.mål_hjemme = np.random.poisson(xGh)
        self.mål_borte = np.random.poisson(xGb)