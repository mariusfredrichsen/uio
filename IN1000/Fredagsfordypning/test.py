"""Dette er testene utviklet for å teste Spilleliste objekter.
De testene som er utviklet bruker mange triks som er utenfor pensum i IN1000.
Det er ikke forventet at man forstår all koden i denne filen.

Det som er forventet, er at man kan endre verdiene som er kommentert,
for å kjøre de testene man ønsker å kjøre.
Det forventes også at man kan lese feilmeldingene som kommer fra en test som eventuelt feiler.
"""

import unittest
import os
import tempfile
import pathlib
from io import StringIO
from contextlib import redirect_stdout

from sang import Sang
from spilleliste import Spilleliste

class TestSpilleliste(unittest.TestCase):
    """Enhetstest for Spilleliste objekter"""

    # Sett verdier til True når du har implementert metodene:
    __les_fra_fil = True
    __legg_til_sang = True
    __fjern_sang = True
    __finn_sang_tittel = True
    __spill_alle = True
    __finn_sang_tittel = True
    __hent_artist_utvalg = True
    __skriv_til_fil = True

    _standard_skip_melding = "Man har valgt å skippe testen"


    def setUp(self):
        self._testsanger = [
            (tittel:="Going for the one", artist:="Yes", Sang(tittel, artist)),
            (tittel:="Money", artist:="Pink Floyd", Sang(tittel, artist)),
            (tittel:="Help", artist:="The Beatles", Sang(tittel, artist)),
            (tittel:="The Carpet Crawlers", artist:="Genesis", Sang(tittel, artist)),
            (tittel:="Let's go crazy", artist:="Prince", Sang(tittel, artist)),
            (tittel:="Mercedes Benz", artist:="Janis Joplin", Sang(tittel, artist)),
            (tittel:="Killer Queen", artist:="Queen", Sang(tittel, artist)),
            (tittel:="We are the champions", artist:="Queen", Sang(tittel, artist)),
        ]
        self._spilleliste = Spilleliste("musikk")


    @unittest.skipUnless(__finn_sang_tittel, _standard_skip_melding)
    def test_finn_sang_tittel_finner_ikke_sang_som_ikke_er_i_spilleliste(self):
        "Tester at .finn_sang_tittel() returnerer None når sang ikke i spilleliste"
        tittel, _, _ = self._testsanger[0]
        ret = self._spilleliste.finn_sang_tittel(tittel)
        error_msg = f"Fant objektet '{ret}' i en spilleliste som skulle vært tom"
        self.assertIsNone(ret, msg=error_msg)


    @unittest.skipUnless(__finn_sang_tittel and __legg_til_sang, _standard_skip_melding)
    def test_finn_sang_tittel_finner_sang_som_er_lagt_til(self):
        """Tester at .finn_sang_tittel() kan finne igjen sanger lagt til med .legg_til_sang()"""
        for tittel, artist, sang in self._testsanger:
            self._spilleliste.legg_til_sang(sang)
            retur = self._spilleliste.finn_sang_tittel(tittel)
            err_msg = f"Fant ikke '{tittel}' av '{artist}', selv etter den skulle vært lagt til"
            self.assertEqual(sang, retur, msg=err_msg)


    @unittest.skipUnless(__finn_sang_tittel and __legg_til_sang and __fjern_sang, _standard_skip_melding)
    def test_finn_sang_tittel_finner_ikke_sang_som_er_fjernet(self):
        """Tester at .finn_sang_tittel() ikke finner sanger som er fjernet med .fjern_sang()"""
        for tittel, artist, sang in self._testsanger:
            self._spilleliste.legg_til_sang(sang)
            self._spilleliste.fjern_sang(sang)
            ret = self._spilleliste.finn_sang_tittel(tittel)
            err_msg = f"Fant objektet '{ret}', selv når man fjernet sangen '{tittel}' av '{artist}' skulle vært fjernet"
            self.assertIsNone(ret, msg=err_msg)

    @unittest.skipUnless(__legg_til_sang and __spill_alle, _standard_skip_melding)
    def test_spill_alle(self):
        """Tester at .spill_alle() printer all informasjon lagt i hver sang"""
        for _, _, sang in self._testsanger:
            self._spilleliste.legg_til_sang(sang)
        with redirect_stdout(StringIO()) as f:
            self._spilleliste.spill_alle()
        output = f.getvalue()
        for tittel, artist, sang in self._testsanger:
            self.assertIn(artist, output, msg=f"Artist '{artist}' ble ikke printet for sangen '{tittel}' av '{artist}'")
            self.assertIn(tittel, output, msg=f"Tittel '{tittel}' ble ikke printet for sangen '{tittel}' av '{artist}'")
        

    @unittest.skipUnless(__les_fra_fil and __finn_sang_tittel, _standard_skip_melding)
    def test_les_fra_fil(self):
        """Tester at .finn_sang_tittel() kan finne alle sanger lagt til med .les_fra_fil()"""
        self._spilleliste.les_fra_fil()
        for tittel, artist, _ in self._testsanger:
            retur = self._spilleliste.finn_sang_tittel(tittel)
            self.assertIsNotNone(retur, f"Fant ikke '{tittel}' av '{artist}' etter å ha lest fra fil")


    @unittest.skipUnless(__legg_til_sang and __hent_artist_utvalg, _standard_skip_melding)
    def test_hent_artist_utvalg(self):
        """Tester at .hent_artist_utvalg() returnerer en liste med alle sanger i til artisten"""
        lokale_testsanger = [
            (tittel:="Shake That", artist:="Eminem and Nate Dogg", Sang(tittel, artist)),
            (tittel:="Mockingbird", artist:="Eminem", Sang(tittel, artist)),
            (tittel:="Nobody Does It Better", artist:="Nate Dogg", Sang(tittel, artist)),
        ]

        alle_artister = set()
        for _, artister, sang in lokale_testsanger:
            self._spilleliste.legg_til_sang(sang)
            for artist in artister.split():
                alle_artister.add(artist)

        alle_utvalg = {artist:self._spilleliste.hent_artist_utvalg(artist) for artist in alle_artister}
        for tittel, artister, sang in lokale_testsanger:
            for artist in artister.split():
                err_msg = f"Forventet å finne '{tittel}' av '{artister}' i utvalget til '{artist}'"
                self.assertIn(sang, alle_utvalg[artist], msg=err_msg)


    @unittest.skipUnless(__les_fra_fil and __skriv_til_fil, _standard_skip_melding)
    def test_skriv_til_fil(self):
        """Tester at .skriv_til_fil() oppretter, og skriver spillelisten til fil"""
        gammel_mappe = os.getcwd()
        gammel_musikk_fil = pathlib.Path(gammel_mappe, "musikk.txt")
        self._spilleliste.les_fra_fil()
        ny_mappe = tempfile.mkdtemp()
        os.chdir(ny_mappe)
        self._spilleliste.skriv_til_fil()
        ny_musikk_fil = pathlib.Path(ny_mappe, "musikk.txt")
        with open(ny_musikk_fil, "r") as ny:
            with open(gammel_musikk_fil, "r") as gammel:
                nye_linjer = [linje for linje in ny]
                for linje in gammel:
                    self.assertIn(linje, nye_linjer, msg=f"Forventet linjen '{linje}' i den nye filen, men fant den ikke")
        os.chdir(gammel_mappe)
        

if __name__ == "__main__":
    unittest.main()