import random

class Kube:
    def __init__(self, lengde, bredde, dybde):
        self._lengde = lengde
        self._bredde = bredde
        self._dybde = dybde
        self._kuber = []

    def __repr__(self):
        return f"Lengden: {self._lengde}, Bredde: {self._bredde}, Dybde: {self._dybde}"
    
    def legg_til_kube(self, kube):
        self._kuber.append(kube)
    
    def skriv_ut(self):
        print(self._kuber)
        for i in self._kuber:
            print(repr(i))


def main():
    for i in range(10):
        kube = Kube(random.randint(1,10),random.randint(1,10),random.randint(1,10))
        kube.legg_til_kube(kube)
    kube.skriv_ut()

main()


