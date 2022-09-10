def enprosedyre():
    alder = int(input("Hvor gammel er kjøperen?\n"))
    billetpris = 0
    #If-else funksjon som forandrer prisen basert på svaret til brukeren
    if alder <= 17:
        billetpris = 30
    elif alder > 17:
        billetpris = 50
    elif alder >= 63:
        billetpris = 35
    else:
        print("Noe galt skjedde.")
    #Problemet med denne if else sjekken er at den siste elif'en vil aldri bli kjørt pga tidligere elifer. Hvis alder > 63 vil den også være > 17.
    #For å fikse dette kan man flytte den lengere opp på if-else sjekken
    print(f"Prisen på billetten blir {billetpris}")

enprosedyre()
    