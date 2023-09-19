from dato import Dato

def main():
    dato1 = Dato(15,10,2022)
    
    print(dato1.hent_aar())
    
    if dato1.hent_dag() == 15:
        print("Loenningsdag!")
    
    if dato1.hent_dag() == 1:
        print("Ny maaned, nye muligheter")
        
    dato = dato1.skriv_ut_dato()
    print(dato)
    
    dato1.neste_dag()
    dato = dato1.skriv_ut_dato()
    print(dato)
    
    dag = int(input("Skriv inn en dag: "))
    maaned = int(input("Skriv inn en maaned: "))
    aar = int(input("Skriv inn et aar: "))
    print(dato1.foer_etter_sjekk(dag, maaned, aar))
        

main()