def sorter_etter_karakter(filnavn):
    liste = []
    ordbok = {}

    f = open(filnavn)
    for l in f:
        liste.append(l.strip().split(","))
    for i in liste:
        if i in ordbok:
            pass          #ENDRE  
        else:    
            ordbok[i[1]] = [i[0]]
    print(ordbok)

sorter_etter_karakter("karakter.csv")