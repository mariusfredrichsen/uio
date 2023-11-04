from collections import defaultdict

def parents_in_tree(G):
    A, E = G
    parents = dict()
    for v in E:
        for u in E[v]:
            parents[u] = v
    
    for v in A:
        if v not in parents:
            parents[v] = None
    
    return parents

def main():
    A, E = set(), defaultdict(set)
    G = A, E
    
    inn = input().strip()
    kitten = inn
    while inn != '-1':
        inn = inn.split()
        for i in range(len(inn)):
            A.add(inn[i])
            if i == 0:
                continue
            E[inn[0]].add(inn[i])
        inn = input().strip()
    
    parents = parents_in_tree(G)
    path = [kitten]
    while parents[kitten]:
        path.append(parents[kitten])
        kitten = parents[kitten]
    
    for p in path:
        print(p, end=" ")
    
main()