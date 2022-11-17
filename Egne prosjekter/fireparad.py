def lag_brett():
    liste = []
    for i in range(6):
        liste.append([0,0,0,0,0,0,0])
    return liste

def skriv_ut_brett(brett):
    for i in range(1,8):
        print(i, end=" ")
    print()
    for linje in brett:
        for elem in linje:
            print(elem, end=" ")
        print()

def sett_brikke_ned(brett, rute, spiller):
    for i in range(1,len(brett)+1):
        if brett[-i][rute] == 0:
            brett[-i][rute] = spiller
            return brett

def velg_rad(brett, spiller):
    inp = input(f"Spiller.{spiller} velg en av rutene: ")
    if type(inp) != str:
        print("Ugyldig input")
        return velg_rad(brett, spiller)
    if int(inp) < 1 or int(inp) > 7:
        print("Ugyldig input")
        return velg_rad(brett, spiller)
    if brett[0][int(inp)-1] != 0:
        print("Ugyldig input")
        return velg_rad(brett, spiller)
    return sett_brikke_ned(brett, int(inp)-1, spiller)

def fullt_brett(brett):
    for i in range(len(brett[0])):
        if brett[0][i] == 0:
            return False
    return True

def sjekk_vinner(brett):
    # sjekker horisontalt
    for y in range(len(brett)):
        for x in range(len(brett[y])-3):
            if brett[y][x] == brett[y][x+1] and brett[y][x] == brett[y][x+2] and brett[y][x] == brett[y][x+3] and brett[y][x] != 0:
                return True
    
    # sjekker vertikalt
    for y in range(len(brett)-3):
        for x in range(len(brett[y])):
            if brett[y][x] == brett[y+1][x] and brett[y][x] == brett[y+2][x] and brett[y][x] == brett[y+3][x] and brett[y][x] != 0:
                return True
    
    # sjekker diagonalt (sor,ost)
    for y in range(len(brett)-3):
        for x in range(len(brett[y])-3):
            if brett[y][x] == brett[y+1][x+1] and brett[y][x] == brett[y+2][x+2] and brett[y][x] == brett[y+3][x+3] and brett[y][x] != 0:
                return True

    # sjekker diagonalt (sor,vest)
    for y in range(1,len(brett)-2):
        for x in range(len(brett)-3):
            if brett[-y][x] == brett[-y-1][x+1] and brett[-y][x] == brett[-y-2][x+2] and brett[-y][x] == brett[-y-3][x+3] and brett[-y][x] != 0:
                return True
    
    return False

brett = lag_brett()
spiller_index = 0
skriv_ut_brett(brett)

while not fullt_brett(brett):
    brett = velg_rad(brett, spiller_index%2+1)
    skriv_ut_brett(brett)
    if sjekk_vinner(brett):
        break
    spiller_index+=1

print(f"Spiller.{spiller_index%2+1} vant!")