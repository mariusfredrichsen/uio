class Rett:
    def __init__(self, navn, pris):
        self._navn = navn
        self._pris = pris
        self._innholdsstoffer = []

    def sjekk_innhold_ok(self, innholdsstoffer):
        for stoffer in innholdsstoffer:
            if stoffer in self._innholdsstoffer:
                return False
        return True
    
    def __str__(self):
        return f"Navn: {self._navn}\nPris: {self._pris}\nInnholdsstoffer: {self._innholdsstoffer}"        