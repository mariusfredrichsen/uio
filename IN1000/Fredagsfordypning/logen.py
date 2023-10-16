import numpy as np
from lag import Lag

class Kamp:
    def __init__(self, hjemmelag, bortelag):
        self.hjemmelag = hjemmelag
        self.bortelag = bortelag
        self._hjemmemål = None
        self._bortemål = None

    @property
    def hjemmelag(self):
        return self._hjemmelag

    @hjemmelag.setter
    def hjemmelag(self, hjemmelag):
        self._hjemmelag = hjemmelag
    
    def bortelag(self):
        return self._bortelag

    def mål_hjemme(self):
        return self._hjemmemål
    
    def hjemmemål(self, xGh):
        if xGh == None:
            self._hjemmemål = None
        else:
            self._hjemmemål = np.random.poisson(xGh)

    def mål_borte(self):
        return self._bortemål
    
    def bortemål(self, xGb):
        if xGb == None:
            self._bortemål = None
        else:
            self._bortemål = np.random.poisson(xGb)
    
    def spill(self):
        # Henter hjemmefordel fra Eliteserien 2023 (tom runde 24), 58% av målene var hjemmemål og beregner forventet antall hjemmemål basert på hjemmelagets angrep og bortelagets forsvar
        hjemmefordel = 1.17
        xGh = (self.hjemmelag.angrep()+self.bortelag.forsvar()) / 2 * hjemmefordel

        # Som for hjemme, bare motsatt
        bortefordel = 0.83
        xGb = (self.bortelag.angrep()+self.hjemmelag.forsvar()) / 2 * bortefordel

        # Antar at målene er poisson-fordelt og randomiserer med forventet hjemme- og bortemål.
        self._hjemmemål = np.random.poisson(xGh)
        self._bortemål = np.random.poisson(xGb)

