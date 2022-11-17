import time
from random import randint
import numpy as np

def lag_rutenett(bredde, hoeyde):
    liste = []
    for i in range(hoeyde):
        sub_liste = []
        for l in range(bredde):
            sub_liste.append(randint(0,1))
        liste.append(sub_liste)
    
    return liste

def antall_ikke_null(rutenett):
    teller = 0
    for linje in rutenett:
        teller += len(linje) - linje.count(0)
    return teller

def antall_ikke_null1(rutenett):
  return np.count_nonzero(rutenett != 0)

def gjennomsnitt(rutenett):
    sum = 0
    for linje in rutenett:
        for elem in linje:
            sum += elem
    return sum / (len(rutenett)*len(rutenett[0]))


rutenett = np.array(lag_rutenett(1000, 1000))
start_tid = time.time()
for i in range(1000):
    print(antall_ikke_null1(rutenett))
slutt_tid = time.time()
print("Tid det tok:", slutt_tid-start_tid)
