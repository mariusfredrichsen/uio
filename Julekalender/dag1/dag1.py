f = open("alveliste.txt")

liste = [0]

for linje in f:
    linje = linje.strip()
    print(linje)
    if linje == "":
        liste.append(0)
    else:
        liste[-1] += int(linje)

for i in range(len(liste)):
    for l in range(len(liste)-1):
        if liste[l] > liste[l+1]:
            liste[l],liste[l+1] = liste[l+1],liste[l]

print(liste)

print(liste[-1] + liste[-2] + liste[-3])