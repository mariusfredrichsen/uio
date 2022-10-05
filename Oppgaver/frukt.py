class Frukt:
    def __init__(self, navn, vann_per, spiselig):
        self._navn = navn
        self._vann_per = vann_per
        self._spiselig = spiselig

    def henVannPer100(self):
        return self._vann_per

    def erSpiselig(self):
        return self._spiselig
    
frukt = Frukt("trollhegg", 0, False)
frukt = Frukt("slyngsøtvier", 0, False)
frukt = Frukt("banan", 86, True)


