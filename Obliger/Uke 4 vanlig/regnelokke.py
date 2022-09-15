liste = []

svar = 1
while svar != 0:
    svar = int(input("Skriv inn et tall (skriv 0 for å stoppe):\n"))
    liste.append(svar)

for i in liste:
    print(i)

def minSum(liste):
    sum = 0
    for i in liste:
        sum+=i
    return sum

max = liste[0]
for i in liste:
    if i > max:
        max = i

min = liste[0]
for i in liste:
    if i < min:
        min = i

print(max, min)