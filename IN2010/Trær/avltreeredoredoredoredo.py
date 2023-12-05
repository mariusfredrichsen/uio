


class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.height = 0
        
    def skriv_ut(self):
        print(self)
        if self.left:
            self.left.skriv_ut()
        if self.right:
            self.right.skriv_ut()
    
    def __str__(self):
        return f"Height: {self.height} , Value: {self.value}"
    
def height(v):
    return -1 if not v else v.height

def set_height(v):
    if v:
        v.height = 1 + max(height(v.left), height(v.right))
        
def balance_factor(v):
    return 0 if not v else height(v.left) - height(v.right)

def left_rotate(v):
    u = v.right
    z = u.left
    
    v.right = z
    u.left = v
    
    set_height(v)
    set_height(u)
    return u

def right_rotate(v):
    u = v.left
    z = u.right
    
    v.left = z
    u.right = v
    
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
        return Node(x)
    if v.value > x:
        v.left = insert(v.left, x)
    elif v.value < x:
        v.right = insert(v.right, x)
    
    set_height(v)
    return balance(v)

def find_min(v):
    if v == None:
        return None
    if v.left == None:
        return v
    return find_min(v.left)

def remove(v, x):
    if not v:
        return None
    if v.value > x:
        v.left = remove(v.left, x)
        return v
    if v.value < x:
        v.right = remove(v.right, x)
        return v
    if v.left == None:
        return v.right
    if v.right == None:
        return v.left
    
    u = find_min(v.right)
    v.value = u.value
    v.right = remove(v.right, u.value)
    set_height(v)
    return balance(v)
    
    
    
    
    
    
    
def main():
    root = None
    for i in range(10):
        root = insert(root, i)
    
    root = remove(root, 7)
    
    
    root.skriv_ut()




if __name__ == '__main__':
    main()