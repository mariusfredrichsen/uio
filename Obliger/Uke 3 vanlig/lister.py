liste = [1,2,3]
liste.append(4)
print(f"Første element: {liste[0]}\nTredje element: {liste[2]}")

navnListe = []
for i in range(4):
    navnListe.append(input("Skriv inn et navn:\n").lower())
if "marius" in navnListe:
    print("Du husket meg!")
else:
    print("Glemte du meg?")

prod = 1
sum = 0
for i in liste:
    prod*=i
    sum+=i

nyliste = []
nyliste.append(prod)
nyliste.append(sum)
print(nyliste)

