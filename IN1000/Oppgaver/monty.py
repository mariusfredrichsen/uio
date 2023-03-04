from dataclasses import asdict
import random

bil = 0
geit = 0

for i in range(1000):
    dører = ["geit", "bil", "geit"]
    random.shuffle(dører)
    vinndør = random.randint(1,3)

    valg = random.randint(1,3)
    if valg < 1 or valg > 3:
        print("Noe gikk galt")
        exit(0)
    else:
        for i in range(len(dører)):
            if dører[i] == "geit" and i != valg-1:
                altdør = i+1
                altdør1 = 6-i-valg-1
                break
        print(f"Du har valgt dør {valg}. Bak dør {altdør} er det en geit. Har du lyst til å bytte til dør {altdør1}?")
        svar = "nei"
        if svar == "ja":
            print(f"Bak dør {altdør1} så er det en {dører[altdør1-1]}.")
            if dører[altdør1-1] == "geit":
                geit+=1
            elif dører[altdør1-1] == "bil":
                bil+=1
        elif svar == "nei":
            print(f"Bak dør {valg} så er det en {dører[valg-1]}.")
            if dører[valg-1] == "geit":
                geit+=1
            elif dører[valg-1] == "bil":
                bil+=1

print(f"biler: {bil} og geiter: {geit}")