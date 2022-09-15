steder = []
klesplagg = []
avreisedatoer = []

for i in range(5):
    steder.append(input(f"Skriv inn et reisemål({i+1}/5):\n"))

for i in range(5):
    klesplagg.append(input(f"Skriv inn et klesplagg({i+1}/5:\n"))

for i in range(5):
    avreisedatoer.append(input(f"Skriv inn en avreisedato({i+1}/5):\n"))

reiseplan = []
reiseplan.append(steder)
reiseplan.append(klesplagg)
reiseplan.append(avreisedatoer)

for i in reiseplan:
    print(i)

index_1 = int(input("Skriv et tall mellom 0 og 2:\n"))
index_2 = int(input("Skriv et tall mellom 0 og 4:\n"))
if index_1 >= 0 and index_1 <= 2:
    if index_2 >= 0 and index_2 <=4:
        print(reiseplan[index_1][index_2])
    else:
        print("Ugyldig input!")
else:
    print("Ugyldig input!")
    
