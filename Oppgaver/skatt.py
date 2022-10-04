def print_innteksskatt():
    inntekt = int(input("Skriv inn inntekten din her: \n"))
    if inntekt >= 200000:
        print(f"{inntekt*(1-0.25)}")
    if inntekt > 200000 and inntekt < 500000:
        print(f"{inntekt*(1-0.3)}")
    if inntekt >= 500000:
        print(f"{inntekt*(1-0.45)}")

def beregn_inntektsskatt(inntekt):
    if inntekt <= 200000:
        return inntekt*0.25
    if inntekt > 200000 and inntekt < 500000:
        return inntekt*0.3
    if inntekt >= 500000:
        return inntekt*0.45

def beregn_formueskatt(formue):
    return formue * 0.05

def beregn_total_skatt(inntekt,formue):
    x = beregn_inntektsskatt(inntekt)
    x += beregn_formueskatt(formue)
    return x

liste = [33000, 700000, 2500000, 100000000]

print(beregn_total_skatt(100,100))

skatt = beregn_inntektsskatt(50000)
print("Skatt som skal betales: ", skatt)

for i in range(10000,500000,10000):
    print(beregn_inntektsskatt(i))

for i in liste:
    print(beregn_inntektsskatt(i))

inntekter = [[100000, 150000, 125000], [100, 0, 500], [1000000, 500000, 650000]]

print(inntekter[0][2]-beregn_inntektsskatt(inntekter[0][2]))

stor = [0,0,0]
teller = 0
for person in inntekter:
    teller += 1
    teller1 = 0
    for i in person:
        teller1 += 1
        if i-beregn_inntektsskatt(i) > stor[0]:
            stor[0],stor[1],stor[2] = i-beregn_inntektsskatt(i),teller,teller1

print(stor)

inntekter = {"Per": [100000, 150000, 125000], "Bjarne": [100, 0, 500], "Olga": [1000000, 50000, 650000]}

print(inntekter["Olga"][0]-beregn_inntektsskatt(inntekter["Olga"][0]))

def funksjon(ordbok,navn):
    return beregn_inntektsskatt(ordbok[navn][2])

print(funksjon(inntekter,"Bjarne"))

filnavn = "skatt.txt" 
f = open(filnavn, "r")

for l in f:
    liste = l.strip().split(" ")
    if beregn_inntektsskatt(int(liste[1])) < int(liste[2]):
        print("bra")
    else:
        print(f"{liste[0]} er en jævla snylter")