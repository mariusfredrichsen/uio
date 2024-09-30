from collections import defaultdict
import graphviz

lines = [
    "A B 13",
    "A C 6",
    "B C 7",
    "B D 1",
    "C D 14",
    "C E 8",
    "C H 20",
    "D E 9",
    "D F 3",
    "E F 2",
    "E J 18",
    "G H 15",
    "G I 5",
    "G J 19",
    "G K 10",
    "H J 17",
    "I K 11",
    "J K 16",
    "J L 4",
    "K L 12",
]


def buildgraph(lines):
    V = set()
    E = defaultdict(set)
    w = dict()

    for line in lines:
        u, v, weight = line.strip().split()

        V.add(u)
        V.add(v)

        E[u].add(v)
        E[v].add(u)

        w[(u, v)] = int(weight)
        w[(v, u)] = int(weight)

    return V, E, w

def draw_graph(G):
    V, E, w = G
    dot = graphviz.Graph()
    seen_edges = set()

    for u in V:
        dot.node(u)

        for v in E[u]:
            if (v, u) in seen_edges:
                continue
            seen_edges.add((u, v))
            dot.edge(u, v, label=str(w[(u, v)]))

    dot.render("graph", format="svg")
    
def draw_parents_graph(G, parents, name):
    V, E, w = G
    dot = graphviz.Graph()
    for v in parents:
        u = parents[v]
        if u:
            dot.edge(u, v, label=str(w[(v, u)]))
    dot.render(name, format="svg")

class Primtrip():
    def __init__(self, value, node, parent):
        self.value = value
        self.node = node
        self.parent = parent
        self.q = (value, node, parent)
    
    def __gt__(self, primtrim):
        return self.value > primtrim.value

    def __eq__(self, primtrim):
        return self.value == primtrim.value
    
    def __iter__(self):
        yield self.value
        yield self.node
        yield self.parent

def prim(G):
    V, E, w = G
    s = next(iter(V))
    queue = [Primtrip(0, s, None)]
    parents = dict()
    
    while queue:
        _, v, p = queue.pop(0)
        if v not in parents:
            parents[v] = p
            for u in E[v]:
                queue.append(Primtrip(w[(v,u)], u, v))
                queue.sort()
    return parents


def main():
    G = buildgraph(lines)
    parents = prim(G)
    draw_graph(G)
    draw_parents_graph(G, parents, "Spenntree_prim")





main()