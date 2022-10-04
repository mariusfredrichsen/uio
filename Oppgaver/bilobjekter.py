class Bil:
    def __init__(self, navn1, navn2):
        self.navn1 = navn1
        self.navn2 = navn2


    def print_navn(self):
        print(self.navn1)
        print(self.navn2)

bil = Bil("Albert", "Knut")
bil.print_navn()