import graphviz

class Node:
    def __init__(self, element):
        self.element = element
        self.left = None
        self.right = None
        self.height = None

class set:
    def __init__(self):
        self.root = None
        self.size = 0

    def findMin(self, root):
         
        if root == None:
            return None
    
    #Har ikke noden noen barn til venstre så er noden minst
        if root.left == None:
            return root
        
    #Ellers må vi finne den minste ved å gå flere steg til venstre
        if root.left != None:
            min = self.findMin(root.left)

        return min
    
    def height(self, node):
        if node == None:
            return -1
        return max(self.height(node.right), self.height(node.left))
    
    def left_rotate(self, z):
        y = z.right
        T = y.left

        y.left = z
        z.right = T

        z.height = 1 + max(self.height(z.left), self.height(z.right))
        y.height = 1 + max(self.height(y.left), self.height(y.right))

        return y

    def right_rotate(self, z):
        y = z.left
        T = y.right

        y.right = z
        z.left = T

        z.height = 1 + max(self.height(z.left), self.height(z.right))
        y.height = 1 + max(self.height(y.left), self.height(y.right))

        return y
    
    def balance_factor(self, v):

        if v == None:
            return 0
    
        return self.height(v.left) - self.height(v.right)
    

    def balance(self, v):

        if self.balance_factor(v) < -1:
            if self.balance_factor(v.right) > 0:
                v.right = self.right_rotate(v.right)
            return self.left_rotate(v)
        
        if self.balance_factor(v) > 1:
            if self.balance_factor(v.left) < 0:
                v.left = self.left_rotate(v.left)
            return self.right_rotate(v)
        
        return v
    
    def insert(self, element, node = 0):
        
        if node == 0:
            node = self.root

        if self.contains(element):
            return

        if node == None:
            node = Node(element)
            self.size += 1
            #if self.root == None:
             #   self.root = node
        elif element < node.element:
            node.left = self.insert(element, node.left)
        elif element > node.element:
            node.right = self.insert(element, node.right)

        node.height = 1 + max(self.height(node.left), self.height(node.right))
        
        #self.root = self.balance(node)
        holder = self.balance(node)
        self.root = holder
        return holder
    

    #def search(self, element, node = 0):

     #   if node == 0:
      #      node = self.root

       # if node == None:
        #    return None
    
        #if node.element == element:
        #    return node
    
        #if element < node.element:
        #    return self.search(element, node.left)
    
        #if element > node.element:
        #    return self.search(element, node.right)


    def contains(self, element, node = 0):

        if node == 0:
            node = self.root
            
       
        # while True:
        #     if node == None:
        #         break
        #     elif node.element == element:
        #         return True
        #     elif node.element < element:
        #         node = node.right
        #         continue
        #     elif node.element > element:
        #         node = node.left
        #         continue
        # return False

        if node == None:
            return False

        if node.element == element:
            return True

        if element < node.element:
            return self.contains(element, node.left)

        if element > node.element:
            return self.contains(element, node.right)
    
        if node.element == element:
            return True
        else:
            return False




    def remove(self, x, node = 0):

        if node == 0:
            node = self.root

        if node == None:
            return None
    
        if x < node.element:
            node.left = self.remove(x, node.left)
            self.root = node
            #self.size -= 1
            return self.balance(node)

        if x > node.element:
            node.right = self.remove(x, node.right)
            self.root = node
            #self.size -= 1
            return self.balance(node)
    
        if node.left == None:
            self.root = node.right
            self.size -= 1
            return self.balance(node)
    
        if node.right == None:
            self.root = node.left
            self.size -= 1
            return self.balance(node)
    
  
        u = self.findMin(node.right)
        node.element = u.element
        node.right = self.remove(u.element, node.right)


        node.height = 1 + max(self.height(node.left), self.height(node.right))
        #self.size -= 1
        holder = self.balance(node)
        self.root = holder
        return holder
    
    def size(self):
        return self.size

def draw_graph(v, dot):
    if v.left:
        dot.node(name=str(v.left) ,label=str(v.left.element))
        dot.edge(str(v), str(v.left))
        dot = draw_graph(v.left, dot=dot)
        
    if v.right:
        dot.node(name=str(v.right) ,label=str(v.right.element))
        dot.edge(str(v), str(v.right))
        dot = draw_graph(v.right, dot=dot)

    return dot

def main(filnavn):
    set1 = set()
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
                set1.insert(nmb)            

            elif command == "remove":
                set1.remove(nmb)

            elif command == "contains":
                if set1.contains(nmb):
                    print("true")
                else:
                    print("false")

            elif command == "size":
                print(set1.size)
            else:
                print("something wrong")

    draw_graph(set1.root, graphviz.Graph()).render("test", format="svg")

    

main("inputs/input_100")

