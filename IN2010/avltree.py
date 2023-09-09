class Node:
    def __init__(self, data: int):
        self.data = data
        self.left = None
        self.right = None
        self.height = 0

    def write(self):
        print(self)
        if self.left != None:
            self.left.write()
        if self.right != None:
            self.right.write()
    
    def __str__(self):
        return f"{self.height}, {self.data}"
    
def height(v):
    if v == None:
        return -1
    return v.height

def leftRotate(v):
    u = v.right
    c = u.left

    u.left = v
    v.right = c

    v.height = 1 + max(height(v.left), height(v.right))
    u.height = 1 + max(height(u.left), height(u.right))

    return u

def rightRotate(v):
    u = v.left
    c = u.right

    u.right = v
    v.left = c

    v.height = 1 + max(height(v.left), height(v.right))
    u.height = 1 + max(height(u.left), height(u.right))

    return u

def balanceFactor(v):
    if v == None:
        return 0
    return height(v.left) - height(v.right)

def balance(v):
    if balanceFactor(v) < -1:
        if balanceFactor(v.right) > 0:
            v.right = rightRotate(v.right)
        return leftRotate(v)
    if balanceFactor(v) > 1:
        if balanceFactor(v.left) < 0:
            v.left = leftRotate(v.left)
        return rightRotate(v)
    return v

def insert(v, x):
    if v == None:
        v = Node(x)
    elif x < v.data:
        v.left = insert(v.left, x)
    elif x > v.data:
        v.right = insert(v.right, x)
    v.height = 1 + max(height(v.left), height(v.right))
    return balance(v)

def findMin(v):
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
    if x > v.data:
        v.right = remove(v.right, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
    u = findMin(v.right)
    v.data = u.data
    v.right = remove(v.right, u.data)
    v.height = max(height(v.left), height(v.right))
    return balance(v)


def inRange(v, a, b):
    if v.left != None:
        if v.left.data >= a and v.left.data <= b:
            inRange(v.left, a, b)
    if v.right != None:
        if v.right.data >= a and v.right.data <= b:
            inRange(v.right, a, b)
    if v.data >= a and v.data <= b:
        pass
        print(v)

def findClosest(v, x):
    closest = float('inf')
    if v == None:
        return closest
    if v.data < x:
        closest = findClosest(v.right, x)
        print("asd")
    if v.data > x:
        closest = findClosest(v.left, x)
        print("asdsg")
    if v.data == x:
        closest = x
    if abs(v.data-x) < abs(closest-x):
        closest = v.data
    return closest
    
    
"""rot = None
for i in [10,5,2,7,15,12,17]:
    rot = insert(rot,i)"""
rot = None
for i in [10,5,15,2,7,12,17]:
    rot = insert(rot, i)

print(findClosest(rot, 13))

