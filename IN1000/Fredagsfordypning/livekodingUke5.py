"""OPPGAVE 1"""


def enkel_prediksjon():
    alder = int(input("Skriv inn alder:\n"))
    kjonn = input("Skriv inn kjonn (mann/kvinne):\n")
    status = input("Skriv inn sivilstatus (singel/gift):\n")
    gjeld = int(input("Skriv inn antall gjeld:\n"))
    utdanning = input("Skriv inn utdanning (ukjent, grunnskole, hoeyskole, universitet): ")

    loensnivaaer = {"ukjent": 300000, "grunnskole": 260000, "hoeyskole": 500000, "universitet": 700000}
    
    print(
        f"Du er en {status} {kjonn} paa en alder av {alder}aar og har {gjeld}kr i gjeld."
    )
    print("betalingshistorikk")
    betalingshistorikk = [
        input("Har personen betalt for 2mnd siden[betalt/ikke_betalt]: "),
        input("Har personen betalt for 1mnd siden[betalt/ikke_betalt]: "),
        input("Har personen betalt for 0mnd siden[betalt/ikke_betalt]: ")
    ]

    punkt1 = status == "singel" and alder < 30 and gjeld > 100000 and kjonn == "mann"
    punkt2 = kjonn == "mann" and alder < 25 and gjeld > 200000
    punkt3 = kjonn == "kvinne" and alder < 28 and status == "singel" and gjeld > 300000
    punkt4 = betalingshistorikk.count("ikke_betalt") > 1
    punkt5 = loensnivaaer[utdanning] > gjeld*3
    
    if punkt5:
        print("Vil betale")
    elif punkt1 or punkt2 or punkt3 or punkt4:
        print("Vil ikke betale")
    else:
        print("Vil betale")
    
def bestem_laan():
    kunde_id = int(input("Skriv inn kunde id: "))
    svarteliste = {23894, 29741, 10961, 22768, 22803, 11993, 24409, 9312, 29405, 6638, 738, 29964, 11967, 13443, 11534, 26228, 6867, 23027, 29137, 14084, 452, 15594, 22765, 25487}
    
    if kunde_id in svarteliste:
        print("Kan ikke faa laan")
    else:
        print("Kan faa laan")
    
enkel_prediksjon()


"""OPPGAVE 3"""
# Lag en tom liste som du skal fylle med betalingshistorikken
# Hent inn betalingshistorikken for de forrige måned ved hjelp av input (én input for hver mnd, start med forrige mnd, deretter måneden før, til slutt 2 mnd siden)
# Forventet input hver gang er enten tekststrengen betalt eller ikke_betalt
# For hver gang du leser inn betalingshistorikken for en gitt måned legger du denne i listen
# Print til slutt innholdet i listen slik at du kan se at den har blitt fyllt opp som forventet
