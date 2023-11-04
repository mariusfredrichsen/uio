from collections import defaultdict





def solve():
    n_boxes = int(input())
    children = defaultdict(set)
    boxes = [int(i) for i in input().strip().split()]
    for i in range(n_boxes):
        children[boxes[i]].add(i+1)
    
        
    for i in range(int(input())):
        boxes = set()
        query = set([int(i) for i in input().strip().split()])
        for q in query:
            visited = set()
            queue = [q]
            while queue:
                v = queue.pop(0)
                if v not in visited:
                    visited.add(v)
                    boxes.add(v)
                    for u in children[v]:
                        queue.append(u)
                        boxes.add(u)
        print(boxes, len(boxes))
    
solve()