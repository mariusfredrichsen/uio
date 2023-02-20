def en_funksjon(multi_mengde):
    tuppel_liste = []
    for i in multi_mengde:
        tuppel_liste.append((i,multi_mengde.count(i)))
    return set(tuppel_liste)

print(en_funksjon([1,1,2,3,4,6,7,6,5,7,8,5,4,3,2,5,3,2,1]))