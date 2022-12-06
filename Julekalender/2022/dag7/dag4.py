f = open("input.txt")
sluk = 0
for linje in f:
    linje = [linje.strip().split(",")[0].strip().split("-"), linje.strip().split(",")[1].strip().split("-")]
    if int(linje[0][0]) >= int(linje[1][0]) and int(linje[0][1]) <= int(linje[1][1]) or int(linje[0][0]) <= int(linje[1][0]) and int(linje[0][1]) >= int(linje[1][1]):
        sluk += 1
        print(linje)

print(sluk)

overlapp = 0
for linje in f:
    linje = [linje.strip().split(",")[0].strip().split("-"), linje.strip().split(",")[1].strip().split("-")]
    
    liste1 = []
    liste2 = []
    for i in range(int(linje[0][0]),int(linje[0][1])+1):
        liste1.append(i)
    for i in range(int(linje[1][0]),int(linje[1][1])+1):
        liste2.append(i)
    
    for elem in liste1:
        if elem in liste2:
            overlapp += 1
            break

print(overlapp)