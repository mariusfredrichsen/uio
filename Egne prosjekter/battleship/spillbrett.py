import pygame
import sys
import time

# regler:
# begge to setter opp skipene sine (4: 1x1, 3: 2x1, 2: 3x1, 1: 4x1)
# skipene kan ikke ligge tett i tett, men tett med kanten
# spillerne skal få lov til å rotere skipene sine
# etter at begge to er ferdig skal spiller 1 starte med å skyte, et forsøk hver, men hvis man treffer får man gå igjen
def lag_spillbrett():
    brett = []
    for i in range(10):
        brett.append([0,0,0,0,0,0,0,0,0,0])
    return brett

class Spiller:
    def  __init__(self, nummer):
        self._brett = lag_spillbrett()
        self._nummer = nummer
        self._skip = []
    
    def hent_brett(self):
        return self._brett

spiller1 = Spiller(1)
spiller2 = Spiller(2)

size = width, height = len(spiller1.hent_brett()[0])*65+1+len(spiller2.hent_brett()[0]*65), len(spiller1.hent_brett())*65+1
print(size)
white = 255, 255, 255

screen = pygame.display.set_mode(size)
grid = pygame.image.load("grid.png")

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()
    
    screen.fill(white)
    screen.blit(grid, (0,0))
    pygame.display.flip()