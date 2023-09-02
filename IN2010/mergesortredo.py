def mergeSort(A):
    if len(A) <= 1:
        return A
    i = len(A)//2
    A1 = mergeSort(A[:i])
    A2 = mergeSort(A[i:])
    return merge(A1, A2, A)

def merge(A1, A2, A):
    i = 0
    l = 0
    while i < len(A1) and l < len(A2):
        if A1[i] < A2[l]:
            A[i + l] = A1[i]
            i += 1
        else:
            A[i + l] = A2[l]
            l += 1
    
    while i < len(A1):
        A[i + l] = A1[i]
        i += 1

    while l < len(A2):
        A[i + l] = A2[l]
        l += 1

    return A

print(mergeSort([4,3,7,6,8,0,1,2,4,6,8,3,5,7,9,9,4,3,2,1]))