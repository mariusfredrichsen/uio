def funksjon(liste):
	if liste.count(liste[0]) == len(liste):
		return liste[0]
	return -1

tall_liste1 = [1]
tall_liste2 = [2,1,2]

print(funksjon(tall_liste1))
print(funksjon(tall_liste2))