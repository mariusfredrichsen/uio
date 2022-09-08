favmat = ["Pizza", "Stekt Ris", "Curry"]

print(favmat)

favmat.remove(favmat[1])

for i in range(len(favmat)):
    favmat[i] = favmat[i].upper()
print(favmat)
