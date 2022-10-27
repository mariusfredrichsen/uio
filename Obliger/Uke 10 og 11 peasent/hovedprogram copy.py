from verden import Verden
import matplotlib.pyplot as plt

def hovedprogram():
    bruker_input = input("Skriv inn dimensjonene på brettet, 'x,y': ").strip().split(",")
    x, y = int(bruker_input[0]), int(bruker_input[1])
    verden = Verden(y, x)
    x = []
    y = []
    while True:
        verden.tegn()
        if input("Trykk 'Enter' for å fortsette eller skriv inn 'q' for å avslutte") == "q":
            break
        verden.oppdatering()

        x.append(verden._generasjonsnummer)
        y.append(verden._rutenett.antall_levende())
    
    plt.plot(x,y)
    plt.show()



# starte hovedprogrammet
hovedprogram()
