
# Kort klasse med mønster og nummer
class Card:
    def __init__(self, type: str, num: int):
        self.type = type
        self.num = num
    
    def __str__(self):
        return f"{self.type} {self.num}"


def bucketsort(cards: list, rules: dict, sort_rule: str):
    # lager N bøtter der hvor n er hvor mange regler vi har
    buckets = [[] for _ in rules]
    
    # går over kortene i kortstokken og sjekker hva vi skal sorter på
    for card in cards:
        if sort_rule == "mønster":
            k = rules[card.type]
            
        elif sort_rule == "nummer":
            k = rules[card.num]
            
        buckets[k].append(card)
    
    # legger tilbake kortene i kortstokken igjen
    i = 0
    for bucket in buckets:
        for card in bucket:
            cards[i] = card
            i += 1


# lager reglene
type_rule = {"spar": 0, 
             "hjerte": 1, 
             "kløver": 2, 
             "ruter": 3}

number_rule = dict()
for i in range(1,14):
    number_rule[i] = i



# kort stokken
cards = [
    Card("spar", 5),
    Card("hjerte", 3),
    Card("kløver", 12),
    Card("ruter", 7),
    Card("spar", 1),
    Card("hjerte", 9),
    Card("kløver", 4),
    Card("ruter", 2),
    Card("spar", 11),
    Card("hjerte", 6)
]


print("sorterer på nummer")
bucketsort(cards, number_rule, "nummer")
for card in cards:
    print(card)

print("\n\n\n")

print("sorterer på mønster")
bucketsort(cards, type_rule, "mønster")
for card in cards:
    print(card)



# overskriver den sorterte kortstokken
cards = [
    Card("spar", 5),
    Card("hjerte", 3),
    Card("kløver", 12),
    Card("ruter", 7),
    Card("spar", 1),
    Card("hjerte", 9),
    Card("kløver", 4),
    Card("ruter", 2),
    Card("spar", 11),
    Card("hjerte", 6)
]


def radixsort(cards: list, rule_list: list, rule_type_list: list):
    
    # går over reglene som er sent in og sorterer kortstokken i den rekkefølgen
    for rule, rule_type in zip(rule_list, rule_type_list):
        bucketsort(cards, rule, rule_type)

print("\n\n\n")
print("radix sort")

# sender inn en liste med regler med en tilsvarende liste med regel type
radixsort(cards, [number_rule, type_rule], ["nummer", "mønster"])

for card in cards:
    print(card)

    
    
    