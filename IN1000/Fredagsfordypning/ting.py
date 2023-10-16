print(True**True)
while input("Vil du fortsette?"):
    # kode skjer her
    pass

# nytt_spill = True
# while nytt_spill:
#     # kode skjer her

#     svar = input("Tast 0 for å avslutte: ")
#     if svar == '0':
#         nytt_spill = False


# nytt_spill = True 
# while nytt_spill: #blir evaluert til True 
#     # mer kode skjer her
#     nytt_spill = int(input("Skal vi spille nytt spill? Skriv 1 for ja, 0 for nei\n> "))


# spille=input('Vil du utføre et nytt spill? ').lower()
# while spille=='ja':
#     # enda mer kode skjer her
#     spille=input('Vil du utføre et nytt spill? ').lower()


# exit = ""
# while exit.lower() != "q":
# 		# [...]
#     exit = input("Ønsker du å fortsette? Tast inn valgfri tast for å fortsette eller oppgi \'Q\' for å avslutte spillet:\n")




liste = [0,1,2,3,4,5,6,7,8,9]
print(liste)

asd = [e for e in liste if e % 2 == 1]

asd[0] = 10
print(asd)

streng = "asd"
print(streng)
streng = streng + "f"
print(streng)

from logen import Kamp
from lag import Lag

kamp = Kamp(Lag("SIF", 5.2, 1.4), Lag("Sarpsborg", 0.9, 4.5))
print(kamp.hjemmelag)
kamp.hjemmelag(Lag("asd", 1, 1))
print(kamp.hjemmelag)
