def finn_alle_fib_tall(tall):
    tall_liste = [0,1]
    while tall_liste[-1] < tall:
        tall_liste.append(tall_liste[-1]+tall_liste[-2])
    tall_liste.pop(-1)
    return tall_liste

print(finn_alle_fib_tall(int(input("Skriv inn et tall:\n"))))