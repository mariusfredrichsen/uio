from dataclasses import asdict
import random

dører = ["geit", "bil", "geit"]
random.shuffle(dører)
vinndør = random.randint(1,3)

valg = int(input("Velg en av tre dører (1-3):\n"))
if valg < 1 or valg > 3:
    print("Noe gikk galt")
    exit(0)
else:
    for i in range(len(dører)):
        if dører[i] == "geit" and i != valg-1:
            altdør = i+1
            altdør1 = 6-i-valg-1
            break
    print(dører)
    print(f"Du har valgt dør {valg}. Bak dør {altdør} er det en geit. Har du lyst til å bytte til dør {altdør1}?")
    svar = input("Ja eller Nei?").lower()
    if svar == "Ja":
        