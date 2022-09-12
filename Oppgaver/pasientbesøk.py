def maxbesøk(liste):
    maxliste = []
    max = 0
    for i in liste:
        if len(i) > max:
            max = len(i)
            maxliste = i
    return maxliste, liste.index(maxliste)+1

def faerrestBesok(liste):
    minliste = liste[0]
    min = len(liste[0])
    for i in liste:
        if len(i) < min:
            min = len(i)
            minliste = i
    return minliste, liste.index(minliste)+1
    
def allBesok(liste):
    n = 0
    for i in liste:
        for l in i:
            n+=1
    return n

def hvemVarPaaDato(tall, liste):
    varpaabesok = []
    for i in liste:
        if tall in i:
            varpaabesok.append(liste.index(i)+1)
    return varpaabesok

listeEn = [[31], [14, 15, 16, 17, 18]]
listeTo = [[1,2,3], [2, 3, 4], [28, 31], [14, 15, 16, 17, 18]]
listeTre = [[1,2,3], [2, 3, 4], [28, 31], [14, 15, 16, 17, 18], [1, 2, 4, 5], [9, 12, 16, 19], [21, 23, 25, 27, 28]]
print(maxbesøk(listeTre))
print(faerrestBesok(listeTre))
print(allBesok(listeTre))
print(hvemVarPaaDato(2,listeTre))


