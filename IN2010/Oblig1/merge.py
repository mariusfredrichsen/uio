from countswaps import CountSwaps

def sort(A):
    if len(A) <= 1:
        return A
    midten = len(A)//2
    A1 = sort(CountSwaps(A[:midten]))
    A2 = sort(CountSwaps(A[midten:]))
    
    i = 0
    l = 0
    while i < len(A1) and l < len(A2):
        if A1[i] <= A2[l]:
            A[i + l] = A1[i]
            i += 1
        else:
            A[i + l] = A2[l]
            l += 1
        A.swap_merge()
            
    while i < len(A1):
        A[i + l] = A1[i]
        i += 1
    
    while l < len(A2):
        A[i + l] = A2[l]
        l += 1
    
    return A
