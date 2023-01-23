from sang import Sang

def hovedprogram():
    sang1 = Sang("Lady Gaga and Bradley Cooper", "Shallow")
    
    # Metoden spill
    print("Spiller av test-objekt:")
    sang1.spill()
    
    print("Tester sjekk_artist med 'Lady Gaga and Bradley Cooper'")	# True
    assert(sang1.sjekk_artist("Lady Gaga and Bradley Cooper"))     	
    print("Tester sjekk_artist med 'Lord Gaga'") 					# True, ett ord finnes i artistnavnet
    assert(sang1.sjekk_artist("Lord Gaga"))   
    print("Tester sjekk_artist med 'Sadley'")						# False
    assert(not sang1.sjekk_artist("Sadley"))       
    print("Tester sjekk_artist med 'Sadley'")	
    assert(not sang1.sjekk_artist("a"))                            # False
    print("Tester sjekk_artist med 'a'")	

    
    # Metoden sjekkTittel
    print("Tester sjekk_tittel med 'Shallow'")						# True
    assert(sang1.sjekk_tittel("Shallow"))          
    print("Tester sjekk_tittel med 'shalLow'")						# True
    assert(sang1.sjekk_tittel("shalLow"))  
    print("Tester sjekk_tittel med 'Hallow'")					# False
    assert(not sang1.sjekk_tittel("Hallow"))       
    print()
 
    # Metoden sjekk_artist_og_tittel
    print("Tester sjekk_artist_og_tittel med 'Bradley Cooper' og 'Shallow'")	# True
    assert(sang1.sjekk_artist_og_tittel('Bradley Cooper', 'Shallow')) 
    print("Tester sjekk_artist_og_tittel med 'Booper' og 'Shallow'")			# False
    assert(not sang1.sjekk_artist_og_tittel('Booper', 'Shallow')) 
    
hovedprogram()