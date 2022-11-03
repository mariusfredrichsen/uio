from verden import Verden
from celle import Celle

def hovedprogram():
    verden = Verden(5, 5)
    
    verden._rutenett._rutenett = [[Celle(), Celle(), Celle(), Celle(), Celle()],
                [Celle(), Celle(), Celle(), Celle(), Celle()],
                [Celle(), Celle(), Celle(), Celle(), Celle()],
                [Celle(), Celle(), Celle(), Celle(), Celle()],
                [Celle(), Celle(), Celle(), Celle(), Celle()]]
    verden._rutenett._rutenett[2][1].sett_levende()
    verden._rutenett._rutenett[2][2].sett_levende()
    verden._rutenett._rutenett[2][3].sett_levende()
    verden.tegn()
    verden.oppdatering()
    verden.tegn()

# starte hovedprogrammet
hovedprogram()