melk = 22
sjokolade = 50
banan = 5
pizza = 50

pris = 0

pris=pris+melk*int(input("hvor mange melk vill du ha? "))
pris=pris+sjokolade*int(input("hvor mange sjokolader vill du ha? "))
pris=pris+banan*int(input("hvor mange bananer vill du ha? "))
pris=pris+pizza*int(input("hvor mange pizzaer vill du ha? "))

print("Prisen blir: ", pris)
