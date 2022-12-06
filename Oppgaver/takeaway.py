class Rett:
    def __init__(self, navn, pris, innholdsstoffer):
        self._navn = navn
        self._pris = pris
        self._innholdsstoffer = innholdsstoffer

    def sjekk_innhold_ok(self, farlige_innholdsstoffer):
        for stoff in farlige_innholdsstoffer:
            if stoff in self._innholdsstoffer:
                return False
        
        return True

    def __str__(self):
        ny_streng = ""
        for stoff in self._innholdsstoffer:
            ny_streng += "\n" + stoff
        return f"Navn: {self._navn}\nPris: {self._pris}\nInnholdsstoffer: {ny_streng}"

spaghett = Rett("spaghett", 10.32, ["pasta", "kjøttdeig", "salt", "pepper", "oregano", "vann"])
print(spaghett)



class Kategori:
    def __init__(self, kategorinavn, retter):
        self._kategorinavn = kategorinavn
        self._retter = retter

    def hent_ok_retter(self, farlig_stoffer):
        ny_liste = []
        for stoff in farlig_stoffer:
            for rett in self._retter:
                if rett.sjekk_innhold_ok(stoff):
                    ny_liste.append(rett)
        
        return ny_liste
    
    def hent_kategorinavn(self):
        return self._kategorinavn

class Meny:
    def __init__(self, kategori_liste):
        self._kategori_liste = kategori_liste
        self._meny = {}
    
    def lag_meny(self, filnavn):
        ny_ordbok = {}
        ny_ordbok[filnavn[0:-4]] = self._lesKategoriFil(filnavn)
        return ny_ordbok

    def hent_redusert_meny(self, farlige_stoffer):
        ny_ordbok = {}
        for kategori in self._meny:
            ny_liste = kategori.hent_ok_retter(farlige_stoffer)
            if ny_liste != []:
                ny_ordbok[kategori.hent_kategorinavn()] = ny_liste

