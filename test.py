liste = [3]

def funksjon(liste):
    for i in range(len(liste)):
        liste.append(liste[i]*3)
        liste.append(liste[i]*3+1)
for i in range(10):
    funksjon(liste)
print(set(liste))
