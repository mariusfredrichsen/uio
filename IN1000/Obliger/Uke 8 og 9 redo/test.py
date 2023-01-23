from sau import Sau
from ulv import Ulv
from spillbrett import Spillbrett
from spillbrett import har_kollidert
from gress import Gress
from stein import Stein

def test_sau():
    sau = Sau("sau", 10, 20)
    sau.sett_posisjon(0,0)
    sau.sett_fart(10,20)
    sau.beveg()
    sau.beveg()

    assert sau.hent_posisjon_venstre() == 20
    assert sau.hent_posisjon_topp() == 40

    sau.snu()

    assert sau.hent_fart_fra_venstre() == -10
    assert sau.hent_fart_fra_topp() == -20

    sau.beveg()

    assert sau.hent_posisjon_venstre() == 10
    assert sau.hent_posisjon_topp() == 20

test_sau()

def test_finn_naermeste_sau():
    brett = Spillbrett()
    brett.opprett_sau("sau", 0, 0)
    brett.opprett_sau("sau", 100, 100)
    ulv = brett.opprett_ulv("ulv", 90, 80, brett)
    ulv = Ulv("ulv", 90, 80, brett)
    ulv.finn_naermeste_sau(brett.hent_sauer())

test_finn_naermeste_sau()

def test_har_kollidert():
    # Test-case 1: Disse to objektene har kollidert, fordi ulven ligger delvis oppå sauen
    sau = Sau("sau", 50, 50)
    ulv = Ulv("ulv", 60, 60, brett=0)
    assert har_kollidert(sau, ulv)
    # Rekkefølgen skal ikke ha noe å si
    assert har_kollidert(ulv, sau)
    
    # Test-case 2: Disse to objektene ligger rett ved siden av hverandre 
    # og har ikke kollidert (husk at de er 50px brede/høye):
    gress = Gress("gress", 100, 100)
    sau = Sau("sau", 150, 150)
    assert not har_kollidert(gress, sau)
    
    # Implementer to test-caser til her: 
    sau = Sau("sau", 0, 49)
    stein = Stein("stein", 49, 0)
    assert har_kollidert(sau, stein)

    sau = Sau("sau", 700, 0)
    ulv = Ulv("ulv", 0, 0, 0)
    assert har_kollidert(sau, ulv)

test_har_kollidert()