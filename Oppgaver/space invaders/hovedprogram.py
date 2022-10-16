from monster import Monster
import pgzrun
from spill import Spill

# Dette er prekode som gjoer at pygame-zero fungerer. Ikke endre dette:
WIDTH = 900
HEIGHT = 700

spill = Spill()

# draw() er en metode Pygame Zero kaller hver gang den skal tegne noe på skjermen (som den gjør mange ganger i sekundet)
# Her sier vi at hver gang Pygame Zero skal tegne noe, så vil vi at den skal kalle tegn-metoden til sauen vår
def draw():
    # Tegn først et rektangel (bakgrunnen vår)
    screen.fill((0, 0, 0))
    # Tegn deretter sauen
    spill.tegn(screen)

# update() kalles også mange ganger i sekundet. Her vil vi bevege sauen vår
def update():
    spill.oppdater(keyboard)


pgzrun.go()