class Node:
    def __init__(self, data: int):
        self.data = data
        self.left = None
        self.right = None
    
    def write(self):
        if self.left != None:
            self.left.write()
        if self.right != None:
            self.right.write()
        print(self)

    def __str__(self):
        return str(self.data)
    
def insert(v, x):
    if v == None:
        v = Node(x)
    elif x < v.data:
        v.left = insert(v.left, x)
    elif x >= v.data:
        v.right = insert(v.right, x)
    return v

def search(v, x):
    if v == None:
        return None
    if v.data == x:
        return v
    elif x < v.data:
        return search(v.left, x)
    elif x > v.data:
        return search(v.right, x)

def findMin(v):
    #finner noden med ingen venstre node, aka minst
    if v == None:
        return None
    if v.left == None:
        return v
    return findMin(v.left)

def remove(v, x):
    if v == None:
        return None
    if x < v.data:
        v.left = remove(v.left, x)
        return v
    elif x > v.data:
        v.right = remove(v.right, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
    u = findMin(v.right)
    v.data = u.data
    v.right = remove(v.right, u.data)
    return v
    


rot = None
rot = insert(rot, 29)
for i in [71,96,97,82,64,64,65,72,50,53]:
    rot = insert(rot, i)
rot.write()
print(search(rot, 5))
print(findMin(rot.right), "MINST")
print(rot.right)
print(rot.right.right.left.left)
print(remove(rot, 50))
print(rot.right.right.left.left)
print(findMin(rot.right), "MINST")
print(findMin(rot.right.right))
