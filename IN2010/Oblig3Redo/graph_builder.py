import graphviz
from collections import defaultdict



def build_graph(lines_actors, lines_movies):
    V, E, w = set(), defaultdict(set), defaultdict(set) # w tar formen (v, u, f) = r der hvor v og u er skuespillere og f er en filmid og r er vurderingen
    for v in lines_actors:
        V.add(v)
        print(lines_actors[v])
        for u in lines_actors:
            if v != u:
                for m in lines_actors[v][1]:
                    if m in lines_actors[u][1]:
                        E[v].add(u)
                        if m not in lines_movies:
                            continue
                        w[(v, u)].add((m, float(lines_movies[m][1])))
                        w[(u, v)].add((m, float(lines_movies[m][1])))
    
    return V, E, w

def draw_graph(G):
    _, E, w = G
    dot = graphviz.Graph()
    visited_edges = set()
    
    for v in E:
        for u in E[v]:
            if (v, u) not in visited_edges:
                visited_edges.add((u, v))
                dot.edge(v, u, label=str(len(w[(u, v)])))
            
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

def shortest_path_from_to(G, v, u):
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
        for m in w[(path[i],path[i+1])]:
            break

        print(f"{actors[path[i]][0]} ===[ {movies[m[0]][0]} ({m[1]}) ]===>")
    print(actors[path[-1]][0] + "\n\n")
    
def least_effort_paths_from(G, v):
    V, E, w = G
    queue = [(0, v)]
    dist = defaultdict(lambda: float('inf'))
    dist[v] = 0
    parents = {v: None}
    
    while queue:
        r, v = queue.pop(0)
        if r != dist[v]:
            continue
        for u in E[v]:
            m = find_best_film(w[(v, u)])
            tmp_r = r + 10 - m[1]
            if tmp_r < dist[u]:
                dist[u] = tmp_r
                queue.append((tmp_r, u))
                parents[u] = (v, m)
            
    return parents

def least_effort_path_from_to(G, v, u):
    parents = least_effort_paths_from(G, v)
    path = [u]

    while parents[u]:
        path.insert(0,parents[u])
        u = parents[u][0]
    
    return path

def find_best_film(movies):
    best_rated = 0
    best_movie = ()
    for m in movies:
        if m[1] > best_rated:
            best_rated = m[1]
            best_movie = m
    return best_movie

def print_least_weighted_path(G, actors, movies, path):
    V, E, w = G
    for i in range(len(path)-1):
        print(f"{actors[path[i][0]][0]} ===[ {movies[path[i][1][0]][0]} ({path[i][1][1]}) ]===>")
    print(f"{actors[path[-1]][0]}")
    
def main():
    lines_actors = dict()
    lines_movies = dict()
    with open('actors.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            line = [line[0], line[1], [e for e in line[2:]]]
            lines_actors[line[0]] = (line[1], line[2])
    with open('movies.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            lines_movies[line[0]] = (line[1], line[2])

    G = build_graph(lines_actors, lines_movies)
    # draw_graph(G)
    
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm2255973', 'nm0000460'))
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0424060', 'nm8076281'))
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm4689420', 'nm0000365'))
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0000288', 'nm2143282'))
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0637259', 'nm0931324'))
    
    print_least_weighted_path(G, lines_actors, lines_movies, least_effort_path_from_to(G, 'nm2255973', 'nm0000460'))
    print_least_weighted_path(G, lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0424060', 'nm8076281'))
    print_least_weighted_path(G, lines_actors, lines_movies, least_effort_path_from_to(G, 'nm4689420', 'nm0000365'))
    print_least_weighted_path(G, lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0000288', 'nm2143282'))
    print_least_weighted_path(G, lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0637259', 'nm0931324'))
    
        
main()