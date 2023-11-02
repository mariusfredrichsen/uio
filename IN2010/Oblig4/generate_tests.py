import random as rnd

nodes = list(range(1, 100))
rnd.shuffle(nodes)

root = nodes.pop()
tree = {root: []}
nodes = nodes[:rnd.randint(0, 100)]
kitten = rnd.choice(nodes)

for node in nodes:
    parent = rnd.choice(list(tree.keys()))
    tree[parent].append(node)
    if node not in tree:
        tree[node] = []

print(kitten)
for node in tree:
    if len(tree[node]) > 0:
        print(node, ' '.join([str(x) for x in tree[node]]))
print(-1)
