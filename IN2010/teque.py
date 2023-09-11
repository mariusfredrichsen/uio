class Node:
    def __init__(self, food):
        self.food = food
        self.prev = None
        self.next = None

class Teque:
    def __init__(self):
        self.head = None
        self.stomach = None
        self.tail = None
        self.teller = 0
    
    def push_front(self, food):
        self.teller += 1
        new_node = Node(food)
        if self.head == None:
            self.head = new_node
            self.stomach = new_node
            self.tail = new_node
        else:
            new_node.next = self.head
            self.head.prev = new_node
            self.head = new_node
            if self.teller % 2 == 0:
                self.stomach = self.stomach.prev
        
    def push_back(self, food):
        self.teller += 1
        new_node = Node(food)
        if self.head == None:
            self.head = new_node
            self.stomach = new_node
            self.tail = new_node
        else:
            self.tail.next = new_node
            new_node.prev = self.tail
            self.tail = new_node
            if self.teller % 2 == 1:
                self.stomach = self.stomach.next
    
    def push_middle(self, food):
        self.teller += 1
        new_node = Node(food)
        if self.head == None:
            self.head = new_node
            self.stomach = new_node
            self.tail = new_node
        elif self.stomach.next == None:
            self.teller -= 1
            self.push_back(food)
        else:
            new_node.next = self.stomach.next
            new_node.prev = self.stomach
            self.stomach.next.prev = new_node
            self.stomach.next = new_node
            if self.teller % 2 == 1:
                self.stomach = self.stomach.next
    
    def get(self, i):
        temp_node = self.head
        for i in range(i):
            temp_node = temp_node.next
        print(temp_node.food)

teque = Teque()

n = int(input())
for i in range(n):
    req, i = input().strip().split()
    i = int(i)
    if req == "push_front":
        teque.push_front(i)
    if req == "push_middle":
        teque.push_middle(i)
    if req == "push_back":
        teque.push_back(i)
    if req == "get":
        teque.get(i)