varer = {"melk": 14.90, "brød": 24.90, "yoghurt": 12.90, "pizza": 39.90}
print(varer)

vare1 = input("Velg en vare:\n")
varer[vare1] = int(input("Hva er prisen for varen?\n"))
vare2 = input("Velg en vare:\n")
varer[vare2] = int(input("Hva er prisen for varen?\n"))
print(varer)