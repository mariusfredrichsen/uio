from motorsykkel import Motorsykkel

def hovedprogram():
    motorsykkel1 = Motorsykkel("Toyota", "AB12415")
    motorsykkel2 = Motorsykkel("Tesla", "EL25215")
    
    motorsykkel1.skriv_ut()
    motorsykkel2.skriv_ut()
    
    motorsykkel2.kjor(10)
    print(motorsykkel2.hent_kilometerstand())
    motorsykkel2.skriv_ut()
    
hovedprogram()