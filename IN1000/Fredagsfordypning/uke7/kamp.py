from lag import Lag
from random import randint


class Kamp:
    def __init__(self, hjemmelag: Lag, bortelag: Lag):
        self.hjemmelag = hjemmelag
        self.bortelag = bortelag
        
        self.antall_spill = 0
        self.hjemmelag_mål = 0
        self.bortelag_mål = 0
        
    def hent_hjemmelag(self):
        return self.hjemmelag
    
    def hent_bortelag(self):
        return self.bortelag
    
    # simulerer greier
    def spill(self):
        self.antall_spill += 1
        self.hjemmelag_mål = randint(0,6)
        self.bortelag_mål = randint(0,6)
        
        hjemme_lag = self.hent_hjemmelag()
        hjemme_lag.øk_antall_kamper()
        hjemme_lag.angrep += self.hjemmelag_mål
        hjemme_lag.forsvar += self.bortelag_mål
        
        borte_lag = self.hent_bortelag()
        borte_lag.øk_antall_kamper()
        borte_lag.angrep += self.bortelag_mål
        borte_lag.forsvar += self.hjemmelag_mål
        
            
    # henter siste resultater fra siste kamp
    def mål_hjemme(self):
        if self.antall_spill == 0:
            return None
        return self.hjemmelag_mål
    
    def mål_morte(self):
        if self.antall_spill == 0:
            return None
        return self.bortelag_mål
    