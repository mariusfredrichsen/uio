class Node:
    def __init__(self, element):
        self.element = element
        self.left = None
        self.right = None
        
    def __str__(self):
        return str(self.element)

def FindMin(v):
    if v.left == None:
        return v
    return FindMin(v.left)

def FindMax(v):
    if v.right == None:
        return v
    return FindMin(v.right)

def CheckBST(v):
    left = True
    right = True
    if v.left != None:
        left = FindMax(v.left).element < v.element
        CheckBST(v.left)
        
        
    if v.right != None:
        right = FindMin(v.right).element > v.element
        CheckBST(v.right)
    
    return right and left
    
    


def main():
    root = Node(5)
    root.left = Node(2)
    root.right = Node(7)
    root.left.left = Node(1)
    root.left.right = Node(9)
    root.right.left = Node(6)
    
    print(CheckBST(root))




main()