from rutenett import Rutenett
from celle import Celle
import random 

def assert_variabler(objekt, forventet_variabler):
    for variabel in forventet_variabler:
        assert hasattr(objekt, variabel), f"manglende variabel for {objekt.__class__.__name__}: {variabel}"

def assert_metoder(objekt, forventet_metoder):
    for metode in forventet_metoder:
        assert hasattr(objekt, metode), f"manglende metode for {objekt.__class__.__name__}: {metode}"

def assert_type(verdi, forventet_typen, navn):
    assert isinstance(verdi, forventet_typen), f"{navn} er av typen {type(verdi).__name__}, men \
                                                det burde være av typen {forventet_typen.__name__}"

def assert_verdi(verdi, forventet_verdi, navn):
    assert_type(verdi, type(forventet_verdi), navn)
    assert verdi == forventet_verdi, f"{navn} var {verdi} men det burde være {forventet_verdi}"

# ----- Metodene over er kun hjelpemetoder som bruker inne i testene



def test_konstruktoer_uten_rutenett():
    testRutenett = Rutenett(3, 5)

    assert_variabler(testRutenett, ["_ant_rader", "_ant_kolonner"])
    
    assert_verdi(testRutenett._ant_rader, 3, "Rutenett._ant_rader")
    assert_verdi(testRutenett._ant_kolonner, 5, "Rutenett._ant_kolonner")
    
    print("test_konstruktoer_uten_rutenett() - Alt riktig!")


def test_konstruktoer_med_rutenett():    
    testRutenett = Rutenett(3, 5) # Rader, Kolonner
    
    assert_metoder(testRutenett, ["_lag_tom_rad", "_lag_tomt_rutenett"])
    assert_variabler(testRutenett, ["_rutenett"])
    
    assert_type(testRutenett._rutenett, list, "Rutenett._rutenett")
            
    for inne_liste in testRutenett._rutenett:
        assert isinstance(inne_liste, list), f"Rutenett._rutenett burde være en nøstet liste, \
                                            men denne raden var av typen {type(inne_liste).__name__}: {inne_liste}"
                                
        assert_verdi(len(inne_liste), 5, "Rutenett._rutenett antall kolonner") # Lengden av en rad (antall kolonner)
        
        assert all([celle is None for celle in inne_liste]), f"forvented at raden inneholder bare None " \
                                                            f"verdier, funnet: {inne_liste}"
    # Lagt til av Siri    
    if len(testRutenett._rutenett)>1:
        assert testRutenett._rutenett[0] is not testRutenett._rutenett[1], \
                f"Rutenett._rutenett elementer refererer til samme liste (rad)"

    assert_verdi(len(testRutenett._rutenett), 3,  # Antall rader
                 "Rutenett._rutenett antall rader")
    
    print("test_konstruktoer_med_rutenett() - Alt riktig!")


def test_fyll_med_tilfeldige_celler():
    testRutenett = Rutenett(3, 5)
    
    assert_metoder(testRutenett, ["fyll_med_tilfeldige_celler"])
    
    random.seed(1)
    testRutenett.fyll_med_tilfeldige_celler()
    random.seed()
        
    status = set()
    
    for i, inne_liste in enumerate(testRutenett._rutenett):
        for j, celle in enumerate(inne_liste):
            assert_type(celle, Celle, f"celle i _rutenett[{i}][{j}]")
            status.add(celle._status)
            
    assert len(status) != 1, f"Alle celler i rutenettet har _status " \
                             f"{status.pop()}, men det burde være noe " \
                             f"levende og noe død"

    print("test_fyll_med_tilfeldige_celler() - Alt riktig!")


def test_hent_celle():
    testRutenett = Rutenett(3, 5)
    assert_metoder(testRutenett, ["hent_celle"])
    
    test_internt_rutenett = [[Celle() for i in range(5)] 
                              for i in range(3)]

    
    testRutenett._rutenett = test_internt_rutenett
    
    for rad in range(3):
        for kolonne in range(5):
            resultat = testRutenett.hent_celle(rad, kolonne)
            assert isinstance(resultat, Celle), f"forvented at " \
                f"hentCelle({rad}, {kolonne}) returnerte et Celle-objekt," \
                f" funnet {type(resultat)}"
                
            assert resultat == test_internt_rutenett[rad][kolonne], "feil Celle " \
                f"objekt returnert av hent_celle({rad}, {kolonne}). Er du " \
                f"sikker på at indeksen til den returnerte cellen er riktig?"
    
    for rad in [-1, 4]:
        for kolonne in [-1, 6]:
            resultat = testRutenett.hent_celle(rad, kolonne)
            assert resultat is None, f"forventet at " \
                f"hentCelle({rad}, {kolonne}) returnerte None, " \
                f"funnet {type(resultat)}"
                
    print("test_hent_celle() - Alt riktig!")


def test_tegn_rutenett():
    testRutenett = Rutenett(4, 4)
    assert_metoder(testRutenett, ["tegn_rutenett"])

    testRutenett.fyll_med_tilfeldige_celler()
    # Lagt til av Siri - trenger å sammenligne utskrift med faktisk innhold for test
    print("Innholdet i rutenettet (sjekk mot tegning nedenfor):")
    for rad in testRutenett._rutenett:
        for celle in rad:
            if celle._status == "doed":
                tegn = "."
            elif celle._status == "levende":
                tegn = "O"
            print(tegn,end="")
        print()

    print("\nResultat av tegn_rutenett:")
    testRutenett.tegn_rutenett()

    print("test_tegn_rutenett() - Utskrift ferdig!")


def test_sett_naboer():
    testRutenett = Rutenett(3, 3)
    assert_metoder(testRutenett, ["_sett_naboer"])
    rutenett_celler = [[Celle(), Celle(), Celle()],
                    [Celle(), Celle(), Celle()],
                    [Celle(), Celle(), Celle()]]
    testRutenett._rutenett = rutenett_celler
    
    testRutenett._sett_naboer(0,0)
    testCelle = testRutenett.hent_celle(0,0)
    assert len(testCelle._naboer) == 3, f"resultatet av testRutenett.sett_naboer(0,0) " \
                                        f"var {len(testCelle._naboer)}, men det burde være 3"
    
    testRutenett._sett_naboer(1,1)
    testCelle = testRutenett.hent_celle(1,1)
    assert len(testCelle._naboer) == 8, f"resultatet av testRutenett.sett_naboer(1,1) " \
                            f"var {len(testCelle._naboer)}, men det burde være 8"
    
    testRutenett._sett_naboer(2,1)
    testCelle = testRutenett.hent_celle(2,1)
    assert len(testCelle._naboer) == 5, f"resultatet av testRutenett.sett_naboer(1,1) " \
                            F"var {len(testCelle._naboer)}, men det burde være 5"
    
    print("test_sett_naboer() - Alt riktig!")

    
def test_koble_celler():
    testRutenett = Rutenett(2, 2) # 2 x 2 rutenett
    assert_metoder(testRutenett, ["koble_celler"])

    testRutenett.fyll_med_tilfeldige_celler()
    testRutenett.koble_celler()
    
    totalt_antall_naboer = 0
    for rad in range(testRutenett._ant_rader):
        for kol in range(testRutenett._ant_kolonner):
            totalt_antall_naboer += len(testRutenett.hent_celle(rad,kol)._naboer)

    assert totalt_antall_naboer == 12, f"Alle celler er ikke koblet sammen." \
                                       f"Totalt antall naboer var {totalt_antall_naboer}" \
                                       f"men skal være 12"


    testRutenett = Rutenett(3, 3) # 3 x 3 rutenett
    testRutenett.fyll_med_tilfeldige_celler()
    testRutenett.koble_celler()
    
    totalt_antall_naboer = 0
    for rad in range(testRutenett._ant_rader):
        for kol in range(testRutenett._ant_kolonner):
            totalt_antall_naboer += len(testRutenett.hent_celle(rad,kol)._naboer)

    assert totalt_antall_naboer == 40, f"Alle celler er ikke koblet sammen." \
                                       f"Totalt antall naboer var {totalt_antall_naboer}" \
                                       f" men skal være 40"

    print("test_koble_celler() - Alt riktig!")


def test_hent_alle_celler():
    testRutenett = Rutenett(2, 2)
    assert_metoder(testRutenett, ["hent_alle_celler"])
    testRutenett.fyll_med_tilfeldige_celler()

    resultat = testRutenett.hent_alle_celler()
    
    assert_type(resultat, list, "resultatet av testRutenett.hent_alle_celler()")
    
    for i, celle in enumerate(resultat):
        assert_type(celle, Celle, f"item på indeks {i} av Rutenett.hent_alle_celler()")
    
    assert len(resultat) == 4, f"resultatet av testRutenett.hent_alle_celler() " \
                               f"var av lengde {len(resultat)}, men det " \
                               f"burde være en av lengde 4"
    
    print("test_hent_alle_celler() - Alt riktig!")


def test_antall_levende():
    testRutenett = Rutenett(3, 3)
    assert_metoder(testRutenett, ["antall_levende"])
    rutenett_celler = [[Celle(), Celle(), Celle()],
                    [Celle(), Celle(), Celle()],
                    [Celle(), Celle(), Celle()]]        
    testRutenett._rutenett = rutenett_celler
    
    testRutenett.hent_celle(0,0).sett_levende()
    testRutenett.hent_celle(2,1).sett_levende()
    testRutenett.hent_celle(0,2).sett_levende()

    antall_levende = testRutenett.antall_levende()
    # Siri: Endret 4 til 3 i feilmelding
    assert antall_levende == 3, f"resultatet av testRutenett.antall_levende() " \
                                f"var {antall_levende}, men det burde være 3"
    
    print("test_antall_levende() - Alt riktig!")



# ----------
# TODO: Kalle på de metodene du ønsker å teste (fjerne kommentarene under):

print("\n** Test Rutenett **")
test_konstruktoer_uten_rutenett()
test_konstruktoer_med_rutenett()
test_fyll_med_tilfeldige_celler()
test_hent_celle()
test_tegn_rutenett()
test_sett_naboer()
test_koble_celler()
test_hent_alle_celler()
test_antall_levende()