from rutenett import Rutenett

class Verden:
    def __init__(self, rader, kolonner):
        self._generasjonsnummer = 0
        
        self._rutenett = Rutenett(rader, kolonner)
        self._rutenett.fyll_med_tilfeldige_celler()
        self._rutenett.koble_celler()

    def tegn(self):
        self._rutenett.tegn_rutenett()
        print(f"Generasjon: {self._generasjonsnummer} - Antall levende celler: {self._rutenett.antall_levende()}")

    def oppdatering(self):
        for y in range(len(self._rutenett._rutenett)):
            for x in range(len(self._rutenett._rutenett[y])):
                self._rutenett._rutenett[y][x].tell_levende_naboer()
        for y in range(len(self._rutenett._rutenett)):
            for x in range(len(self._rutenett._rutenett[y])):
                self._rutenett._rutenett[y][x].oppdater_status()
        self._generasjonsnummer += 1
