class Node:
    def __init__(self, data):
        self.right = None
        self.left = None
        self.data = data
    
def insert(v, x):
    if v == None:
        v = Node(x)
    elif x < v.data:
        v.left = insert(v.left, x)
    elif x > v.data:
        v.right = insert(v.right, x)
    return v


info = input().strip().split()
height = int(info[0])
path = info[1]
root = None

root_number = 1
extra = 0
for i in range(height):
    root_number *= 2
    extra += root_number//2
root_number += extra

for i in range(root_number, 0, -1):
    print(i)
    root = insert(root, i)

print(root.left.right)