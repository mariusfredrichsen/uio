alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

"""f = open("input.txt")
prioritet = 0
for linje in f:
    del1 = linje.strip()[:len(linje)//2]
    del2 = linje.strip()[len(linje)//2:]
    
    for bokstav in del1:
        if bokstav in del2:
            prioritet = prioritet + 1 + alfabet.index(bokstav)
            break

print(prioritet)"""


prioritet = 0
f = open("input.txt")
liste = []
for linje in f:
    liste.append(linje.strip())
    if len(liste) == 3:
        print(liste)
        for bokstav in liste[0]:
            if bokstav in liste[1] and bokstav in liste[2]:
                prioritet += alfabet.index(bokstav) + 1
                break
        liste = []

print(prioritet)