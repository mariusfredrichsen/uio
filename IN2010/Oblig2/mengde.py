class Set:
    def __init__(self):
        self.root = None
        self.size = 0
    
class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
        
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
    return v
    
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
    return v

def remove(set, x):
    if contains(set, x):
        set.size -= 1
        set.root = removeRec(set.root, x)

def size(set):
    return set.size

def main():
    set1 = Set()
    antall = int(input())

    for _ in range(antall):
        linje = input().strip().split(" ")
        command = linje[0]

        if command == "insert":
            insert(set1, int(linje[1]))
        elif command == "remove":
            remove(set1, int(linje[1]))
        elif command == "contains":
            if contains(set1, int(linje[1])):
                print("true")
            else:
                print("false")
        else:
            print(size(set1))

main()