True and True #True
True and False #False
True or True #True
True or False #True
False or False #False
not(True) #
not(False)
True and not(True)
True and not(False)
not(True) or not(False)
((True and False) and False) or (True and False)
not(True) or (True and not(False))
(not(True) and (False or True)) or ((True and False) or not(False))

def storreEnn(tall):
    if tall > 50:
        print("Tallet er større enn 50")
        if tall > 100:
            print("Tallet er også større enn 100")
        else:
            print("Men tallet er mindre enn eller lik 100")
    elif tall > 25:
        print("Tallet er større enn 25")
        if tall > 35:
            print("Tallet er også større enn 35")
        else:
            print("Men tallet er mindre enn eller lik 35")
    else:
        print("Tallet er mindre enn eller lik 25")
