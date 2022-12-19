from rack import Rack

class Regneklynge:
    def __init__(self, max_noder):
        self._regneklynge_liste = []
        self._max_noder = max_noder
        
    def legg_til_node(self, node, max):
        teller = 0
        for i in range(len(self._regneklynge_liste)):
            if len(self._regneklynge_liste[i]._rack_liste) < self._max_noder:
                self._regneklynge_liste[i]._rakc_liste.append(node)
                pass
            teller += 1
        
        if teller == len(self._regneklynge_liste):
            self._regneklynge_liste.append(Rack())
    
    def ant_prosessorer(self):
        return
    
