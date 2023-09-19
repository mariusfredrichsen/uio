kitten = input().strip()
branches = {}

root = input().strip().split(" ")
branches[root[0]] = root[1:]
root = root[0]
while True:
    stdin = input().strip()
    if stdin == "-1":
        break
    
    stdin = stdin.split(" ")
    branches[stdin[0]] = stdin[1:]

class Node:
    def __init__(self, data):
        self.data = data
        self.branches = []
    
    def __str__(self):
        return self.data

def insert(v, x):
    if v == None:
        v = Node(x)
    elif v.branches != [] and v.data in branches:
        for b in v.branches:
            insert(b, 0)
    elif v.data in branches:
        for b in branches[v.data]:
            v.branches.append(Node(b))
    return v

def print_branches(branches):
    for b in branches:
        print(str(b))
    print()

def find_path(v, kitten, path):
    if v.data == kitten:
        print(path + str(rot))
    for b in v.branches:
        find_path(b, kitten, str(b) + " " + path)
            

rot = None
rot = insert(rot, root)
for i in range(1, len(branches)):
    rot = insert(rot, 0)

find_path(rot, kitten, "")