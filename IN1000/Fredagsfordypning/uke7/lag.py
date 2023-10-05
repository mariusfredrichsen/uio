



class Lag:
    def __init__(self, navn: str, angrep: float, forsvar: float):
        self.navn = navn
        self.angrep = angrep
        self.forsvar = forsvar
        
    def hent_navn(self):
        return self.navn
    
    def hent_angrep(self):
        return self.angrep
    
    def hent_forsvar(self):
        return self.forsvar