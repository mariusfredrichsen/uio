from sang import Sang
from spilleliste import Spilleliste

def hovedprogram():

    all_musikk = Spilleliste('Hele musikkbiblioteket')
    all_musikk.les_fil('musikk.txt')
    
    print("Tester spillAlle: Spiller alle sanger i listen:")
    all_musikk.spill_alle()
    print()
    
    ny_sang = Sang("Jahn Teigen", "Mil etter mil")
    print("Spiller ny sang:")
    all_musikk.spill_sang(ny_sang)
    print()   
	 
    all_musikk.legg_til_sang(ny_sang)
    print("Spiller alle sanger i listen inkl ny sang:")
    all_musikk.spill_alle() # pass på at rekkefølgen mellom Tittel
    print()
    
    funnet_sang = all_musikk.finn_sang("Mil etter mil")
    if funnet_sang is not None:
        print("Fant sangen:")
        all_musikk.spill_sang(funnet_sang)
    else:
        print("Fant ikke sangen\n")
    assert(funnet_sang in all_musikk.hent_artist_utvalg("Jahn"))
    print()
    
    # Tester om flere sanger returneres for samme artist
    queen_liste = all_musikk.hent_artist_utvalg("Queen")
    print("Spiller sanger med Queen hentet fra listen: ")
    for queen_sang in queen_liste:
        queen_sang.spill()
    
    all_musikk.fjern_sang(funnet_sang)
    assert(not (funnet_sang in all_musikk.hent_artist_utvalg("Jahn")))
    
hovedprogram()