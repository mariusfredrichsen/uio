class Rom:
    def __init__(self, navn, antall_plasser):
        self._navn = navn
        self._antall_plasser = antall_plasser

    def hent_navn(self):
        return self._navn
    
    def hent_antall_plasser(self):
        return self._antall_plasser