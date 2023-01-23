class Dato:
    def __init__(self, ny_dag, ny_maaned, nytt_aar):
        self._dag = ny_dag
        self._maaned = ny_maaned
        self._aar = nytt_aar
        
    def hent_aar(self):
        return self._aar

    def hent_dag(self):
        return self._dag
    
    def skriv_ut_dato(self):
        return f"{self._dag}.{self._maaned}.{self._aar}"
    
    def dato_sjekk(self, dag, maaned):
        if self._dag == dag and self._maaned == maaned:
            return True
        return False
    
    def foer_etter_sjekk(self, dag, maaned, aar):
        if self._aar > aar:
            return "Foer"
        elif self._aar < aar:
            return "Etter"
        elif self._maaned > maaned:
            return "Foer"                       
        elif self._dag > dag:
            return "Foer"
        elif self._aar == aar and self._maaned == maaned and self._dag == dag:
            return "Samme dato"
        else:
            return "Etter"
    
    def neste_dag(self):
        self._dag += 1
        ordbok = {
            1: 31,
            2: 28,
            3: 31,
            4: 30,
            5: 31,
            6: 30,
            7: 31,
            8: 31,
            9: 30,
            10: 31,
            11: 30,
            12: 31
        }
        if self._dag > ordbok[self._maaned]:
            self._maaned += 1
            self._dag = 1
            if self._maaned > 12:
                self._aar += 1
                self._maaned = 1
                
    