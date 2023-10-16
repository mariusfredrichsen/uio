from lag import Lag
import random


class Kamp:
    def __init__(self, hjemmelag, bortelag):
        self.hjemmelag = hjemmelag
        self.bortelag = bortelag
        self.hjemmelag_mål = 0
        self.bortelag_mål = 0
        self.spilt = False
    
    def spill(self):
        for _ in range(6):
            if random.randint(0, 1):
                if self.hjemmelag.angrep() + random.random() > self.bortelag.forsvar() + random.random()/1.21:
                    self.hjemmelag_mål += 1
                if self.bortelag.angrep() + random.random() > self.hjemmelag.forsvar() + random.random():
                    self.bortelag_mål += 1
        self.spilt = True
    
    def mål_hjemme(self):
        if self.spilt:
            return self.hjemmelag_mål
        return None 
    
    def mål_borte(self):
        if self.spilt:
            return self.bortelag_mål
        return None