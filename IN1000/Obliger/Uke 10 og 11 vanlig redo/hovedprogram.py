from verden import Verden

def hovedprogram():
    verden = Verden(50, 50)
    while not input("Skriv 'q' for å avslutte"):
        verden.tegn()
        verden.oppdatering()

# starte hovedprogrammet
hovedprogram()