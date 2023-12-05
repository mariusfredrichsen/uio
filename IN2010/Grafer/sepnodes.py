from collections import defaultdict

def build_graph(edges):
    V, E = set(), defaultdict(set)
    
    for edge in edges:
        v, u, w = edge.strip().split()
        
        V.add(v)
        V.add(u)
        
        E[v].add(u)
        E[u].add(v)

    return V, E

def seperationnodes_rec(E, v, d, depth, low, parents, seps):
    depth[v] = low[v] = d
    for u in E[v]:
        if u in parents and parents[u] == v:
            continue
        if u in depth:
            low[v] = min(low[v], depth[u])
            continue
        
        parents[u] = v
        seperationnodes_rec(E, u, d + 1, depth, low, parents, seps)
        low[v] = min(low[u], low[v])
        if d <= low[u]:
            seps.add(v)

def seperationnodes(G):
    V, E = G
    s = next(iter(V))
    depth = {s: 0}
    low = {s: 0}
    parents = {s: None}
    seps = set()
    
    for v in E[s]:
        if v not in depth:
            seperationnodes_rec(E, v, 1, depth, low, parents, seps)
    
    if len([u for u in E[s] if depth[u] == 1]) > 1:
        seps.add(s)
    
    return seps

def isbiconnective(G):
    return len(seperationnodes(G)) == 0

def main():
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
    
    G = build_graph(lines)
    print(isbiconnective(G))
    print(seperationnodes(G))
    
    
main()