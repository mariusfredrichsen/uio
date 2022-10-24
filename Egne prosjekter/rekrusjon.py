liste = [1,2,[3,4,[5,6,[7,8],9],10]]

def åpne(liste):
    ny_liste = []
    for elem in liste:
        if type(elem) == type([]):
            return åpne(elem)
        else:
            ny_liste.append(elem)
    return ny_liste

print(åpne(liste))
