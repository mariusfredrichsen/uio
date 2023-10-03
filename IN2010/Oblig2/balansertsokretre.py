from heapq import heappop, heappush

def create_balanced_tree(sorted_array):
    if len(sorted_array) <= 1:
        if len(sorted_array) == 1:
            print(sorted_array[0])
        return
    
    create_balanced_tree([sorted_array[len(sorted_array)//2]])
    create_balanced_tree(sorted_array[len(sorted_array) // 2 + 1:])
    create_balanced_tree(sorted_array[:len(sorted_array) // 2])

def create_balanced_tree_heap(heap):
    if len(heap) <= 1:
        if len(heap) == 1:
            print(heappop(heap))
        return
    heap_split = []
    for _ in range(len(heap)//2):
        heappush(heap_split, heappop(heap))
    print(heappop(heap))
    create_balanced_tree_heap(heap)
    create_balanced_tree_heap(sorted(heap_split))
        
def main():
    inp = input()
    sorted_array = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
    while inp != "":
        sorted_array.append(int(inp))
        inp = input().strip()
    print("<<========  EGEN GREIE ========>>")
    create_balanced_tree(sorted_array.copy())
    print("\n\n\n<<========  MED HEAP ========>>")
    create_balanced_tree_heap(sorted_array.copy())

main()