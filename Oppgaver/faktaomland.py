hovedstad = {"Norge": "Oslo", "Nederland": "Amsterdam", "Spania": "Madrid"}
språk = {"Norge": "Norsk", "Nederland": "Nederlandsk", "Spania": "Spansk"}
innbyggere = {"Norge": 5391369, "Nederland": 17282163, "Spania": 46733038}

svar = input("Land: Norge, Nederland, Spania\nHvilke land vil du vite mer om?")
if svar not in hovedstad:
    print("Ikke i ordboka")
    exit(0)

print(f"{svar}s hovedstad er {hovedstad[svar]}, språk er {språk[svar]}, innbyggertall er {innbyggere[svar]}")
