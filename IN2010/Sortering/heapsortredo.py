from random import randint



def bubble_down(A, i, n):
    largest = i
    left = i*2+1
    right = i*2+2

    if left < n and A[largest] < A[left]:
        left, largest = largest, left
    
    if right < n and A[largest] < A[right]:
        right, largest = largest, right
    
    if i != largest:
        A[largest], A[i] = A[i], A[largest]
        bubble_down(A, largest, n)
        
def build_max_heap(A, n):
    for i in range(n//2, -1, -1):
        bubble_down(A, i, n)
        
def heapsort(A):
    build_max_heap(A, len(A))
    for i in range(len(A)-1, -1, -1):
        A[0], A[i] = A[i], A[0]
        bubble_down(A, 0, i)


def is_sorted(A) -> bool:
    i = 0
    while i < len(A)-2:
        if A[i] > A[i+1]:
            return False
        i += 1
    return True

def main():
    
    for i in range(1000):
        
        A = [randint(0, 99) for i in range(100)]
        
        heapsort(A)
        
        print(is_sorted(A))
    
main()