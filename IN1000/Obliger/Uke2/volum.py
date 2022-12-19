#Du skal lage et program som sjekker volumet til en kjegle der hvor brukeren
#angir radius og høyde.

#Print volumet av kjeglen med maks 2 desimaler.
#Hvis volumet av kjeggelen er større enn 1500 så skal du printe "Volumet var stort"
#Hvis volumet av kjeggelen er mindre enn 150 så skal du printe "Volumet var lite"
#eller skal du printe "Volumet var passe"

#Bruk formelen (r*r*h*3.14)/3


r = float(input("Angi en radius for en kjegle:\n"))
h = float(input("Angi en høyde for en kjegle:\n"))
#får tak i verdiene for radius og høyde og passer på desimaltall

v = (r**2*h*3.14)/3
#regner ut volumet

print(f"Volum av Kjegle: {v:.2f}")
if v < 150:
    print("Volumet var lite")
elif v > 1500:
    print("Volumet var stort")
else:
    print("Volumet var passe")
#bruker if, elif og else til å printe ut forskjellige ord til forskjellige situasjoner
