a = [0,2,3,8,7,1,5,4]
n = len(a)

for i in range(n):
    print(a)
    k = i
    for l in range(i+1,n):
        print(i, k)
        if a[l] < a[k]:
            k = l
    if i != k:
        a[i], a[k] = a[k], a[i]

print(a)

#Her sorteres listen etter å finne den minste og sette den til i (iterasjons variabelen til listen)
#Den leter igjennom listen og finner det minste og setter k til å være den posisjonen (så k = l som er posisjons variabelen til den innerste for loopen)
#Hvis i og k skulle være lik så gjør man ikke noe