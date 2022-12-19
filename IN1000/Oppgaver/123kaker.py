def oppskrift_fra_sukker(tall):
    ordbok = {"sukker": tall, "mel": tall*2, "smør": tall*3}
    return ordbok

def skriv_oppskrift(sukker, mel, smør):
    print(f"Du trenger {sukker}g sukker.")
    print(f"Du trenger {mel}g mel.")
    print(f"Du trenger {smør}g smør.")

def bake_kaker():
    svar = int(input("Hvor mye sukker har du? (gram)\n"))
    ing = oppskrift_fra_sukker(svar)
    skriv_oppskrift(ing["sukker"], ing["mel"], ing["smør"])

bake_kaker()