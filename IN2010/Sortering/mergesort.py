def mergeSort(A):
    if len(A) <= 1:
        print(A, "Jeg er i mergeSort")
        return A
    i = len(A)//2
    A1 = mergeSort(A[:i])
    A2 = mergeSort(A[i:])
    return merge(A1, A2, A)

#Splitter opp listene inn i n antall lister der hvor de holder på ett element hver
#deretter setter dem sammen og sorterer dem med en sorterings algortime (merge)
#A i alle tilfeller er en form for halverte lister (A1 og A2) utenom helt til slutt der hvor man har de to siste listene

def merge(A1, A2, A):
    print(A, "Jeg er i starten av merge")
    i = 0
    l = 0
    while i < len(A1) and l < len(A2):
        if A1[i] <= A2[l]:
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
    
    print(A, "Jeg er i slutten av merge")
    return A