fil = open("navn.txt")
liste = []

for linje in fil:
    liste.append(linje)

print(liste)