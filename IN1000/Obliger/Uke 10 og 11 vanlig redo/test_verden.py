from rutenett import Rutenett
from verden import Verden

def assert_variabler(objekt, forventet_variabler):
    for variabel in forventet_variabler:
        assert hasattr(objekt, variabel), f"manglende variabel for {objekt.__class__.__name__}: {variabel}"

def assert_metoder(objekt, forventet_metoder):
    for metode in forventet_metoder:
        assert hasattr(objekt, metode), f"manglende metode for {objekt.__class__.__name__}: {metode}"

# ----- Metodene over er kun hjelpemetoder som bruker inne i testene


def test_konstruktoer():
    verden = Verden(4,5)
    assert_variabler(verden, ["_rutenett", "_generasjonsnummer"])
    assert isinstance(verden._rutenett, Rutenett), f"Verden._rutenett burde vært en instans av klassen Rutenett"
    assert verden._generasjonsnummer == 0
    print("konstruktør - Ingen feil funnet!")


def test_tegn():
    verden = Verden(4,5)
    assert_metoder(verden, ["tegn"])
    verden.tegn()
    print("tegn() - Utskrift ferdig!")


def test_oppdatering():
    verden = Verden(3,3)
    assert_metoder(verden, ["oppdatering"])
    print("oppdatering():")
    verden.tegn()
    for i in range(3):
        verden.oppdatering()
        verden.tegn()
    
    assert verden._generasjonsnummer == 3

    print("\noppdatering() - testet 3 ganger!")


# ----------
# TODO: Kalle på de metodene du ønsker å teste (fjern kommentarene under):

print("\n** Test Verden **")
test_konstruktoer()
test_tegn()
test_oppdatering()