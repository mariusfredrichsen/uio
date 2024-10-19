from collections import defaultdict


def build_graph(edges):
    V, E = set(), defaultdict(set)
    for edge in edges:
        v, u = edge.split()
        
        V.add(v)
        V.add(u)

        E[v].add(u)
    
    return V, E


def dfsvisit(G, v, visited, stack):
    _, E = G
    visited.add(v)
    
    for u in E[v]:
        if u not in visited:
            dfsvisit(G, u, visited, stack)
    stack.append(v)

def dfstopsort(G):
    V, E = G
    visited = set()
    stack = []
    for v in V:
        if v not in visited:
            dfsvisit(G, v, visited, stack)
    return stack

def reverse_graph(G):
    V, E = G
    reE = defaultdict(set)
    for v in V:
        for u in E[v]:
            reE[u].add(v)
    return V, reE
        


def strongly_connected_components(G):
    V, E = G
    
    stack = dfstopsort(G)
    
    Gr = reverse_graph(G)
    visited = set()
    components = []
    
    while stack:
        v = stack.pop()
        if v not in visited:
            component = []
            dfsvisit(Gr, v, visited, component)
            components.append(component)

    return components

def main():
    edges = [
        "A B",
        "B C",
        "B E",
        "B F",
        "C D",
        "C G",
        "D C",
        "D H",
        "E A",
        "E F",
        "F G",
        "G F",
        "H D",
        "H G"
    ]

    G = build_graph(edges)
    
    print(strongly_connected_components(G))




main()