def poeng_lager(liste):
    valg1 = liste[0]
    valg2 = liste[1]
    score = 0

    if valg2 == "X": # stein
        score += 1
        if valg1 == "A": # stein, uavgjort
            score += 3
        elif valg1 == "B": # papir, taper
            pass
        elif valg1 == "C": # saks, vinner
            score += 6
            
    elif valg2 == "Y": # papir
        score += 2
        if valg1 == "A": # stein, vinner
            score += 6
        elif valg1 == "B": # papir, uavgjort
            score += 3
        elif valg1 == "C": # saks, taper
            pass

    elif valg2 == "Z": # saks
        score += 3
        if valg1 == "A": # stein, taper
            pass
        elif valg1 == "B": # papir, vinner
            score += 6
        elif valg1 == "C": # saks, uavgjort
            score += 3

    return score

def poeng_lager_2(liste):
    valg = liste[0]
    resultat = liste[1]
    score = 0
    
    if resultat == "X": # tap
        if valg == "A": # stein, må velge saks
            score += 3
        elif valg == "B": # papir, må velge stein
            score += 1
        elif valg == "C": # saks, må velge papir
            score += 2
              
    if resultat == "Y": # uavgjort
        score += 3
        if valg == "A": # stein
            score += 1
        elif valg == "B": # papir
            score += 2
        elif valg == "C": # saks
            score += 3

    if resultat == "Z": # vinn
        score += 6
        if valg == "A": # stein, velg papir
            score += 2
        elif valg == "B": # papir, velg saks
            score += 3
        elif valg == "C": # saks, velg stein
            score += 1
    
    return score

f = open("input.txt")
total_score = 0
for linje in f:
    total_score += poeng_lager_2(linje.strip().split())

print(total_score)