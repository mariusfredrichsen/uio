filnavn = "individer1000.txt"
fil = open(filnavn)
ting = []
n = 0
for linje in fil:
    data = linje.strip().split(",")
    alder = int(data[1])
    kjonn = data[2]
    sivilstatus = data[3]
    gjeld = int(data[4])
    betalingshistorikk = []
    for i in range(0, 3):
        betalingshistorikk.append(data[5+i])
    utdanningsnivo = data[8]
    fasit = data[9]
    if "vil ikke betale" in data:
        ting.append(int(data[0]))
    

sum = 0
for i in ting:
    print(i)
    sum+=i


d = sum/len(ting)
print(d)

{1,1,1,1,1}