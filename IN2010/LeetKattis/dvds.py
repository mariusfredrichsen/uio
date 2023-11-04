a = [4,5,3,2,6,8,7,1,9]

for i in range(len(a)):
    k = 0
    print(a)
    for i in range(len(a)-1):
        if a[i] > a[-1] and a[i] < a[k]:
            k = i
    print(a[k])
    a.append(a.pop(k))