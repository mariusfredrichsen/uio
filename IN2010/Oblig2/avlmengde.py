class Set:
    def __init__(self):
        self.root = None
        self.size = 0
    
class Node:
    def __init__(self, data):
        self.height = 0
        self.data = data
        self.left = None
        self.right = None        

def height(v):
    if v == None:
        return -1
    return v.height

def balanceFactor(v):
    if v == None:
        return 0
    return height(v.left) - height(v.right)

def leftRotation(v):
    u = v.right
    c = u.left
    
    u.left = v
    v.right = c
    
    v.height = 1 + max(height(v.left), height(v.right))
    u.height = 1 + max(height(u.left), height(u.right))
    
    return u

def rightRotation(v):
    u = v.left
    c = u.right
    
    u.right = v
    v.left = c
    
    v.height = 1 + max(height(v.left), height(v.right))
    u.height = 1 + max(height(u.left), height(u.right))
    
    return u

def balance(v):
    if balanceFactor(v) > 1:
        if balanceFactor(v.left) < 0:
            v.left = leftRotation(v.left)
        return rightRotation(v)
    if balanceFactor(v) < -1:
        if balanceFactor(v.right) > 0:
            v.right = rightRotation(v.right)
        return leftRotation(v)
    return v   
        
def contains(set, x):
    v = set.root
    while True:
        if v == None:
            break
        elif v.data == x:
            return True
        elif v.data < x:
            v = v.right
            continue
        elif v.data > x:
            v = v.left
            continue
    return False

def insertRec(v, x):
    if v == None:
        v = Node(x)
    elif x < v.data:
        v.left = insertRec(v.left, x)
    elif x >= v.data:
        v.right = insertRec(v.right, x)
    v.height = 1 + max(height(v.left), height(v.right))
    return balance(v)
    
def insert(set, x):
    if not contains(set, x):
        set.size += 1
        set.root = insertRec(set.root, x)

def findMin(v):
    if v == None:
        return None
    if v.left == None:
        return v
    return findMin(v.left)

def removeRec(v, x):
    if v == None:
        return None
    if v.data > x:
        v.left = removeRec(v.left, x)
        return v
    if v.data < x:
        v.right = removeRec(v.right, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
    u = findMin(v.right)
    v.data = u.data
    v.right = removeRec(v.right, u.data)
    v.height = 1 + max(height(v.left), height(v.right))
    return balance(v)

def remove(set, x):
    if contains(set, x):
        set.size -= 1
        set.root = removeRec(set.root, x)

def size(set):
    return set.size

def main(filnavn):
    set1 = Set()
    with open(filnavn) as f:
        teller = 0
        for linje in f:
            if teller == 0:
                teller += 1
                continue

            #f = open("inputs/input_10000", "r")
            #antall = int(f.readline())

            linje = linje.strip().split(" ")
            #linje = f.readline().strip().split(" ")
            command = linje[0]

            if len(linje) == 2:
                nmb = int(linje[1])

            if command == "insert":
                insert(set1, nmb)            

            elif command == "remove":
                remove(set1, nmb)

            elif command == "contains":
                if contains(set1, nmb):
                    print("true")
                else:
                    print("false")

            elif command == "size":
                print(size(set1))

    
main()