"""Dette er testene utviklet for å teste Sang objekter.
De testene som er utviklet bruker mange triks som er utenfor pensum i IN1000.
Det er ikke forventet at man forstår all koden i denne filen.

Det som er forventet, er at man kan endre verdiene under til å kjøre de testene man ønsker å kjøre.
Det forventes også at man kan lese feilmeldingene som kommer fra en test som eventuelt feiler.
"""

# Sett verdier til True om du ønsker å kjøre spesifikke tester
__spill = True
__sjekk_artist = True
__sjekk_tittel = True
__sjekk_artist_og_tittel = True
__streng_til_fil = True


import unittest
from io import StringIO
from contextlib import redirect_stdout

from sang import Sang


class TestSang(unittest.TestCase):
    """Enhetstesten til Sang"""


def lag_parametrisert_test_navn(test_funksjon, *args):
    tilegg = "_".join([str(arg) for arg in args])
    return f"{test_funksjon.__name__}_{tilegg}"


def generer_test_spill(artist, tittel):
    def test_spill(self):
        with redirect_stdout(StringIO()) as f:
            Sang(tittel, artist).spill()
        output = f.getvalue()
        self.assertIn(artist, output, msg=f"Artisten printes ikke")
        self.assertIn(tittel, output, msg=f"Tittelen printes ikke")
    test_spill.__name__ = lag_parametrisert_test_navn(test_spill, artist)
    test_spill.__doc__ = f"Tester om .spill() printer sangen som spilles ({tittel} av {artist})"
    return test_spill


def generer_test_sjekk_artist(artist_under_sjekk, artist, tittel, forventet_retur):
    def test_sjekk_artist(self):
        sang = Sang(tittel, artist)
        error_msg = f"Forventet '{forventet_retur}' når man sjekker etter artisten(e) '{artist_under_sjekk}'"
        self.assertEqual(sang.sjekk_artist(artist_under_sjekk), forventet_retur, msg=error_msg)
    test_sjekk_artist.__name__ = lag_parametrisert_test_navn(test_sjekk_artist, artist_under_sjekk, forventet_retur)
    test_sjekk_artist.__doc__ = f"Tester at .sjekk_artist('{artist_under_sjekk}') returnerer {forventet_retur} for sangen '{tittel}' av '{artist}'"
    return test_sjekk_artist


def generer_test_sjekk_tittel(tittel_under_sjekk, artist, tittel, forventet_retur):
    def test_sjekk_tittel(self):
        sang = Sang(tittel, artist)
        error_msg = f"Forventet '{forventet_retur}' når man sjekker etter tittelen '{tittel_under_sjekk}'"
        self.assertEqual(sang.sjekk_tittel(tittel_under_sjekk), forventet_retur, msg=error_msg)
    test_sjekk_tittel.__name__ = lag_parametrisert_test_navn(test_sjekk_tittel, tittel_under_sjekk, forventet_retur)
    test_sjekk_tittel.__doc__ = f"Tester at .sjekk_tittel('{tittel_under_sjekk}') returnerer {forventet_retur} for sangen '{tittel}' av '{artist}'"
    return test_sjekk_tittel


def generer_test_sjekk_artist_og_tittel(sjekket_tittel, sjekket_artist, artist, tittel, forventet_retur):
    def test_sjekk_artist_og_tittel(self):
        """Tester at .sjekk_artist_og_tittel() gir rett returverdi"""
        sang = Sang(tittel, artist)
        error_msg = f"Forventet '{forventet_retur}' når man sjekker etter tittelen '{sjekket_tittel}' og artisten(e) '{sjekket_artist}'"
        self.assertEqual(sang.sjekk_artist_og_tittel(sjekket_artist, sjekket_tittel), forventet_retur, msg=error_msg)
    test_sjekk_artist_og_tittel.__name__ = lag_parametrisert_test_navn(test_sjekk_artist_og_tittel, sjekket_artist, sjekket_tittel, forventet_retur)
    test_sjekk_artist_og_tittel.__doc__ = f"Tester at .sjekk_artist_og_tittel('{sjekket_artist}', '{sjekket_tittel}') returnerer {forventet_retur} for sangen '{tittel}' av '{artist}'"
    return test_sjekk_artist_og_tittel

    
def generer_test_streng_til_fil(artist, tittel, forventet_retur):
    def test_streng_til_fil(self):
        sang = Sang(tittel, artist)
        faktisk_retur = sang.streng_til_fil()
        error_msg = f"Forventet at sangen skulle returnere '{forventet_retur}', fikk '{faktisk_retur}'"
        self.assertEqual(faktisk_retur, forventet_retur, msg=error_msg)
    test_streng_til_fil.__name__ = lag_parametrisert_test_navn(test_streng_til_fil,forventet_retur)
    test_streng_til_fil.__doc__ = f"Tester at .streng_til_fil() returnerer {forventet_retur.rstrip()} for sangen '{tittel}' av '{artist}'"""
    return test_streng_til_fil


def _generer_tester(*args):
    """Genererer testene basert på tupler med funksjon som oppretter tester, parametre, og om testene skal kjøres"""
    genererte_tester = []
    for generator, parameter_liste, skal_testes in args:
        if skal_testes:
            for parametre in parameter_liste:
                genererte_tester.append(generator(*parametre))
    return genererte_tester


def __legg_til_tester_i_TestSang():
    # Parametrene til testene
    spill_parametre = [
        ["Cryoshell", "Bye Bye Babylon"],
        ["Linkin Park", "In the End"]
    ]
    
    sjekk_artist_parametre = [
        ["Snoop", artist:="Dr. Dre and Snoop Dogg", tittel:="The Next Episode", True],
        ["snoop", artist, tittel, True],
        ["Dre the Giant", artist, tittel, True],
        ["lady gaga", artist, tittel, False],
        ["snoopdog", artist, tittel, False],
        ["Dog", artist, tittel, False]
    ]

    sjekk_tittel_parametre = [
        ["The Next Episode", artist, tittel, True],
        ["tHe NeXt EpIsOdE", artist, tittel, True],
        ["Poker Face", artist, tittel, False],
        ["The Carpet Crawlers", artist, tittel, False]
    ]

    sjekk_artist_og_tittel_parametre = [
        ["The Next Episode", "Snoop", artist, tittel, True],
        ["The Next Episode", "Lady Gaga", artist, tittel, False],
        ["Poker Face", "Dr. Dre", artist, tittel, False],
        ["Poker Face", "Lady Gaga", artist, tittel, False]
    ]

    streng_til_fil_parametre = [
        [artist, tittel, "The Next Episode;Dr. Dre and Snoop Dogg\n"],
        ["The Beatles", "Help", "Help;The Beatles\n"]
    ]

    genererte_tester = _generer_tester(
        (generer_test_spill, spill_parametre, __spill),
        (generer_test_sjekk_artist, sjekk_artist_parametre, __sjekk_artist),
        (generer_test_sjekk_tittel, sjekk_tittel_parametre, __sjekk_tittel),
        (generer_test_sjekk_artist_og_tittel, sjekk_artist_og_tittel_parametre, __sjekk_artist_og_tittel),
        (generer_test_streng_til_fil, streng_til_fil_parametre, __streng_til_fil),
    )

    # Legger til testene i TestSang, slik at de er tilgjengelig når unittest.main kjører.
    for testfunksjon in genererte_tester:
        setattr(TestSang, testfunksjon.__name__, testfunksjon)


__legg_til_tester_i_TestSang()
if __name__ == "__main__":
    unittest.main()

