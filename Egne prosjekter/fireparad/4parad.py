import pgzrun

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

def velg_kolonne(index, brett):
    spiller = index%2+1
    plassering = int(input(f"Hvor vil du plassere brikken din? (Spiller {spiller} sin tur)\n").strip())
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

WIDTH = 700
HEIGHT = 600

def draw():
    screen.fill((0, 0, 0))
    for x in range(WIDTH//100):
        for y in range(HEIGHT//100):
            if brett[x][y].hent_status_tegn() == 1:
                screen.blit("hvit", (x*100, y*100))

def update():
    global index
    index = 0
    brett = lag_brett()
    skriv_ut_brett(brett)
    while True:
        while True:
            kol = velg_kolonne(index, brett)
            if kol != "ugyldig":
                break
        brett = sett_brikke_paa_plass(brett, kol, index%2+1)
        skriv_ut_brett(brett)
        if sjekk_vinner(brett):
            break
        index += 1

pgzrun.go()