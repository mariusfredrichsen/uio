def find_median(A):
    if len(A)%2 == 0:
        return (A[len(A)//2-1] + A[(len(A)//2)])//2
    return A[len(A)//2]

def insertionsort(A):
    for i in range(1, len(A)):
        while i > 0 and A[i-1] > A[i]:
            A[i-1], A[i] = A[i], A[i-1]
            i -= 1

t = int(input())

for i in range(t):
    n = int(input())
    A = [int(num) for num in input().split()]
    sum = 0
    new_A = []
    for j in A:
        new_A.append(j)
        insertionsort(new_A)
        sum += find_median(new_A)
    print(sum)