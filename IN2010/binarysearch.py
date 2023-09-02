sorted_list = [i for i in range(1,101)]

#A er listen den leter og x er blinken 
def binarySearch(A, x):
    low  = 0
    high = len(A)

    while low <= high:
        mid = (low + high)//2
        print(mid)
        if A[mid] == x:
            return True
        elif A[mid] < x:
            low = mid+1
        else:
            high = mid-1
    return False

print(binarySearch(sorted_list, 100))
print(len(sorted_list))
