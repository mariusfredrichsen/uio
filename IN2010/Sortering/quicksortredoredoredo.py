from random import randint

def partition(A, low, high):
    p = randint(low, high)
    A[high], A[p] = A[p], A[high]
    
    pivot = A[high]
    left = low
    right = high - 1
    
    while left <= right:
        while left <= right and A[left] <= pivot:
            left += 1
        while right >= left and A[right] >= pivot:
            right -= 1
        if left < right:
            A[left], A[right] = A[right], A[left]
    
    A[left], A[high] = A[high], A[left]
    return left

def quicksort(A, low, high):
    if low >= high:
        return
    p = partition(A, low, high)
    quicksort(A, low, p - 1)
    quicksort(A, p + 1, high)
    return A

def main():
    A = [(3, 'a'), (2, 'a'), (2, 'b'), (3, 'b')]

    
    quicksort(A, 0, len(A)-1)
    
    print(A)
    
main()
    