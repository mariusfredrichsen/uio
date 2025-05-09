from random import randint

def partition(A, low, high):
    p = randint(low, high)
    A[p], A[high] = A[high], A[p]
    
    pivot = A[high]
    left = low
    right = high-1
    
    while left <= right:
        while left <= right and A[left] <= pivot:
            left += 1
        while right >= left and A[right] >= pivot:
            right -= 1
        if left < right:
            print(A)
            A[left], A[right] = A[right], A[left]
    
    A[left], A[high] = A[high], A[left]
    print(A)
    print(pivot)
    return left

def quicksort(A, low, high):
    if low >= high:
        return A
    p = partition(A, low, high)
    quicksort(A, low, p - 1)
    quicksort(A, p + 1, high)
    return A

def main():
    A = [randint(0,99) for _ in range(15)]
    print(quicksort(A, 0, len(A)-1))
    
main()
    
    
    
    