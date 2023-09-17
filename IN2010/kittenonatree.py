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
    def __init__(self, b):
        self.branch = b
        self.branches = []
    
    def __str__(self):
        return self.branch

rot = Node(root)

def make_branch(v, x):
    if x in branches:
        v.branches.append(Node(x))

def find_branch(v, x):
    for i in v.branches:
        asd = find_branch(i, x)

def ting(v):
    if v == None:
        return None
    for i in branches[v.branch]:
        make_branch(v, i)
    for b in v.branches:
        ting(b)

ting(rot)