class Sang:
    def __init__(self, artist, tittel):
        self._artist = artist
        self._tittel = tittel

    def spill(self):
        print(f"Spiller {self._tittel} av {self._artist}")
    
    def sjekk_artist(self, navn):
        for elem in navn.split():
            if elem in self._artist.split():
                return True
        return False

    def sjekk_tittel(self, tittel):
        if tittel.lower() == self._tittel.lower():
            return True
        return False
    
    def sjekk_artist_og_tittel(self, artist, tittel):
        if self.sjekk_artist(artist) and self.sjekk_tittel(tittel):
            return True
        return False
    

