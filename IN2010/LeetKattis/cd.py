N, M = map(int, input().strip().split())

while N and M:

    jill = set()
    jack = set()
    
    for n in range(N):
        jill.add(input().strip())
    
    for m in range(M):
        jack.add(input().strip())
    
    print(len(jill.intersection(jack)))
    N, M = map(int, input().strip().split())