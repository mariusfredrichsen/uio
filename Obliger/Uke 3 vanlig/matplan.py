maaltider = {"albert bjornson": ["Sushi","Biff","Salat"], "sisil larsen": ["Brød","Fisk","Stekt Ris"], "morpheus": ["Snicker","Lasagne","Calzone"]}

def enprosedyre():
    for i in maaltider:
        print(i) #Går igjennom ordboken og printer nøkklene
    beboer = input("Skriv navnet på en av beboerne:\n").lower()
    if beboer in maaltider: #Sjekker om inputtet finnes i ordboken
        print(f"Her er måltidet til {beboer}:\n{maaltider[beboer]}")
    else:
        print("Den beboeren finnes ikke på listen.")
    
enprosedyre()

"""
Oppgave 4.3:
a) Jeg ville ha brukt en ordbok hvis man har brukernavnet og annen info om IN1000 studenten. Hvis ikke så hadde jeg bare brukt en vanlig liste eller mengde(Hvis det finnes flere med samme brukernavnet så hadde jeg bare brukt liste)
b) En ordbok fordi det er lettere å knytte poenge til studenten ved å sette opp et nøkkel-verdi par
c) En liste og ikke en mengde fordi man vil også se hvor mange som vant og ikke utelukke folk som har samme navn
d) En mengde fordi det er unødvendig å skrive samme allergien flere ganger
"""