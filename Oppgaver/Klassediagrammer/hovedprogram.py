from bygning import Bygning
from rom import Rom

def hovedprogram():
    bygning = Bygning("Ifi")
    bygning.legg_til_rom(Rom("CAML", antall_plasser=72))
    bygning.legg_til_rom(Rom("AWK", antall_plasser=16))
    bygning.finn_storste_rom().hent_navn() == "CAML"

hovedprogram()