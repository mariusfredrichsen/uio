from spillbrett import Spillbrett
import pgzrun

spillbrett = Spillbrett(3000)
spillbrett.legg_til_objekter_fra_fil("testbane2.txt")


# Dette er prekode som gjoer at pygame-zero fungerer. Ikke endre dette:
WIDTH = 900
HEIGHT = 700

def draw():
    screen.fill((128, 81, 9))
    spillbrett.tegn(screen)

def update():
    spillbrett.oppdater()

pgzrun.go()