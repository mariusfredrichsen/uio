import array
from random import randint

A = array.array('i', [0 for i in range(17)])

def first_empty(A) -> int:
    for i in range(len(A)):
        if A[i] == 0:
            return i
    return None

def insert(A, x):
    i = first_empty(A)
    A[i] = x
    j = (i-1)//2
    while i > 0 and A[i] < A[j]:
        A[i], A[j] = A[j], A[i]
        i = j
        j = (i-1)//2

for i in range(17):
    insert(A, randint(1,100))
print(A)