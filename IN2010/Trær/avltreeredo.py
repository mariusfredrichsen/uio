from random import randint

class Node:
    def __init__(self, x):
        self.x = x
        self.left = None
        self.right = None
        self.height = 0

    def __str__(self):
        return str(self.x)
    
def height(v) -> int:
    if v == None:
        return -1
    return v.height

def balanceFactor(v):
    if v == None:
        return None
    return height(v.left) - height(v.right)

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
    elif v.x <= x:
        v.right = insert(v.right, x)
    elif v.x > x:
        v.left = insert(v.left, x)
    
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
    if v.x < x:
        v.right = remove(v.right, x)
        return v
    if v.x > x:
        v.left = remove(v.left, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
    
    u = findMin(v.right)
    v.x = u.x
    v.right = remove(v.right, u.x)
    v.height = 1 + max(height(v.left), height(v.right))
    return balance(v)

rot = None
rot = insert(rot, 50)

for i in range(20):
    rot = insert(rot, randint(1,100))


print(rot)
print(rot.left)
print(rot.right)
print(rot.left.left)
print(rot.left.right)
print(rot.right.left)
print(rot.right.right)

for i in range(3):
    print()
rot = remove(rot, 50)
print(rot)
print(rot.left)
print(rot.right)
print(rot.left.left)
print(rot.left.right)
print(rot.right.left)
print(rot.right.right)


    