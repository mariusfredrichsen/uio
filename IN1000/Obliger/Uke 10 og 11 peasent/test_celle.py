from celle import Celle

def test_celle():
    celle = Celle()
    assert celle._status == "doed"
    assert celle._ant_levende_naboer == 0

    print("konstruktør - Alt riktig!")


def test_sett_doed_levende():
    celle = Celle()
    celle.sett_levende()
    assert celle._status == "levende"

    celle.sett_doed()
    assert celle._status == "doed"

    print("sett_doed(), sett_levende() - Alt riktig!")


def test_er_levende():
    celle = Celle()
    assert (not celle.er_levende())
    celle.sett_levende()
    assert(celle.er_levende())

    print("er_levende() - Alt riktig!")


def test_hent_status_tegn():
    celle = Celle()
    assert (celle.hent_status_tegn() == ".")
    celle.sett_levende()
    assert (celle.hent_status_tegn() == "O")

    print("hent_status_tegn() - Alt riktig!")


def test_legg_til_nabo():
    celle = Celle()
    nabo1 = Celle() 
    celle.legg_til_nabo(nabo1)
    assert (len(celle._naboer) == 1)

    print("legg_til_nabo() - Alt riktig!")


def test_tell_levende_naboer():
    celle = Celle()
    nabo1 = Celle() 
    celle.legg_til_nabo(nabo1)
    celle.tell_levende_naboer()
    assert (celle._ant_levende_naboer == 0) 

    nabo1.sett_levende()
    celle.tell_levende_naboer()
    assert (celle._ant_levende_naboer == 1)

    print("tell_levende_naboer() - Alt riktig!")


def test_oppdater_status():
    celle = Celle()
    celle.oppdater_status() 
    assert (not celle.er_levende()) # Blir doed, mindre enn 2 levende naboer

    nabo1 = Celle()
    nabo1.sett_levende()
    celle.legg_til_nabo(nabo1)
    nabo2 = Celle()
    nabo2.sett_levende()
    celle.legg_til_nabo(nabo2)

    celle.tell_levende_naboer()
    celle.oppdater_status() 
    assert (not celle.er_levende()) # Blir doed, 2 levende naboer

    nabo3 = Celle()
    nabo3.sett_levende()
    celle.legg_til_nabo(nabo3)
    celle.tell_levende_naboer()
    celle.oppdater_status() 
    assert (celle.er_levende()) # Blir levende, 3 levende naboer

    nabo4 = Celle()
    nabo4.sett_levende()
    celle.legg_til_nabo(nabo4)
    celle.tell_levende_naboer()
    celle.oppdater_status() 
    assert (not celle.er_levende()) # Blir doed, mer enn 3 levende naboer

    print("oppdater_status() - Alt riktig!")


# ----------
# TODO: Kalle på de metodene du ønsker å teste (fjern kommentarene under):

print("\n** Test Celle **")
test_celle()
test_sett_doed_levende()
test_er_levende()
test_hent_status_tegn()
test_legg_til_nabo()
test_tell_levende_naboer()
test_oppdater_status()


