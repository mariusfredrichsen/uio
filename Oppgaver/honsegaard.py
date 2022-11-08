antall_honer = int(input("Hvor mange hoener bor i gaarden?\n"))

om_reven_kommer = input("Kommer reven?\n").lower()

om_bonden_sover = input("Sover bonde?\n").lower()

if om_reven_kommer == "ja" and om_bonden_sover == "ja":
    print(f"Det bor naa {antall_honer-3} hoens i gaarden.")
elif om_reven_kommer == "ja" and om_bonden_sover == "nei":
    print(f"Det bor naa {antall_honer} i gaarden. Bonden selger ett reveskinn, og tjener 190kr")
else:
    print(f"Det bor {antall_honer} hoens paa gaarden.")