from collections import defaultdict
import graphviz

def draw_graph(G, name):
    V, E = G
    dot = graphviz.Digraph()
    for v in V:
        dot.node(v)
        if v in E:
            for u in E[v]:
                dot.edge(v, u)
    dot.render(name, format="svg")

def main():
    E = defaultdict(set)
    V = set()
    with open("emner.txt", 'r') as f:
        for line in f:
            line = line.strip().split()
            line = [line[0], [e for e in line[1:]]]
            for e in line[1]:
                E[line[0]].add(e)
                V.add(line[0])
                V.add(e)
    G = V, E
    
    draw_graph(G, "topologisk_tree")



main()