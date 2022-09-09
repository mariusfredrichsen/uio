def prediksjon_med_betalingshistorikk():
    utdanning = {"ukjent": 300000, "grunnskole": 260000, "høoeyskole": 500000, "universitet": 700000}
    ald = int(input("Hvor gammel er du? "))
    kjø = str(input("Hva slags kjønn er du (mann/kvinne)? ")).lower()
    sta = str(input("Hva er din sivil status (singel/gift)? ")).lower()
    gje = int(input("Hvor mye gjeld har du? "))
    utd = str(input("Hva er utdanningsnivået ditt (ukjent/grunnskole/hoeyskole/universitet)? "))

    his = []
    for i in range(3):
        his.append(input(f"Har du betalt for {i} måneder siden (betalt/ikke_betalt)?"))
    print(his, utdanning[utd])

    if kjø == "mann" and utdanning[utd] > gje*3:
        print("Vil betale gjeld.")
    elif his.count("ikke_betalt") == 2:
        print("Vil ikke betale gjeld.")
    elif sta == "singel" and ald < 30 and gje > 100000 and kjø == "mann":
        print("Vil ikke betale gjeld.")
    elif kjø == "mann" and ald < 25 and gje > 200000:
        print("Vil ikke betale gjeld")
    elif sta == "singel" and kjø == "kvinne" and ald < 28 and gje > 300000:
        print("Vil ikke betale gjeld.")
    else:
        print("Vil betale gjeld.")

prediksjon_med_betalingshistorikk()