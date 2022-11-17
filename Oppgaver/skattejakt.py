def lag_kart(stoerrelse):
    matrise = []
    for l in range(stoerrelse):
        liste = []
        for i in range(stoerrelse):
            liste.append("O")
        matrise.append(liste)
    return matrise

def skriv_ut_kart(matrise):
    for liste in matrise:
        for elem in liste:
            print(elem, end = " ")
        print()

def velg_posisjon_for_skatt(matrise):
    inp = input("Skriv inn koordinatene for x og y 'x,y':\n").strip().split(",")
    print(inp)
    x, y = int(inp[0]), int(inp[1])

    if y > len(matrise) or x > len(matrise):
        print("Utenfor skattekartet")
        velg_posisjon_for_skatt(matrise)
    else:
        matrise[y][x] = "X"
        return matrise
    
def gjett_skatt_paa_kart(matrise):
    for i in range(3):
        inp = input("Gjett hvor skatten er:\n").strip().split(",")
        x,y = int(inp[0]), int(inp[1])

        if matrise[x][y] == "X":
            print("Whueyyyyy")
            break
        else:
            print(f"Prøv på nytt, {2-i} forsøk igjen.")

def hovedfunksjon():
    kart = lag_kart(int(input("Velg størrelse på kart:\n")))
    skriv_ut_kart(kart)
    kart = velg_posisjon_for_skatt(kart)
    gjett_skatt_paa_kart(kart)

hovedfunksjon()