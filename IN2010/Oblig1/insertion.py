def sort(A):
    for i in range(1, len(A)):
        l = i
        while l > 0 and A[l-1] > A[l]:
            A.swap(l, l-1)
            l -= 1  
    # Do insertion sort here. Use the Sorter's comparison- and swap
    # methods for automatically counting the swaps and comparisons.

    # Use A.swap(i, j) to swap the values at two indices i and j. The swap is
    # counted, when using this method. Comparisons are counted automatically.

    return A
