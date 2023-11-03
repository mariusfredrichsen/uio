from rutenett import Rutenett

class Verden:
    def __init__(self, rader, kolonner):
        self._rutenett = Rutenett(rader, kolonner)
        self._generasjonsnummer = 0
        
        self._rutenett.fyll_med_tilfeldige_celler()
        self._rutenett.koble_celler()

    def tegn(self):
        print(f"{self._rutenett}\nAntall generasjoner:{self._generasjonsnummer}\nAntall levende celler: {self._rutenett.antall_levende()}")

    def oppdatering(self):
        for celle in self._rutenett.hent_alle_celler():
            celle.tell_levende_naboer()
        
        for celle in self._rutenett.hent_alle_celler():
            celle.oppdater_status()
        
        self._generasjonsnummer += 1