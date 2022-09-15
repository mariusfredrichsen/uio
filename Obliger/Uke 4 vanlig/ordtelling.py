def ordteller(ord):
    nBokstaver = 0
    for i in ord:
        nBokstaver += 1
    
    return nBokstaver

def setningTeller(setning):
    ordbok = {}
    setning = setning.split(" ")
    for i in setning:
        nKopi = 0
        for l in setning:
            if i == l:
                nKopi += 1
        ordbok[i] = nKopi
    
    return ordbok

def setningOgOrdTeller(setning):
    ordbok = setningTeller(setning)
    print(f"Det er {len(setning.split(' '))} ord i setningen din. ")
    for i in ordbok:
        nBokstaver = ordteller(i)
        print(f"Ordet {i} oppstår {ordbok[i]} ganger, og har {nBokstaver} bokstaver.")

setning = "mamma elsker bamser fordi mamma hater bamser din klovn"
setningOgOrdTeller(setning)
