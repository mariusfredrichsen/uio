from sang import Sang

class Spilleliste:
    def __init__(self, listenavn):
        self._sanger = []
        self._navn = listenavn
        
    def les_fra_fil(self):
        with open(self._navn + ".txt", 'r', encoding='utf8') as f:
            for line in f:
                tittel, artist = line.strip().split(";")
                self._sanger.append(Sang(tittel, artist))

    def legg_til_sang(self, ny_sang):
        self._sanger.append(ny_sang)
    
    def fjern_sang(self, sang):
        self._sanger.remove(sang)
        
    def spill_alle(self):
        for sang in self._sanger:
            sang.spill()
            
    def finn_sang_tittel(self, tittel):
        for sang in self._sanger:
            if sang.sjekk_tittel(tittel):
                return sang
        return None
    
    def hent_artist_utvalg(self, artistnavn):
        out = []
        for sang in self._sanger:
            if sang.sjekk_artist(artistnavn):
                out.append(sang)
        return out

    def skriv_til_fil(self):
        with open(self._navn + '.txt', 'w', encoding='utf8') as f:
            for sang in self._sanger:
                f.write(sang.streng_til_fil())
    
    def __str__(self):
        out = ""
        for sang in self._sanger:
            out += sang.streng_til_fil()
        return out