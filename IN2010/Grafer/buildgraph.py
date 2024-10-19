import os

os.environ["PATH"] += os.pathsep + "C:/Users/mariu/Downloads/Graphviz/bin"
import graphviz
from collections import defaultdict
import random


def get_input():
    lines = []
    line = input().strip()
    while line != "":
        lines.append(line)
        line = input().strip()
    return lines


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


def drawgraph(G):
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


G = buildgraph(lines)
drawgraph(G)


def depth_first_search_rec(G, s, visited: set, result: list) -> list:
    V, E, w = G

    visited.add(s)
    result.append(s)
    for v in E[s]:
        if v not in visited:
            depth_first_search_rec(G, v, visited, result)
    return result


def depth_first_search(G, s):
    V, E, w = G
    visited = set()
    stack = [s]
    result = []

    while stack:
        v = stack.pop()
        if v not in visited:
            result.append(v)
            visited.add(v)
            for u in E[v]:
                stack.append(u)
    return result


def breath_first_search_rec(G, s, visited: set, result: list, queue: list) -> list:
    V, E, w = G
    visited.add(s)
    result.append(s)
    for v in E[s]:
        if v not in visited:
            queue.append(v)
            visited.add(v)

    if queue:
        return breath_first_search_rec(G, queue.pop(0), visited, result, queue)
    return result


def braeth_first_search(G, s):
    V, E, w = G
    visited = set([s])
    queue = [s]
    result = []

    while queue:
        v = queue.pop(0)
        result.append(v)
        for u in E[v]:
            if u not in visited:
                visited.add(u)
                queue.append(u)
    return result


def bfs_shortest_path(G, s):
    V, E, w = G
    queue = [s]
    parents = {s: None}

    while queue:
        v = queue.pop(0)
        for u in E[v]:
            if u not in parents:
                parents[u] = v
                queue.append(u)
    return parents


def bfs_shortest_path_between(G, s, t):
    parents = bfs_shortest_path(G, s)
    path = []

    if t not in parents:
        return path

    while t:
        path.append(t)
        t = parents[t]

    return path[::-1]


def bfs_shortest_path_all(G, s):
    V, E, w = G
    parents = bfs_shortest_path(G, s)
    paths = []

    for v in V:
        path = []
        if v in parents:
            path = bfs_shortest_path_between(G, s, v)
        paths.append(path)

    return paths


def draw_parents(parents):
    dot = graphviz.Graph()
    for v in parents:
        u = parents[v]
        if u:
            dot.edge(u, v)
    dot.render("parenting_tree", format="svg")


from collections import defaultdict


def dijkstra(G, s):
    V, E, w = G
    queue = [(0, s)]
    dist = defaultdict(lambda: float("inf"))
    dist[s] = 0

    while queue:
        cost, v = queue.pop(0)
        if cost != dist[v]:
            continue
        for u in E[v]:
            tmp_cost = cost + w[(v, u)]
            if tmp_cost < dist[u]:
                dist[u] = tmp_cost
                queue.append((tmp_cost, u))

    return dist


def weighted_shortest_path_from(G, s):
    V, E, w = G
    queue = [(0, s)]
    dist = defaultdict(lambda: float("inf"))
    dist[s] = 0
    parents = {s: None}

    while queue:
        cost, v = queue.pop(0)
        if cost != dist[v]:
            continue
        for u in E[v]:
            tmp_cost = cost + w[(v, u)]
            if tmp_cost < dist[u]:
                dist[u] = tmp_cost
                queue.append((tmp_cost, u))
                parents[u] = v

    return parents

def draw_parents_graph(G, parents, name):
    V, E, w = G
    dot = graphviz.Graph()
    for v in parents:
        u = parents[v]
        if u:
            dot.edge(u, v, label=str(w[(v, u)]))
    dot.render(name, format="svg")

def prim(G):
    V, E, w = G
    s = random.choice(list(V))
    queue = [(0, s, None)]
    parents = {}

    while queue:
        c, v, p = queue.pop(0)
        if v in parents:
            continue
        parents[v] = p
        for u in E[v]:
            queue.append((w[(v, u)], u, v))

    return parents

def removenode(G, v):
    V, E, w = G
    
    newV = V.copy()
    newE = E.copy()
    
    newV.discard(v)
    del newE[v]
    
    for u in newV:
        neighbours = newE[u].copy()
        neighbours.discard(v)
        newE[u] = neighbours

    return newV, newE, w

def is_biconnected(G):
    V, E, w = G

    for v in V:
        newV, newE, newW = newG = removenode(G, v)
        nodelist = depth_first_search(newG, next(iter(newV)))

        if set(nodelist) != newV:
            return False
    return True


G = buildgraph(lines)
print(isbiconnected_naive(removenode(G, 'C')))


# Select Flight, Airline, To, Time, Information
# FROM arrivals
# WHERE 


# draw_parents_graph(G, prim(G), "Spenntree")


# print(sorted(dijkstra(G, 'A').items()))
# print(weighted_shortest_path_from(G, 'A'))
# draw_parents_graph(G, weighted_shortest_path_from(G, 'A'))

# print(breath_first_search_rec(G, 'A', set(), [], []))
# print(braeth_first_search(G, 'A'))
# print(bfs_shortest_path_between(G, 'A', 'G'))
# print(bfs_shortest_path_all(G, 'A'))

# print(depth_first_search(G, 'A'))
# print(depth_first_search_rec(G, 'A', set(), []))
