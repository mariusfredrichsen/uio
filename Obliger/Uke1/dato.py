dag = int(input("Skriv inn en dato (dag): \n"))
mån = int(input("Skriv inn en dato (mån): \n"))

dag1 = int(input("Skriv inn en dato (dag): \n"))
mån1 = int(input("Skriv inn en dato (mån): \n"))
#samler inn datoer ovenfor og gjør dem om til heltall fra strings

if mån < mån1:
    print("Riktig rekkefølge!")
elif mån == mån1:
    if dag < dag1:
        print("Riktig rekkefølge!")
    elif dag > dag1:
        print("Feil rekkefølge!")
    elif dag == dag1:
        print("Samme dato!")
elif mån > mån1:
    print("Feil rekkefølge!")
#sjekker først hvilken av månedene som er først.
#hvis de er like så sjekker den dagene.
#hvis dagene er også like er det samme dato.
