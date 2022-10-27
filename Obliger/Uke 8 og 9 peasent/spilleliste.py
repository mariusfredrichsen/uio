from sang import Sang

class Spilleliste:
    def __init__(self, listenavn):
        self._sanger = []
        self._navn = listenavn
    
    def les_fil(self, filnavn):
        f = open(filnavn)
        for linje in f:
            sang = Sang(linje.strip().split(";")[0], linje.strip().split(";")[1])
            self._sanger.append(sang)
    
    def legg_til_sang(self, ny_sang):
        self._sanger.append(ny_sang)
    
    def fjern_sang(self, sang):
        self._sanger.pop(self._sanger.index(sang))
    
    def spill_sang(self, sang):
        sang.spill()
    
    def spill_alle(self):
        for sang in self._sanger:
            sang.spill()

    def finn_sang(self, tittel):
        for sang in self._sanger:
            if sang.sjekk_tittel(tittel):
                return sang
        return
    
    def hent_artist_utvalg(self, artistnavn):
        sang_liste = []
        for sang in self._sanger:
            if sang.sjekk_artist(artistnavn):
                sang_liste.append(sang)
        return sang_liste
    

