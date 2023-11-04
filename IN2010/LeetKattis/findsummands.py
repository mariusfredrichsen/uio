import random


def findSummands(A, x):
    i = 0
    while i < len(A):
        result = binarySearch(A, x - A[i])
        if result == -1 or result == i:
            i += 1
        else:
            print(A[i], A[result])
            del A[i]
            del A[result]




def binarySearch(A, x):
    low  = 0
    high = len(A)

    while low <= high:
        mid = (low + high)//2
        if A[mid] == x:
            return mid
        elif A[mid] < x:
            low = mid+1
        else:
            high = mid-1
    return -1
    


findSummands(sorted(random.sample(range(0,8), 5)), 8)
