#Oppgave 1
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
    return score #Returnerer en liste der hvor først element er scoren til spiller 1 og andre element er scoren til spiller 2


#Oppgave 2
def spill_snilt(tidligere_trekk):
    svi = 0
    sam = 0
    for i in tidligere_trekk:
        if i == "svik":
            svi += 1
        else:
            sam += 1
    if tidligere_trekk == []: 
        return "samarbeid" #Hvis det er første runde så skal den samarbeide
    if svi > sam:
        return "svik" #Hvis det er flere svik enn samarbeid i lista så skal den svike
    else:
        return "samarbeid" #Ellers bare samarbeider den


#Oppgave 3
def spill_slemt(tidligere_trekk):
    if tidligere_trekk == []:
        return "samarbeid" #Samarbeider alltid den første runden
    for i in tidligere_trekk:
        if len(tidligere_trekk) > 4: 
            return "svik" #Sviker alltid etter 5 trekk
        else:
            return "samarbeid" #Samarbeider hvis det ikke har gått mer enn 5 trekk


#Oppgave 4
def utfor_spill():
    runder = 10
    score1 = 0
    score2 = 0
    trekk1 = []
    trekk2 = []
    for i in range(runder): #Spiller 10 runder
        trekk1.append(spill_slemt(trekk2)) #Spiller 1 sjekker spiller 2 sine trekk
        trekk2.append(spill_snilt(trekk1)) #Motsatt
        score = beregn_score(spill_slemt(trekk2), spill_snilt(trekk1)) #Beregner scoren ut ifra hva prosedyrene returnerer
        score1 += score[0]
        score2 += score[1] #Plukker ut elementene fra listene (kunne egt ha skrevet "score[0/1]" i f stringen under, men er for lat)
    print(f"spiller1 sin score: {score1}\nspiller2 sin score: {score2}")


#Oppgave 5
def utfor_spill_uendelig():
    runder = 20
    score1 = 0
    score2 = 0
    trekk1 = []
    trekk2 = []
    fortsett = "y"
    while fortsett == "y": #Setter opp en while loop som looper bare når fortsett er lik "y"
        for i in range(runder):
            trekk1.append(spill_snilt(trekk2))
            trekk2.append(spill_snilt(trekk1))
            score = beregn_score(spill_snilt(trekk2), min_strategi_ASDASD(trekk1,trekk2))
            score1 += score[0]
            score2 += score[1]
        print(f"spiller1 sin score: {score1}\nspiller2 sin score: {score2}")
        fortsett = input("Vil du ta en runde til? (y/n)") #Forandrer variabelen "fortsett" til enten "y" eller "n"


#Oppgave 6
def min_strategi_ASDASD(dinevalg,minevalg):
    if dinevalg[-1] == "svik":
        return "samarbeid"
    return "svik"



utfor_spill_uendelig()