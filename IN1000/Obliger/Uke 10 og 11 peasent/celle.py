class Celle:
    # Konstruktør
    def __init__(self):
        self._status = "doed"
        self._naboer = []
        self._ant_levende_naboer = 0
    
    def sett_doed(self):
        self._status = "doed"

    def sett_levende(self):
        self._status = "levende"

    def legg_til_nabo(self, nabo):
        self._naboer.append(nabo)

    def er_levende(self):
        if self._status == "levende":
            return True
        return False

    def hent_status(self):
        pass

    def hent_status_tegn(self):
        if self.er_levende():
            return "O"
        return "."

    def tell_levende_naboer(self):
        levende_naboer = []

        for celle in self._naboer:
            if celle.er_levende():
                levende_naboer.append(celle)

        self._ant_levende_naboer = len(levende_naboer)

    def oppdater_status(self):
        if self.er_levende():
            if self._ant_levende_naboer < 2:
                self.sett_doed()
            if self._ant_levende_naboer >= 2:
                self.sett_levende()
            if self._ant_levende_naboer > 3:
                self.sett_doed()
        
        if not self.er_levende():
            if self._ant_levende_naboer == 3:
                self.sett_levende()