#Lag et program som spør om et tall i terminalen. Hvis tallet er større enn 40 så skal man 
#dele det på 2 og gange det med 3 så skrive det ut. Hvis ikke så skal man bare skrive ut 
#tall i tillegg til å si "hei".

tall = float(input("Skriv inn et tall: "))

if tall > 40:
    print(tall/2*3)
    
else:
    print(str(tall) + " hei")