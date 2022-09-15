def adder(tall1, tall2):
    sum = tall1 + tall2
    return sum
tall1, tall2 = 4,5

print(f"Summen av {tall1} og {tall2} er {adder(tall1,tall2)}")
print(f"Summen av {tall1} og {tall2} er {adder(tall1,tall2)}")

def tellForeKomst(minTekst, minBokstav):
    n = 0
    for i in minTekst:
        if minBokstav in i:
            n+=1
    print(n)

tekst = input("Skriv inn en tekst:\n")
bokstav = input("Skriv inn en bokstav:\n")

tellForeKomst(tekst, bokstav)