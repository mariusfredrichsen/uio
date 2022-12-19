from sau import Sau
from gress import Gress
from stein import Stein
from ulv import Ulv

def har_kollidert(objekt1, objekt2):
    if objekt1.hent_posisjon_venstre()-50 < objekt2.hent_posisjon_venstre() and objekt1.hent_posisjon_venstre()+50 > objekt2.hent_posisjon_venstre():
        return True
    elif objekt1.hent_posisjon_topp()-50 < objekt2.hent_posisjon_topp() and objekt1.hent_posisjon_topp()+50 > objekt2.hent_posisjon_topp():
        return True
    else:
        return False

class Spillbrett:
    def __init__(self):
        self._sauer = []
        self._gress = []
        self._steiner = []
        self._ulver = []
    
    def opprett_sau(self, bilde, posisjon_venstre, posisjon_topp):
        sau = Sau(posisjon_venstre, posisjon_topp, bilde)
        self._sauer.append(sau)
    
    def hent_sauer(self):
        return self._sauer

    def oppdater(self):
        for sau in self._sauer:
            sau.beveg()
        for ulv in self._ulver:
            ulv.beveg()

        for sau in self._sauer:
            if sau.er_spist() == False:
                for ulv in self._ulver:
                    if har_kollidert(ulv, sau) == True:
                        sau.blir_spist()

        for sau in self._sauer:
            if sau.er_spist() == False:
                for gress in self._gress:
                    if har_kollidert(sau, gress) == True:
                        gress.blir_spist()
        
        for sau in self._sauer:
            for stein in self._steiner:
                if har_kollidert(sau, stein) == True:
                    sau.snu()

        
    def tegn(self, skjerm):
        for sau in self._sauer:
            if not sau.er_spist():
                sau.tegn(skjerm)
        
        for gress in self._gress:
            if not gress.er_spist():
                gress.tegn(skjerm)
        
        for stein in self._steiner:
            stein.tegn(skjerm)
        
        for ulv in self._ulver:
            ulv.tegn(skjerm)
    
    def opprett_gress(self, bilde, posisjon_venstre, posisjon_topp):
        gress = Gress(posisjon_venstre, posisjon_topp, bilde)
        self._gress.append(gress)

    def opprett_stein(self, bilde, posisjon_venstre, posisjon_topp):
        stein = Stein(posisjon_venstre, posisjon_topp, bilde)
        self._steiner.append(stein)
    
    def opprett_ulv(self, bilde, posisjon_venstre, posisjon_topp, brett):
        ulv = Ulv(posisjon_venstre, posisjon_topp, bilde, brett)
        self._ulver.append(ulv)
        return ulv