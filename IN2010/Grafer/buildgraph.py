import os
os.environ["PATH"] += os.pathsep + 'C:/Users/mariu/Downloads/Graphviz/bin'
import graphviz

def get_input():
    lines = []
    line = input().strip()
    while line != "":
        lines.append(line)
        line = input().strip()
    return lines

from collections import defaultdict

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

    dot.render('graph', format='svg')

drawgraph(buildgraph(get_input()))