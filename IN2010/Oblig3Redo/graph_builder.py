import graphviz
from collections import defaultdict



def build_graph(lines_actors, lines_movies):
    V, E, w = set(), defaultdict(set), dict() # w tar formen (v, u, f) = r der hvor v og u er skuespillere og f er en filmid og r er vurderingen
    for v in lines_actors:
        V.add(v)
        for u in lines_actors:
            if v != u:
                for m in lines_actors[v][1]:
                    if m in lines_actors[u][1]:
                        E[v].add(u)
                        w[(v, u)] = (m, lines_movies[m][1])
                        w[(u, v)] = (m, lines_movies[m][1])
    return V, E, w

def draw_graph(G):
    _, E, w = G
    dot = graphviz.Graph()
    visited_edges = set()
    
    for v in E:
        for u in E[v]:
            if (v, u) not in visited_edges:
                visited_edges.add((u, v))
                dot.edge(v, u, label=w[(u, v)][0] + " " + w[(u, v)][1])
            
    dot.render('marvel_graph', format='svg')
        
def shortest_paths_from(G, v):
    _, E, _ = G
    parents = {v: None}
    queue = [v]
    
    while queue:
        v = queue.pop(0)
        for u in E[v]:
            if u not in parents:
                parents[u] = v
                queue.append(u)
    
    return parents

def shortest_paths_from_to(G, v, u):
    parents = shortest_paths_from(G, v)
    path = [u]
    
    while parents[u]:
        path.insert(0,parents[u])
        u = parents[u]
    
    return path

def print_path(G, actors, movies, path):
    _, _, w = G
    print("\n\n")
    for i in range(len(path)-1):
        m, r = w[(path[i],path[i+1])]
        print(f"{actors[path[i]][0]} ===[ {movies[m][0]} ({r}) ]===>")
    print(actors[path[-1]][0] + "\n\n")




def main():
    lines_actors = dict()
    lines_movies = dict()
    with open('marvel_actors.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            line = [line[0], line[1], [e for e in line[2:]]]
            lines_actors[line[0]] = (line[1], line[2])
    with open('marvel_movies.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            lines_movies[line[0]] = (line[1], line[2])

    G = build_graph(lines_actors, lines_movies)
    draw_graph(G)
    
    
    
    
    path = shortest_paths_from_to(G, 'nm0000949', 'nm0000313')
    print_path(G, lines_actors, lines_movies, path)
    
        
main()