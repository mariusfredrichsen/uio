from collections import defaultdict


def build_graph(edges):
    V, E = set(), defaultdict(set)
    for edge in edges:
        v, u = edge.split()
        
        V.add(v)
        V.add(u)

        E[v].add(u)
    
    return V, E


def dfsvisit(G, u, visited, stack):
    V, E = G
    visited.add(u)
    for v in E[u]:
        if v not in visited:
            dfsvisit(G, v, visited, stack)
    stack.append(u)

def dfstopsort(G):
    V, E = G
    visited = set()
    stack = []
    for u in V:
        if u not in visited:
            dfsvisit(G, u, visited, stack)
    return stack

def reversegraph(G):
    V, E = G
    rE = defaultdict(set)

    for u in V:
        for v in E[u]:
            rE[v].add(u)
    return V, rE


def stronglyconnectedcomponents(G):
    V, E = G

    stack = dfstopsort(G)

    Gr = reversegraph(G)
    visited = set()
    components = []
    while stack:
        u = stack.pop()
        if u not in visited:
            component = []
            dfsvisit(Gr, u, visited, component)
            components.append(component)

    return components

def main():
    edges = [
        "A B",
        "B A",
        "A E",
        "B E",
        "D E",
        "D C",
        "C D",
        "B C"
    ]

    G = build_graph(edges)
    
    print(stronglyconnectedcomponents(G))




main()