from collections import defaultdict
from heapq import heappush, heappop
import graphviz







def build_graph(lines):
    V, E, w = set(), defaultdict(set), dict()
    for line in lines:
        line = line.strip().split()
        V.add(line[0])
        V.add(line[1])
        
        E[line[0]].add(line[1])
        E[line[1]].add(line[0])
        w[(line[0], line[1])] = int(line[2])
        w[(line[1], line[0])] = int(line[2])
    
    return V, E, w

def dijkstras(G, s):
    _, E, w = G
    dist = defaultdict(lambda: float('inf'))
    dist[s] = 0
    queue = [(0, s)]
    parents = {s: None}
    
    while queue:
        cost, v = heappop(queue)
        if cost != dist[v]:
            continue
        for u in E[v]:
            c = cost + w[(v,u)]
            if dist[u] > c:
                dist[u] = c
                heappush(queue, (c, u))
                parents[u] = v
    return parents

def draw_dist_graph(G, parents, name):
    V, _, w = G
    dot = graphviz.Graph()
    for v in parents:
        u = parents[v]
        if u:
            dot.edge(v, u, label=str(w[(v, u)]))
    dot.render(name, format='svg')

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
    parents = dijkstras(G, "A")
    draw_dist_graph(G, parents, "dijkstras")

main()
    
    
    