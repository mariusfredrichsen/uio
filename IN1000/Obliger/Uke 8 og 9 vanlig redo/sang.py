


class Sang:
    def __init__(self, tittel, artist):
        self._tittel = tittel
        self._artist = artist
        
    def spill(self):
        print(f"Nå spilles {self._tittel} med {self._artist}")
    
    def sjekk_artist(self, navn):
        for n in navn.lower().split():
            if n in self._artist.lower().split():
                return True
        return False
    
    def sjekk_tittel(self, tittel):
        return tittel.lower() == self._tittel.lower()
    
    def sjekk_artist_og_tittel(self, artist, tittel):
        return self.sjekk_artist(artist) and self.sjekk_tittel(tittel)
    
    def streng_til_fil(self):
        return f"{self._tittel};{self._artist}\n"
    
    def __str__(self):
        return f"{self._tittel} med {self._artist}"