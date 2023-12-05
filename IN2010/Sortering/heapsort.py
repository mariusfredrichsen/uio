from random import randint





def bubble_down(A, i, n):
    largest = i
    left = 2*i + 1
    right = 2*i + 2
    
    if left < n and A[largest] < A[left]:
        largest, left = left, largest
    
    if right < n and A[largest] < A[right]:
        largest, right = right, largest
    
    if i != largest:
        A[i], A[largest] = A[largest], A[i]
        bubble_down(A, largest, n)

def build_max_heap(A, n):
    for i in range(n//2, -1, -1):
        bubble_down(A, i, n)

def heap_sort(A):
    build_max_heap(A, len(A))
    for i in range(len(A)-1, -1, -1):
        A[0], A[i] = A[i], A[0]
        bubble_down(A, 0, i)


def main():
    A = [randint(0, 99) for i in range(100)]
    heap_sort(A)
    print(A)


main()