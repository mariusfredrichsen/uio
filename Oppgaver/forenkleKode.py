def funksjon(filnavn_liste):
    alle_navn = []
    for filnavn in filnavn_liste:
        f = open(filnavn)
        for line in f:
            navn = line.strip()
            alle_navn.append(navn)
        
    return alle_navn

liste = ["navn1.txt", "navn2.txt", "andre_navn.txt"]
print('\n'.join(sorted(funksjon(liste))))