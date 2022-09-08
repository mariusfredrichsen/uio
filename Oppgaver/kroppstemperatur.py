temp = float(input("Hva er din kroppstemperatur?\n"))
if temp < 36.5:
    print("Under")
elif temp >= 36.5 and 37.5 >= temp:
    print("Passe")
elif temp > 37.5:
    print("Over")
else:
    print("?")
