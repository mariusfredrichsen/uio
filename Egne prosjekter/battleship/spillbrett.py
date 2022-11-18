import pygame
import sys
import time

size = width, height = 900, 700
speed = [1, 1]
white = 255, 255, 255

screen = pygame.display.set_mode(size)
# regler:
# begge to setter opp skipene sine (4: 1x1, 3: 2x1, 2: 3x1, 1: 4x1)
# skipene kan ikke ligge tett i tett, men tett med kanten
# spillerne skal få lov til å rotere skipene sine
# etter at begge to er ferdig skal spiller 1 starte med å skyte, et forsøk hver, men hvis man treffer får man gå igjen
# 
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
    
    def legg_til_skip(self, type):
        if type == 1:
            pass
        if type == 2:
            pass
        if type == 3:
            pass
        if type == 4:
            pass



















ball = pygame.transform.scale(pygame.image.load("pingu.png"), (100, 100))
ballrect = ball.get_rect()
print(ballrect)

while True:
    pygame.time.Clock().tick(240)
    for event in pygame.event.get():
        if event.type == pygame.QUIT: sys.exit()

    ballrect = ballrect.move(speed)
    if ballrect.left < 0 or ballrect.right > width:
        speed[0] = -speed[0]
    if ballrect.top < 0 or ballrect.bottom > height:
        speed[1] = -speed[1]
    
    screen.fill(white)
    screen.blit(ball, ballrect)
    pygame.display.flip()