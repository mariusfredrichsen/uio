from spillbrett import Spillbrett
import sys



def kjor_spill(spillbrett):
    score = 0
    # oppdater() vil returnere True så lenge sauen lever og runden ikke er over
    while spillbrett.oppdater():
        score = spillbrett._sauer[0].score()

    return score


bane = "testbane2.txt"
spillbrett = Spillbrett(3000)
spillbrett.legg_til_objekter_fra_fil(bane)
kjor_spill()


# Her kan du leke deg med ulike baner og ulike strategier
# det kan være lurt å lage for-løkker som tester ulike hjerner mot ulike baner
# f. eks noe slikt:
# for hjerneklasse in [SuperHjerne, TestHjerne, AnnenHjerne]:
#    .. opprett spillbret og legg til objekter fra fil
#    .. hent ut sauen og endre hjernen til hjerne (spillbrett.hent_sauer()[0].sett_hjerne(hjerneklasse(...))  ?
#    .. sjekk hvor bra score du får ved å kalle kjor_spill()
#    .. kanskje en ny for-løkke rundt ulike baner?
#    .. finn ut hvilken hjerne eller config på en hjerne som funker best på tvers av baner