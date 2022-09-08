navn = input("Hva heter du? ")
alder = int(input("Hva er ditt alder? ")) #Feil 4, er ikke en int for senere


print("Hei, ", navn, ", fint navn!") #Feil 1, ","


foerste_bokstav = navn[0]

if foerste_bokstav == "P": #Feil 2, manglet ":"
    print("Navnet ditt starter med P, som Python!")
else:
    print("Jeg kjenner ingen ord som starter med ", foerste_bokstav)


alder_i_fem_aar = alder + 5

print("i fem år skal du være", alder_i_fem_aar)


if alder >= 18:
    if alder < 100:
        drikke = "øl"
    else:
        drikke = "livets eliksir"
else:
    drikke = "brus" #Feil 3, var ikke string eller definert


print("Hei ", navn, ", har du lyst på ", drikke, "?") #Feil 5, "name"
