from collections import defaultdict

def build_graph(edges):
    V, E = set(), defaultdict(set)
    
    for edge in edges:
        v, u = edge.strip().split()
        
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
            low[v] = min(depth[u], low[v])
            continue
        parents[u] = v
        
        seperationnodes_rec(E, u, d + 1, depth, low, parents, seps)
        low[v] = min(low[u], low[v])
        
        if low[u] >= d:
            seps.add(v)
    
    
def seperationnodes(G):
    V, E = G
    s = next(iter(V))
    depth = {s : 0}
    low = {s : 0}
    parents = {s : None}
    seps = set()
    
    for v in E[s]:
        if v not in depth:
            seperationnodes_rec(E, v, 1, depth, low, parents, seps)
            
    if len([v for v in E[s] if depth[v] == 1]) > 1:
        seps.add(s)
    
    return seps




def main():
    lines = [
        "A B",
        "A F",
        "B A",
        "B C",
        "C B",
        "C F",
        "C D",
        "C E",
        "D C",
        "D E",
        "E D",
        "E C",
        "F A",
        "F C"
    ]
    
    G = build_graph(lines)
    print(seperationnodes(G))

main()