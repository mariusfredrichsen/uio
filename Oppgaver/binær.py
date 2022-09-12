tall = int(input("Skriv inn et tall:\n"))
bin = []
n = 0

while tall > 0:
    bin.append(tall%2)
    tall = tall//2
    n+=1

bin.reverse()
bin = ''.join(str(e) for e in bin)
print(f"Her er binær versjonen av tallet: {bin}")