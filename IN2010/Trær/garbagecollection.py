
def Free(v):
    # fra oppgaven
    pass

def DFSVisit(G, s, visited):
    V, E = G
    stack = [s]
    
    while stack:
        v = stack.pop()
        if v not in visited:
            visited.add(v)
            for u in E[v]:
                stack.append(u)
    

def garbagecollection(G, R):
    V, _ = G
    
    visited = set()
    
    for v in R:
        if v not in visited:
            DFSVisit(G, v, visited)
    
    for v in V:
        if v not in visited:
            Free(v)
    
    