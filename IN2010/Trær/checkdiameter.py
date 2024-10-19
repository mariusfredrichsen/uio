class Node:
    def __init__(self, data: int):
        self.data = data
        self.left = None
        self.right = None
        self.h = 0

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


def fix_height(v):
    if not v:
        return -1
    
    v.h = 1 + max(fix_height(v.left), fix_height(v.right))
    return v.h

def get_height(v):
    if not v:
        return -1
    return v.h

def checkdiameter(v):
    if not v:
        return 0
    
    d = get_height(v.left) + get_height(v.right) + 2
    
    ld = checkdiameter(v.left)
    rd = checkdiameter(v.right)
    
    return max(d, ld, rd)



def main():
    rot = Node(5)
    for i in [3, 4, 10, 9, 8, 7, 6, 11, 12]:
        rot = insert(rot, i)
    
    fix_height(rot)
    print(checkdiameter(rot))
    
    
    
main()