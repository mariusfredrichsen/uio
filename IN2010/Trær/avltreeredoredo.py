





class Node:
    def __init__(self, x):
        self.x = x
        self.left = None
        self.right = None
        self.h = 0
    
    def skriv_ut(self):
        print(self)
        if self.left:
            self.left.skriv_ut()
        if self.right:
            self.right.skriv_ut()
    
    def __str__(self):
        return f"Height: {self.h} , Value: {self.x}"
    
def get_height(v):
    return -1 if not v else v.h

def set_height(v):
    v.h = 1 + max(get_height(v.left), get_height(v.right))

def balance_factor(v):
    return None if not v else get_height(v.left) - get_height(v.right)

def left_rotate(v):
    u = v.right
    z = u.left
    
    u.left = v
    v.right = z
    
    set_height(v)
    set_height(u)
    return u

def right_rotate(v):
    u = v.left
    z = u.right
    
    u.right = v
    v.left = z
    
    set_height(v)
    set_height(u)
    return u

def balance(v):
    if balance_factor(v) > 1:
        if balance_factor(v.left) < 0:
            v.left = left_rotate(v.left)
        return right_rotate(v)
    elif balance_factor(v) < -1:
        if balance_factor(v.right) > 0:
            v.right = right_rotate(v.right)
        return left_rotate(v)
    return v

def insert(v, x):
    if not v:
        v = Node(x)
    elif x <= v.x:
        v.left = insert(v.left, x)
    elif x > v.x:
        v.right = insert(v.right, x)
        
    set_height(v)
    return balance(v)

def find_min(v):
    if v == None:
        return None
    return v if not v.left else find_min(v.left)

def find_max(v):
    if v == None:
        return None
    return v if not v.right else find_max(v.right)

def remove(v, x):
    if v == None:
        return None
    if v.x == x:
        pass
    if x > v.x:
        v.right = remove(v.right, x)
        return v
    if x < v.x:
        v.left = remove(v.left, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
        
    u = find_max(v.left)
    v.x = u.x
    remove(v.left, u.x)
    set_height(v)
    return balance(v)

def main():
    root = None
    for i in range(10):
        root = insert(root, i)
    
    root = remove(root, 7)
    
    
    root.skriv_ut()



main()