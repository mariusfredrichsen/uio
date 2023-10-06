
class Lag:
    def __init__(self, navn: str, angrep: float, forsvar: float):
        self.navn = navn
        self.angrep = angrep
        self.forsvar = forsvar
        self.antall_kamper = 0
        
    def hent_navn(self):
        return self.navn
    
    def hent_angrep(self):
        return self.angrep/self.antall_kamper
    
    def hent_forsvar(self):
        return self.forsvar/self.antall_kamper
    
    def øk_antall_kamper(self):
        self.antall_kamper += 1
    
    def hent_antall_kamper(self):
        return self.antall_kamper