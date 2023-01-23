from regneklynge import Regneklynge

class Datasenter:
    def __init__(self):
        self._regneklynger = {}
        
    def les_fra_fil(self, filnavn):
        f = open(filnavn)
        index = 0
        
        for linje in f:
            linje.strip().split()
            if index == 0:
                max_noder_per_rack = linje[0]
            else:
                antall_noder = linje[0]
                minne_per_node = linje[1]
                antall_prosessorer_per_node = linje[2]