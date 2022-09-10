liste = [42,72,39]
liste.append(90) #Legger til tallet 90 på slutten av listen
print(f"Første element: {liste[0]}\nTredje element: {liste[2]}") #printer den første og andre elementet i liste

navnliste = []
for i in range(4):
    navnliste.append(input("Skriv inn et navn:\n").lower())
#Lager en ny liste og spør brukeren 4 ganger om et navn
if "marius" in navnliste:
    print("Du husket meg!")
else:
    print("Glemte du meg?")

prod = 1
sum = 0
for i in liste:
    prod*=i
    sum+=i
#Går igjennom listen og gjør om på variablene "prod" og "sum" for hvert element

nyliste = []
nyliste.append(prod)
nyliste.append(sum)
#Ny liste med variablene prod og sum

nyliste1 = liste + nyliste #Kombinerer de tidligere listene
print(nyliste1)
nyliste1.pop()
nyliste1.pop()
print(nyliste1)
#Fjerner de to siste elementene i listen