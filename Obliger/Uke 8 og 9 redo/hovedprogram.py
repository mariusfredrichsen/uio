import random as r
from spillbrett import Spillbrett
import pgzrun

# Her lager vi et nytt spillbrett og oppretter to sauer med ulike bilder og ulike start-posisjoner
spillbrett = Spillbrett()
spillbrett.opprett_sau("sau", r.randint(0,900-50), r.randint(0,700-50))
spillbrett.opprett_sau("sau2", r.randint(0,900-50), r.randint(0,700-50))
for i in range(15):
    spillbrett.opprett_gress("gress", r.randint(0,900-50), r.randint(0,700-50))
for i in range(4):
    spillbrett.opprett_stein("stein", r.randint(0,900-50), r.randint(0,700-50))
spillbrett.opprett_ulv("ulv", r.randint(0,900-50), r.randint(0,700-50), spillbrett)


# Dette er prekode som gjør at pygame zero fungerer. Ikke endre dette:
WIDTH = 900
HEIGHT = 700
def draw():
    screen.fill((128, 81, 9))
    spillbrett.tegn(screen)

def update():
    spillbrett.oppdater()

pgzrun.go()