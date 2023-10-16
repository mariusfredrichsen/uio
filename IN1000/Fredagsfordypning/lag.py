

class Lag:
    def __init__(self, navn, angrep, forsvar):
        self._navn = navn
        self._angrep = angrep
        self._forsvar = forsvar
        
    def navn(self):
        return self._navn
    
    def angrep(self):
        return self._angrep
    
    def forsvar(self):
        return self._forsvar
    
    