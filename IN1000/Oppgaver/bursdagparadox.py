from random import randint

ulik = 0
lik = 0
for i in range(1000):
    liste = []
    for i in range(60):
        liste.append(randint(1,365))

    for elem in liste:
        if liste.count(elem) > 1:
            lik += 1
            break
        else:
            ulik += 1
            break

print("lik: ", lik)
print("ulik: ", ulik)