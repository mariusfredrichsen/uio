import random as r
import matplotlib.pyplot as plt

class Person():
    def __init__(self, navn, status):
        self._navn = navn
        self._syk = status
        self._har_vaert_syk = False
    
    def blir_syk(self):
        if not self._har_vaert_syk:
            self._syk = True
    
    def blir_frisk(self):
        self._syk = False
        self._har_vaert_syk = True
    
    def syk(self):
        return self._syk
    
    def har_vaert_syk(self):
        return self._har_vaert_syk
    
class Verden():
    def __init__(self):
        self._personer = []
    
    def legg_til_person(self, navn, status):
        self._personer.append(Person(navn, status))
    
    def blir_frisk_paa_morgenen(self):
        for person in self._personer:
            if person.syk():
                if r.randint(1,10) == 1:
                    person.blir_frisk()
    
    def blir_tilfeldig_syk(self):
        for person in self._personer:
            if not person.har_vaert_syk():
                if r.randint(1,100) <= 2:
                    person.blir_syk()

    def alle_moter_alle(self):
        for i in range(len(self._personer)):
            for l in range(i+1,len(self._personer)):
                if self._personer[i].syk() or self._personer[l].syk():
                    if r.randint(1,100) <= 1:
                        self._personer[i].blir_syk()
                        self._personer[l].blir_syk()

    def hent_antall_syke(self):
        teller = 0
        for person in self._personer:
            if person.syk():
                teller += 1
        return teller

def hovedprogram():
    antall_syke = []
    verden = Verden()
    for i in range(99):
        verden.legg_til_person("Albert", False)
    verden.legg_til_person("Albert", True)
    antall_syke.append(verden.hent_antall_syke())
    for i in range(100):
        verden.blir_frisk_paa_morgenen()
        verden.blir_tilfeldig_syk()
        verden.alle_moter_alle()
        antall_syke.append(verden.hent_antall_syke())
    plt.plot(antall_syke)
    plt.show()

hovedprogram()
        
    
