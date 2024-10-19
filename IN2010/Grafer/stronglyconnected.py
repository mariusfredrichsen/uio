from collections import defaultdict


def build_graph(edges):
    V, E = set(), defaultdict(set)
    for edge in edges:
        v, u = edge.split()
        
        V.add(v)
        V.add(u)

        E[v].add(u)
    
    return V, E


<<<<<<< HEAD
def dfsvisit(G, v, visited, stack):
    _, E = G
    visited.add(v)
    
    for u in E[v]:
        if u not in visited:
            dfsvisit(G, u, visited, stack)
    stack.append(v)
=======
def dfsvisit(G, u, visited, stack):
    V, E = G
    visited.add(u)
    for v in E[u]:
        if v not in visited:
            dfsvisit(G, v, visited, stack)
    stack.append(u)
>>>>>>> b5a5cf6f325df43b4280df4b19ab37661718ae69

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
<<<<<<< HEAD
    reE = defaultdict(set)
    for v in V:
        for u in E[v]:
            reE[u].add(v)
    return V, reE
        
=======
    rE = defaultdict(set)

    for u in V:
        for v in E[u]:
            rE[v].add(u)
    return V, rE
>>>>>>> b5a5cf6f325df43b4280df4b19ab37661718ae69


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