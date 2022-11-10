class Kanin:
    def __init__(self):
        self._alder = 0

    def hent_alder(self):
        return self._alder


class Verden:
    def __init__(self):
        self._kaniner = []

    def lag_kanin(self, kanin):
        self._kaniner.append(kanin)
    
    def sjekk_alder(self):
        for kanin in self._kaniner:
            if kanin.hent_alder() >= 2:
                self.lag_kanin(Kanin())

    def oppdater(self):
        for kanin in self._kaniner:
            kanin._alder += 1
        
    def hent_kaniner(self):
        return self._kaniner
    
def hovedprogram():
    verden = Verden()
    verden.lag_kanin(Kanin())
    verden.lag_kanin(Kanin())

    for i in range(20):
        verden.sjekk_alder()
        verden.oppdater()
        print(len(verden.hent_kaniner()))

hovedprogram()