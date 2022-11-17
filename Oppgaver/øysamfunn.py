from random import randint

def fødsel():
    barn = []
    for i in range(4):
        if randint(0, 1) == 0:
            barn.append("jente")
            return barn
        else:
            barn.append("gutt")
    return barn

def simuler():
    gutter_og_jenter = []
    for i in range(1000000):
        for barn in fødsel():
            gutter_og_jenter.append(barn)
    
    return gutter_og_jenter

liste = simuler()
print("jenter: ", liste.count("jente"), "gutter: ", liste.count("gutt"))