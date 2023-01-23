def enkel_prediksjon():
    ald = int(input("Hvor gammel er du? "))
    kjø = str(input("Hva slags kjønn er du (mann/kvinne)? ")).lower()
    sta = str(input("Hva er din sivil status (singel/gift)? ")).lower() #Passer på at man ikke trenger å skrive med kunn småe bokstaver
    gje = int(input("Hvor mye gjeld har du? "))
    print(f"Du er en {sta} {kjø} på {ald}år med {gje}kr i gjeld.")

    if sta == "singel" and ald < 30 and gje > 100000 and kjø == "mann": #Bruker "and" for å tvinge på at alle de forskjellige utrykkene må være sanne
        print("Vil ikke betale gjeld.")
    elif kjø == "mann" and ald < 25 and gje > 200000:
        print("Vil ikke betale gjeld")
    elif sta == "singel" and kjø == "kvinne" and ald < 28 and gje > 300000:
        print("Vil ikke betale gjeld.")
    else:
        print("Vil betale gjeld.")
#If setning for å skille ut hvem som betaler og ikke betaler
enkel_prediksjon()
