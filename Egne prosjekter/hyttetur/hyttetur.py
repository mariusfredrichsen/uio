

class Hytte:
    def __init__(self, navn, antall_senger, pris):
        self._navn = navn
        self._antall_senger = antall_senger
        self._pris = pris

    def hent_navn(self):
        return self._navn

    def total_pris(self, antall_personer):
        return self._pris * antall_personer
    
    def sjekk_plass(self, antall_personer):
        return antall_personer <= self._antall_senger
    
    def __str__(self):
        return f"Navn: {self._navn}\nAntall senger: {self._antall_senger}\nPris per seng: {self._pris}"
    
    def __eq__(self, objekt):
        if objekt.hent_navn() == self._navn:
            return True
        return False
    

class Tur:
    def __init__(self, hytter, beskrivelse):
        self._hytter = hytter
        self._beskrivelse = beskrivelse
    
    def skriv_tur(self):
        print(self._beskrivelse + "\nHytter:")
        for hytte in self._hytter:
            print(hytte)
    
    def hent_hytter(self):
        return self._hytter
    
    def sjekk_pris_plass(self, antall_personer, maks_pris):
        for hytte in self._hytter:
            if hytte.sjekk_plass(antall_personer) and hytte.total_pris(antall_personer) <= maks_pris:
                return True
        return False
    


class Turplanlegger:
    def __init__(self, hytte_fil, tur_fil):
        self._hytter = self.hytter_fra_fil(hytte_fil)
        self._turer = self._turer_fra_fil(tur_fil)

    def hytter_fra_fil(self, filnavn):
        f = open(filnavn)
        ordbok = {}

        for linje in f:
            hytte = linje.strip().split()
            ordbok[hytte[0]] = Hytte(hytte[0], int(hytte[1]), int(hytte[2]))
    
        return ordbok
    
    def _turer_fra_fil(self, filnavn):
        f = open(filnavn)
        liste = []
        index = 0

        for linje in f:
            hytter = []
            if index%2 == 0:
                beskrivelse = linje
            if index%2 == 1:
                for hytte in linje.strip().split():
                    hytter.append(hytte)
                liste.append(Tur(hytter, beskrivelse))
                beskrivelse = ""
            index += 1
        
        return liste
    
    def finn_turer(self, antall_personer, maks_pris, maks_dag):
        for tur in self._turer:
            if tur.sjekk_pris_plass(antall_personer, maks_pris) and len(tur.hent_hytter()) <= maks_dag:
                tur.skriv_tur()


def testprogram():
    turer = Turplanlegger("hytter.txt", "turer.txt")
    turer.finn_turer(7, 7000, 5)

testprogram()




    
    

