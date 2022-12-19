navn = input("Hva heter du? ")
print("Hei " + navn)
#setter sammen en string "Hei " og variabelen(string) "navn" i en print funksjon

Tall1 = 10
Tall2 = 20
Diff = Tall1-Tall2 #differansen mellom første og andre tall variabel
print(Tall1)
print(Tall2)
print("Differanse: " + str(Diff)) #gjør variabelen "Diff" om til en string inne i print funksjonen

navn1 = input("Kan du gi meg et nytt navn? ")
sammen = navn + navn1 #setter sammen navn variabelene
print(sammen)

sammen = f"{navn} og {navn1}" #gjør om på den forrige variabelen og seperer navn variabelene med en f-string
print(sammen)
