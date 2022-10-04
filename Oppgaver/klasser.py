lotterinavn = "Kult lotteri"
lodd = [1,2,3,4]
permie = 1251

class Lotteri:
    def __init__(self, navn, premie, lodd):
        self.navn = navn
        self.permie = premie
        self.lodd = lodd

lotteri = Lotteri(lotterinavn, permie, lodd)

print(lotteri.navn)
print(lotteri.lodd)
print(lotteri.permie)