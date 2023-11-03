a = [5,4,3,2,8,6,0,1,7]
n = len(a)

# for i in range(1, n):
#     l = i
#     print(a)
#     while l > 0 and a[l-1] > a[l]:
#         a[l-1], a[l] = a[l], a[l-1]
#         print(a)
#         l -= 1
#     print()

# print(a)


for i in range(1, n):
    while i > 0 and a[i-1] > a[i]:
        a[i-1], a[i] = a[i], a[i-1]
        i -= 1
print(a)
#Her vil den starte og lete fra i+1 så fra i+2 så i+3 osv osv
#Den henter inn elementene som er mindre enn tallene som er bak i
#så i er på måte en grense for hvor l skal lete i for loopene
#l henter der hvor i er grense område for hvor l skal starte