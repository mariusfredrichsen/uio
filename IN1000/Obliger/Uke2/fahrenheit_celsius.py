fahr = float(input("Skriv inn en temperatur i fahrenheit:\n"))
#passer på at inputtet er et tall og kan være et desimal tall
print("Fahrenheit: ", fahr)

cels = ((fahr)-32)*5/9
#bruker den gitte formelen for fahrenheit til celsius
print(f"Celsius: {cels:.2f}")
#formaterer til at det er bare 2 desimaler slik at det ser litt bedre ut
