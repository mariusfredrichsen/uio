import math
r = float(input("Velg en radius på en sirkel:\n"))

dia = r*2
areal = math.pi*r**2
omkr = 2*r*math.pi

def formatering():
    table = {'Diameter': dia, 'Areal': areal, 'Omkrets': omkr}

    for sirkel, rad in table.items():
        print(f"{sirkel:10} ==> {rad:.2f}")

formatering()
