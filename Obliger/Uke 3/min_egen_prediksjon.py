# Her er din funksjon med din egen prediksjon
def min_prediksjon(alder, kjonn, sivilstatus, gjeld, betalingshistorikk, utdanningsnivo):
    utdanning = {"ukjent": 300000, "grunnskole": 260000, "hoeyskole": 500000, "universitet": 700000}
    if kjonn == "mann" and utdanning[utdanningsnivo] > gjeld*3:
        return "vil betale"
    elif betalingshistorikk.count("ikke_betalt") == 3:
        return "vil ikke betale"
    elif sivilstatus == "singel" and alder < 24 and gjeld > 1000000 and kjonn == "mann":
        return "vil ikke betale"
    elif kjonn == "mann" and alder < 25 and gjeld > 0:
        return "vil ikke betale"
    elif sivilstatus == "singel" and kjonn == "kvinne" and alder < 22 and gjeld > 200000:
        return "vil ikke betale"
    else:
        return "vil betale"

# Dette limer du inn under
def test_min_prediksjon():

    antall_predikert = 0
    antall_riktig_predikert = 0
    true_positive = 0
    true_negative = 0
    false_positive = 0
    false_negative = 0

    filnavn = "individer1000.txt"
    fil = open(filnavn)
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

        prediksjon = min_prediksjon(alder, kjonn, sivilstatus, gjeld, betalingshistorikk, utdanningsnivo)

        if prediksjon == fasit:
            antall_riktig_predikert += 1

        if fasit == "vil betale":
            if prediksjon == fasit:
                true_positive += 1
            else:
                false_negative += 1
        else:
            if prediksjon == fasit:
                true_negative += 1
            else:
                false_positive += 1

        antall_predikert += 1

    precision = true_positive / (true_positive + false_positive)
    recall = true_positive / (true_positive + false_negative)
    f1_score = 2 * (precision*recall) / (precision + recall)

    print(antall_riktig_predikert, "av", antall_predikert, "ble riktig predikert")
    print("Recall:", recall)
    print("Precision:", precision)
    print("F1 score:", f1_score)

test_min_prediksjon()