


a = [1,3,6,2,7,8,5]


n = len(a)
for i in range(1, n):
    while i > 0 and a[i-1] > a[i]:
        a[i-1], a[i] = a[i], a[i-1]
        i -= 1


print(a)
#Her vil den starte og lete fra i+1 så fra i+2 så i+3 osv osv
#Den henter inn elementene som er mindre enn tallene som er bak i
#så i er på måte en grense for hvor l skal lete i for loopene
#l henter der hvor i er grense område for hvor l skal starte