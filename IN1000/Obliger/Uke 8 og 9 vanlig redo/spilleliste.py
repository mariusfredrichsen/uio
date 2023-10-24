from sang import Sang

class Spilleliste:
    def __init__(self, listenavn):
        self._sanger = []
        self._navn = listenavn
        
    def les_fra_fil(self):
        with open(self._navn + ".txt", 'r', encoding='utf8') as f:
            