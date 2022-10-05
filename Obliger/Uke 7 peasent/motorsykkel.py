class Motorsykkel:
    def __init__(self, merke, registernummer):
        self._kilometerstand = 0
        self._merke = merke
        self._registernummer = registernummer
    
    def kjor(self, km):
        self._kilometerstand += km
    
    def hent_kilometerstand(self):
        return self._kilometerstand
    
    def skriv_ut(self):
        print(f"merke: {self._merke}")
        print(f"registernummer: {self._registernummer}")
        print(f"kilometeravstand: {self._kilometerstand}")