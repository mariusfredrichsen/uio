class Rektangel:
    def __init__(self, lengde, bredde):
        self.lengde = lengde
        self.bredde = bredde

    def oekLengde(self, oekning):
        self.lengde += oekning
    
    def oekBredde(self, oekning):
        self.bredde += oekning
    
    def areal(self):
        return self.lengde * self.bredde
    
    def omkrets(self):
        return self.lengde * 2 + self.bredde * 2

rektangel1 = Rektangel(10,10)
rektangel2 = Rektangel(10,10)

print(rektangel1.areal())
print(rektangel2.areal())
rektangel1.oekLengde(1)
rektangel2.oekBredde(10)
print(rektangel1.areal())
print(rektangel2.areal())