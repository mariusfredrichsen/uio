def nest_størst(første, andre, tredje, fjærde):
    liste = [første, andre, tredje, fjærde]
    for l in range(len(liste)):
        for i in range(len(liste)-1):
            if liste[i] > liste[i+1]:
                liste[i], liste[i+1] = liste[i+1], liste[i]
    
    return liste

print(nest_størst(100,0,99,332))