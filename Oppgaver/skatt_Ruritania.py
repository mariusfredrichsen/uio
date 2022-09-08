inntekt = float(input("Hva er din inntekt?\n"))

if inntekt < 10000:
    inntekt = inntekt*0.9
elif inntekt >= 10000:
    inntekt = (inntekt*0.9)*0.7
print(inntekt)
