from tkinter import Y
from verden import Verden
import pgzrun


x, y = 200, 110
verden = Verden(x, y)

WIDTH = x*8
HEIGHT = y*8

def draw():
    screen.fill((0, 0, 0))
    for x in range(WIDTH//8):
        for y in range(HEIGHT//8):
            if verden._rutenett._rutenett[x][y].hent_status_tegn() == "O":
                screen.blit("hvit", (x*8, y*8))

def update():
    verden.oppdatering()


pgzrun.go()