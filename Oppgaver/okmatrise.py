eksempel_matrise = [[0, 1, 2], [3, 2, 1], [1, 1, 0]]

def byttliste(liste, tall):
    for i in range(len(liste)):
        liste[i] = tall

def byttmatrise(matrise):
    for i in range(len(matrise)):
        for l in range(len(matrise[i])):
            matrise[i][l]+=(i+1)
    return matrise

def matriseprint(matrise):
    for i in matrise:
        print(i)

matriseprint(byttmatrise(eksempel_matrise))