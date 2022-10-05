class Buss:
    def __init__(self, max_plass):
        self._antall = 0
        self._max_plass = max_plass
    
    def erTom(self):
        if self._antall == 0:
            return True
        return False

    def erFull(self):
        if self._antall == self._max_plass:
            return True
        return False

    def plukkOpp(self):
        if self._antall == self._max_plass:
            pass
        else:
            self._antall += 1
    
    def slippAv(self):
        if Buss.erTom():
            pass
        else:
            self._antall -= 1

    def hent_antall(self):
        return self._antall


def main():
    buss1 = Buss(20)

    for i in range(10):
        buss1.plukkOpp()

    print(buss1.hent_antall())

    for i in range(12):
        buss1.plukkOpp()

    print(buss1.hent_antall())

main()