import random as r
import time

def lag_brett():
    brett = []
    for i in range(17):
        brett.append([0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0])
    return brett

def skriv_ut_brett(brett):
    for linje in brett:
        for elem in linje:
            print(elem, end = " ")
        print()

def lag_slange(brett):
    brett[len(brett)//2][len(brett)//2-3] = 1
    return brett

def lag_hale(brett, lengde):
    

brett = lag_brett()
lag_slange(brett)
skriv_ut_brett(brett)