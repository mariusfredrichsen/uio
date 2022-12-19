from spillbrett import Spillbrett
from gress import Gress
from ulv import Ulv
import random as r

# Her lager vi et nytt spillbrett og oppretter to sauer med ulike bilder og ulike start-posisjoner
spillbrett = Spillbrett()
spillbrett.opprett_sau("sau", r.randint(125, 725), r.randint(125, 425))
spillbrett.opprett_sau("sau2", r.randint(125, 725), r.randint(125, 425))
spillbrett.opprett_ulv("ulv", r.randint(25,875), r.randint(25,675), spillbrett)
for i in range(10):
    spillbrett.opprett_gress("gress", r.randint(25,875), r.randint(25,675))
for i in range(3):
    spillbrett.opprett_stein("stein", r.randint(25,875), r.randint(25,675))


# Dette er prekode som gjør at pygame zero fungerer. Ikke endre dette:
WIDTH = 900
HEIGHT = 700
def draw():
    screen.fill((128, 81, 9))
    spillbrett.tegn(screen)

def update():
    spillbrett.oppdater()