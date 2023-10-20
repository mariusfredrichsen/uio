

class Lag:
    def __init__(self, navn, angrep, forsvar):
        self._navn = navn
        self._angrep = angrep
        self._forsvar = forsvar
        self._borte = 0
        self._hjemme = 0
        
    def navn(self):
        return self._navn
    
    def angrep(self):
        return self._angrep
    
    def forsvar(self):
        return self._forsvar
    
    def n(self):
        print(self._hjemme, self._borte)

    def __str__(self):
        return self._navn
    
    