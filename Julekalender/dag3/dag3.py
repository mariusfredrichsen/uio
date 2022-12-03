alfabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

f = open("input.txt")
prioritet = 0
for linje in f:
    del1 = linje.strip()[:len(linje)//2]
    del2 = linje.strip()[len(linje)//2:]
    
    for bokstav in del1:
        if bokstav in del2:
            prioritet = prioritet + 1 + alfabet.index(bokstav)
            break

print(prioritet)