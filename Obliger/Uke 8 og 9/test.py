from sau import Sau
from spillbrett import Spillbrett
from ulv import Ulv
from spillbrett import har_kollidert
from gress import Gress

def test_sau():
    sau = Sau(50, 50, "sau")
    sau.sett_posisjon(0,0)
    sau.sett_fart(10,20)
    sau.beveg()
    sau.beveg()

    assert [sau.hent_posisjon_venstre(), sau.hent_posisjon_topp()] == [20,40]

    sau.snu()

    assert [sau.hent_fart_fra_venstre(), sau.hent_fart_fra_topp()] == [-10,-20]

    sau.beveg()

    assert [sau.hent_posisjon_venstre(), sau.hent_posisjon_topp()] == [10,20]

test_sau()

def test_finn_naermeste_sau():
    brett = Spillbrett()
    brett.opprett_sau("sau", 10, 0)
    brett.opprett_sau("sau", 100, 100)
    brett.opprett_sau("sau", 50, 50)
    ulv = brett.opprett_ulv("ulv", 10, 5, brett)
    naermeste_sau = ulv.finn_naermeste_sau(brett.hent_sauer())
    print(naermeste_sau.hent_posisjon_venstre(), naermeste_sau.hent_posisjon_topp())  # Det bør printes 100, 100, ettersom denne sauen er nærmest ulven

test_finn_naermeste_sau()

def test_har_kollidert():
    brett = Spillbrett
    # Test-case 1: Disse to objektene har kollidert, fordi ulven ligger delvis oppå sauen
    sau = Sau(50, 50, "sau")
    ulv = Ulv(100, 100, "ulv", brett)
    print(har_kollidert(sau, ulv))
    
    # Rekkefølgen skal ikke ha noe å si
    print(har_kollidert(ulv, sau))
    
    
    # Test-case 2: Disse to objektene ligger rett ved siden av hverandre 
    # og har ikke kollidert (husk at de er 50px brede/høye):
    gress = Gress(1, 99, "gress")
    sau = Sau(50,50, "sau")
    print(har_kollidert(gress, sau))
    
    # Implementer to test-caser til her: 
    # ...
test_har_kollidert()