from verden import Verden
import random as r

verden1 = Verden()

verden1.opprett_dyr("sau", "Knut", r.randint(1,10))
verden1.opprett_dyr("sau", "Deep", r.randint(1,10))
verden1.opprett_dyr("sau", "Mari", r.randint(1,10))
verden1.opprett_dyr("sau", "Isabel", r.randint(1,10))
verden1.opprett_dyr("sau", "Noah", r.randint(1,10))
verden1.opprett_dyr("sau", "Erlend", r.randint(1,10))
#Lager en håndfull sauer

verden1.opprett_dyr("ulv", "Sofie", r.randint(1,10))
verden1.opprett_dyr("ulv", "Linn", r.randint(1,10))
verden1.opprett_dyr("ulv", "Anna", r.randint(1,10))
#Lager noen ulver også

print(verden1.antall_sauer())
print(verden1.antall_ulver())
print()
#Skjekker hvor mange levende sauer og antall ulver

verden1.beskriv()
#Gir navn og posisjon over sauene så ulvene

for i in range(10):
    verden1.oppdater()
#Simulerer verdenen

denfeiteste = verden1.finn_feiteste_ulv()
print(f"{denfeiteste.hent_navn()} er den feiteste ulven på {denfeiteste.hent_vekt()}")