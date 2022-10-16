from sau import Sau
from gress import Gress
from stein import Stein
from ulv import Ulv

class Spillbrett:
    def __init__(self):
        self._sauer = []
        self._gress = []
        self._steiner = []
        self._ulver = []
    
    def opprett_sau(self, bilde, posisjon_venstre, posisjon_topp):
        sau = Sau(bilde, posisjon_venstre, posisjon_topp)
        self._sauer.append(sau)
    
    def opprett_gress(self, bilde, posisjon_venstre, posisjon_topp):
        gress = Gress(bilde, posisjon_venstre, posisjon_topp)
        self._gress.append(gress)

    def opprett_stein(self, bilde, posisjon_venstre, posisjon_topp):
        stein = Stein(bilde, posisjon_venstre, posisjon_topp)
        self._steiner.append(stein)

    def opprett_ulv(self, bilde, posisjon_venstre, posisjon_topp, brett):
        ulv = Ulv(bilde, posisjon_venstre, posisjon_topp, brett)
        self._ulver.append(ulv)
    
    def hent_sauer(self):
        return self._sauer
    
    def oppdater(self):
        for sau in self._sauer:
            sau.beveg()
        for ulv in self._ulver:
            ulv.beveg()

        for ulv in self._ulver:
            for sau in self._sauer:
                if sau.er_spist() == False and har_kollidert(ulv, sau):
                    sau.blir_spist()
        
        for sau in self._sauer:
            for gress in self._gress:
                if sau.er_spist() == False and har_kollidert(gress, sau):
                    gress.blir_spist()
        
        for sau in self._sauer:
            for stein in self._steiner:
                if har_kollidert(sau, stein):
                    sau.snu()


    
    def tegn(self, skjerm):
        for sau in self._sauer:
            if sau.er_spist() == False:
                sau.tegn(skjerm)
        for gress in self._gress:
            gress.tegn(skjerm)
        for ulv in self._ulver:
            ulv.tegn(skjerm)
        for stein in self._steiner:
            stein.tegn(skjerm)

def har_kollidert(objekt1, objekt2):
    if objekt1.hent_posisjon_venstre() > objekt2.hent_posisjon_venstre()-50:
        if objekt1.hent_posisjon_venstre() < objekt2.hent_posisjon_venstre()+50:
            return True
    if objekt1.hent_posisjon_topp() > objekt2.hent_posisjon_topp()-50:
        if objekt1.hent_posisjon_topp() < objekt2.hent_posisjon_topp()+50:
            return True
    return False
