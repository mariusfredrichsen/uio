from sauehjerne import Sauehjerne
from sau import Sau
from spillbrett import Spillbrett
from sauehjerne import finn_vanligste_element_i_liste

def test_avstand():
    brett = Spillbrett(10)
    brett.opprett_sau("sau", 400, 500)
    brett.opprett_gress("gress", 100, 100)
    brett.opprett_gress("gress", 50, 200)
    brett.opprett_gress("gress", 0, 800)
    brett.opprett_gress("gress", 400, 500)

    sau = brett.hent_sauer()[0]
    sauehjernen = sau.sauehjerne()

    assert sauehjernen.avstand_til_objekt(brett.hent_gress()[0]) == 14
    assert sauehjernen.avstand_til_objekt(brett.hent_gress()[1]) == 13
    assert sauehjernen.avstand_til_objekt(brett.hent_gress()[2]) == 14
    assert sauehjernen.avstand_til_objekt(brett.hent_gress()[3]) == 0
    print("Great success")
# test_avstand()



def test_retning():
    brett = Spillbrett(10)
    brett.opprett_sau("sau", 400, 500)
    brett.opprett_gress("gress", 100, 100)
    brett.opprett_gress("gress", 450, 500)
    brett.opprett_gress("gress", 400, 450)

    sau = brett.hent_sauer()[0]
    sauehjernen = sau.sauehjerne()
    gress = brett.hent_gress()[0]
    gress1 = brett.hent_gress()[1]
    gress2 = brett.hent_gress()[2]

    assert sauehjernen.retninger_mot_objekt(gress) == ["venstre", "opp"]
    assert sauehjernen.retninger_fra_objekt(gress) == ["hoeyre", "ned"]
    assert sauehjernen.retninger_mot_objekt(gress1) == ["hoeyre"]
    assert sauehjernen.retninger_mot_objekt(gress2) == ["opp"]
    print("Great success")

# test_retning()

def test_finn_vanligste_element_i_liste():
    print(finn_vanligste_element_i_liste(["ned", "ned", "opp"]))
    assert finn_vanligste_element_i_liste(["ned", "ned", "opp"]) == "ned"
    assert finn_vanligste_element_i_liste(["ned", "venstre", "opp", "venstre"]) == "venstre"
    ## Legg gjerne inn et par tester til

test_finn_vanligste_element_i_liste()