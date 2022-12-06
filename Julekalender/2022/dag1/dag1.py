def les(filename):
    with open(filename,'r') as infile:
        kal=[]
        sum=0
        for line in infile:
            if line=='\n':
                kal.append(sum)
                sum=0
            else:
                line=int(line)
                sum+=line
    return kal

def kal(list):
    mest=list[0]
    for i in range(len(list)):
        if mest<list[i]:
            mest=list[i]
    return mest

<<<<<<< HEAD:Julekalender/dag1/dag1.py
list=les('alveliste.txt')
print(kal(list))
=======
for linje in f:
    linje = linje.strip()
    if linje == "":
        liste.append(0)
    else:
        liste[-1] += int(linje)

for i in range(len(liste)):
    for l in range(len(liste)-1):
        if liste[l] > liste[l+1]:
            liste[l],liste[l+1] = liste[l+1],liste[l]

print(liste[-1])
print(liste[-1] + liste[-2] + liste[-3])
>>>>>>> 6f626e951d35d08f2071c48a64ba7ed3935396e4:Julekalender/2022/dag1/dag1.py
