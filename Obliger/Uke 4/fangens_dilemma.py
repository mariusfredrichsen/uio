def beregn_score(valg_spiller1, valg_spiller2):
    score = []
    if valg_spiller1 == "samarbeid" and valg_spiller2 == "samarbeid":
        score = [3,3]
    if valg_spiller1 == "svik" and valg_spiller2 == "samarbeid":
        score = [5,0]
    if valg_spiller1 == "samarbeid" and valg_spiller2 == "svik":
        score = [0,5]
    if valg_spiller1 == "svik" and valg_spiller2 == "svik":
        score = [1,1]
    return score

def spill_snilt(tidligere_trekk):
    svi = 0
    sam = 0
    for i in tidligere_trekk:
        if i == "svik":
            svi+=1
        else:
            sam+=1
    if tidligere_trekk == []:
        return "samarbeid"
    if svi > sam:
        return "svik"
    else:
        return "samarbeid"

def spill_slemt(tidligere_trekk):
    if tidligere_trekk == []:
        return "samarbeid"
    for i in tidligere_trekk:
        if len(tidligere_trekk) > 4:
            return "svik"
        else:
            return "samarbeid"

def utfor_spill():
    runder = 10
    score1 = 0
    score2 = 0
    trekk1 = []
    trekk2 = []
    for i in range(runder):
        trekk1.append(spill_slemt(trekk2))
        trekk2.append(spill_snilt(trekk1))
        score = beregn_score(spill_slemt(trekk2), spill_snilt(trekk1))
        score1 += score[0]
        score2 += score[1]
    print(f"spiller1 sin score: {score1}\nspiller2 sin score: {score2}")

def utfor_spill_uendelig():
    runder = 20
    score1 = 0
    score2 = 0
    trekk1 = []
    trekk2 = []
    fortsett = "y"
    while fortsett == "y":
        for i in range(runder):
            trekk1.append(spill_snilt(trekk2))
            trekk2.append(spill_snilt(trekk1))
            score = beregn_score(spill_snilt(trekk2), min_strategi_ASDASD(trekk1,trekk2))
            score1 += score[0]
            score2 += score[1]
        print(f"spiller1 sin score: {score1}\nspiller2 sin score: {score2}")
        fortsett = input("Vil du ta en runde til? (y/n)")


def min_strategi_ASDASD(dinevalg,minevalg):
    if dinevalg == [] or minevalg == []:
        return "samarbeid"
    temp = []
    if len(dinevalg) >= 1:
        for i in range(len(dinevalg)-2,len(dinevalg)):
            temp.append(dinevalg[i])
        if temp.count("samarbeid") >= 1:
            return "svik"
    if dinevalg[len(dinevalg)-2] == "svik":
        return "svik"
    else:
        return "samarbeid"

utfor_spill_uendelig()