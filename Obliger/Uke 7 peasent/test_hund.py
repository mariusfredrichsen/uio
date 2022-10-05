from hund import Hund

def main():
    hund1 = Hund(10,10)
    
    for i in range(2):
        hund1.spring()
        print(hund1.hent_vekt())
        hund1.spis(0)
        print(hund1.hent_vekt())
        
main()