import pygame
import sys
import time

def lag_brett():
    brett = []
    for i in range(6):
        brett.append([0, 0, 0, 0, 0, 0, 0])
    
    return brett

def skriv_ut_brett(brett):
    for i in range(7):
        print(i, end = " ")
    print()
    for liste in brett:
        for elem in liste:
            print(elem, end = " ")
        print()

def sjekk_om_gyldig(tall, brett):
    if tall < 0 or tall > 6:
        return False
    if brett[0][tall] != 0:
        return False
    return True

def velg_kolonne(brett, plassering):
    if sjekk_om_gyldig(plassering, brett):
        return plassering
    return "ugyldig"

def sett_brikke_paa_plass(brett, kol, type):    
    for y in range(1, len(brett)+1):
        if brett[-y][kol] == 0:
            brett[-y][kol] = type
            return brett
    
def sjekk_vinner(brett):    
    # sjekker horisontalt
    for y in range(len(brett)):
        for x in range(len(brett[y])-3):
            if (brett[y][x] == brett[y][x+1] and brett[y][x] == brett[y][x+2] and brett[y][x] == brett[y][x+3]) and brett[y][x] != 0:
                return True
    
    # sjekker vertikalt
    for y in range(len(brett)-3):
        for x in range(len(brett[y])):
            if (brett[y][x] == brett[y+1][x] and brett[y][x] == brett[y+2][x] and brett[y][x] == brett[y+3][x]) and brett[y][x] != 0:
                return True

    # sjekker diagonalt (soor/oost)
    for y in range(len(brett)-3):
        for x in range(len(brett[y])-3):
            if (brett[y][x] == brett[y+1][x+1] and brett[y][x] == brett[y+2][x+2] and brett[y][x] == brett[y+3][x+3]) and brett[y][x] != 0:
                return True
    
    #sjekker diagonalt (soor/vest)
    for y in range(1, len(brett)-2):
        for x in range(len(brett)-3):
            if (brett[-y][x] == brett[-y-1][x+1] and brett[-y][x] == brett[-y-2][x+2] and brett[-y][x] == brett[-y-3][x+3]) and brett[-y][x] != 0:
                return True
            
    return False

def finn_pos(brett):
    # sjekker horisontalt
    for y in range(len(brett)):
        for x in range(len(brett[y])-3):
            if (brett[y][x] == brett[y][x+1] and brett[y][x] == brett[y][x+2] and brett[y][x] == brett[y][x+3]) and brett[y][x] != 0:
                return [(x*100+50,y*100+50), ((x+3)*100+50,y*100+50)]
    
    # sjekker vertikalt
    for y in range(len(brett)-3):
        for x in range(len(brett[y])):
            if (brett[y][x] == brett[y+1][x] and brett[y][x] == brett[y+2][x] and brett[y][x] == brett[y+3][x]) and brett[y][x] != 0:
                return [(x*100+50,y*100+50), (x*100+50,(y+3)*100+50)]

    # sjekker diagonalt (soor/oost)
    for y in range(len(brett)-3):
        for x in range(len(brett[y])-3):
            if (brett[y][x] == brett[y+1][x+1] and brett[y][x] == brett[y+2][x+2] and brett[y][x] == brett[y+3][x+3]) and brett[y][x] != 0:
                return [(x*100+50,y*100+50), ((x+3)*100+50,(y+3)*100+50)]
    
    #sjekker diagonalt (soor/vest)
    for y in range(1, len(brett)-2):
        for x in range(len(brett)-3):
            if (brett[-y][x] == brett[-y-1][x+1] and brett[-y][x] == brett[-y-2][x+2] and brett[-y][x] == brett[-y-3][x+3]) and brett[-y][x] != 0:
                return [(x*100+50,len(brett)*100-y*100+50), ((x+3)*100+50,len(brett)*100-(y+2)*100-50)]
            
    return False


index = 0
brett = lag_brett()

pygame.init()
size = width, height = len(brett[0])*100, len(brett)*100
white = 255,255,255
black = 0,0,0

screen = pygame.display.set_mode(size)

rood = pygame.image.load("red.png")
rood_trans = pygame.image.load("redtransparent.png")
gul = pygame.image.load("yellow.png")
gul_trans = pygame.image.load("yellowtransparent.png")
brett1 = pygame.image.load("brett.png")

while True:
    screen.fill(white)
    pos = pygame.mouse.get_pos()[0]//100, pygame.mouse.get_pos()[1]//100
    for y in range(1,len(brett)+1):
        if brett[-y][pos[0]] == 0:
            y_pos = len(brett)*100-y*100
            break
    if index%2 == 0:
        screen.blit(rood_trans, (pos[0]*100, y_pos))
    if index%2 == 1:
        screen.blit(gul_trans, (pos[0]*100, y_pos))

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            sys.exit()
        if event.type == pygame.MOUSEBUTTONDOWN:
            kol = velg_kolonne(brett, pos[0])
            if kol == "ugyldig":
                break
            brett = sett_brikke_paa_plass(brett, kol, index%2+1)
            index += 1
        
    for y in range(len(brett)):
        for x in range(len(brett[y])):
            if brett[y][x] == 1:
                screen.blit(rood,(x*100,y*100))
            if brett[y][x] == 2:
                screen.blit(gul,(x*100,y*100))
    screen.blit(brett1, (0,0))
    if sjekk_vinner(brett):
        index -= 1
        start, end = finn_pos(brett)
        pygame.draw.line(screen,color=black,start_pos=start,end_pos=end,width=50)
        pygame.display.flip()
        print(f"Spiller.{index%2+1} vant!")
        pygame.time.wait(2500)
        break
    pygame.display.flip()



"""while True:
    while True:
        kol = velg_kolonne(index, brett)
        if kol != "ugyldig":
            break
    brett = sett_brikke_paa_plass(brett, kol, index%2+1)
    skriv_ut_brett(brett)
    if sjekk_vinner(brett):
        break
    index += 1
"""