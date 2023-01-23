from rett import Rett

class Kategori:
    def __init__(self, kategorinavn, retter):
        self._kategorinavn = kategorinavn
        self._retter = retter
    
    def hent_ok_retter(self, innholdsstoffer):
        ny_liste = []
        for stoffer in innholdsstoffer:
            for rett in self._retter:
                if not rett.sjekk_innhold_ok(stoffer):
                    ny_liste.append(rett)

        return rett
    
