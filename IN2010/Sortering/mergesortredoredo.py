from random import randint

def mergesort(A):
    n = len(A)
    if n <= 1:
        return A
    i = n//2
    
    A1 = mergesort(A[:i])
    A2 = mergesort(A[i:])
    return sort(A, A1, A2)

def sort(A, A1, A2):
    i = j = 0   
    
    while i < len(A1) and j < len(A2):
        if A1[i] <= A2[j]:
            A[i + j] = A1[i]
            i += 1
        else:
            A[i + j] = A2[j]
            j += 1
    
    while i < len(A1):
        A[i + j] = A1[i]
        i += 1
    
    while j < len(A2):
        A[i + j] = A2[j]
        j += 1
    
    return A

def main():
    A = [randint(0, 99) for i in range(100)]
    
    mergesort(A)
    
    print(A)

main()