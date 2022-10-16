C = int(input())

for i in range(C):
    N = input().split(" ")
    for x in range(len(N)):
        N[x] = int(N[x])
    teller1 = 0
    for l in range(1,len(N)):
        teller1 += N[l]
    teller1 /= N[0]
    teller2 = 0
    for l in range(1,len(N)):
        if N[l] > teller1:
            teller2 += 1

    
    teller2 /= N[0]
    print("{0:0.3f}".format(teller2*100))
            