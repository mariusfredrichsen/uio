import random as r

class Celle:
    def __init__(self):
        self._pos_x = 0
        self._pos_y = 0

    def sett_tilfeldig_posisjon(self):
        self._pos_x = r.randint(0,100)
        self._pos_y = r.randint(0,100)
    
    def __str__(self):
        return f"Celle på posisjon {self._pos_x}, {self._pos_y}"
    
    def beregn_avstand(self, annen_celle):
        return ((self._pos_x - annen_celle._pos_x)**2 + (self._pos_y - annen_celle._pos_y)**2)**0.5

celler = []

for i in range(10):
    cell = Celle()
    cell.sett_tilfeldig_posisjon()
    celler.append(cell)

for i in celler:
    naermest = celler[0].beregn_avstand(celler[1])
    naermeste_celler = []
    total_avstand = 0
    antall = 0
    for i in celler:
        for x in celler:
            if i != x:
                distanse = i.beregn_avstand(x)
                total_avstand += distanse
                antall += 1
                if distanse < naermest:
                    naermeste_celler = [i, x]
print(total_avstand/antall)
for i in range(2):
    print(naermeste_celler[i])
