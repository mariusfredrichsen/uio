import numpy as np

verden =   [[1, 1, 1, 1, 1, 0, 0],
            [0, 1, 1, 0, 1, 1, 1],
            [0, 0, 1, 0, 0, 0, 1],
            [0, 0, 1, 1, 1, 0, 1],
            [0, 1, 1, 0, 1, 1, 1],
            [0, 1, 0, 0, 1, 0, 0],
            [1, 1, 1, 1, 1, 0, 0]]

besøkte_celler = []

def sjekk_rundt(matrise, celle):
    x, y = celle[0], celle[1]
    besøkte_celler.append(celle)

    ledige_celler = []
    try:
        if matrise[x+1][y] == 1 and [x+1, y] not in besøkte_celler:
            ledige_celler.append([x+1, y])
    except:
        pass
    try:
        if matrise[x-1][y] == 1 and [x-1, y] not in besøkte_celler:
            ledige_celler.append([x-1, y])
    except:
        pass
    try:
        if matrise[x][y+1] == 1 and [x, y+1] not in besøkte_celler:
            ledige_celler.append([x, y+1])
    except:
        pass
    try:
        if matrise[x][y-1] == 1 and [x, y-1] not in besøkte_celler:
            ledige_celler.append([x, y-1])
    except:
        pass
    return ledige_celler




def main():
    print(np.matrix(verden))
    start = input("Skriv inn start celle, Eks: '1,2': ").strip().split(",")
    slutt = input("Skriv inn slutt celle, Eks: '0,0': ").strip().split(",")
    start = [int(i) for i in start]
    slutt = [int(i) for i in slutt]

    while slutt not in neste_celle:
        print(neste_celle)
        for elem in neste_celle:
            if elem not in besøkte_celler:
                neste_celle = sjekk_rundt(verden, elem)

    print(np.matrix(verden))
    print(f"besøkte celle: {besøkte_celler}")




main()
