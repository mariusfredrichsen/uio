import graphviz
from collections import defaultdict
from time import time

def build_graph(p, a, m):
    V, E, w = set(), defaultdict(set), defaultdict(set)
    for m_a in p:
        for v in p[m_a]:
            for u in p[m_a]:
                if u != v:
                    E[v].add(u)
                    w[(v, u)].add((m_a, float(m[m_a][1])))
                    w[(u, v)].add((m_a, float(m[m_a][1])))
                    
    for v in a:
        V.add(v)
        
    return V, E, w

def draw_graph(G, a, m):
    _, E, w = G
    dot = graphviz.Graph()
    visited_edges = set()
    
    for v in E:
        for u in E[v]:
            if (v, u) not in visited_edges:
                visited_edges.add((u, v))
                dot.edge(a[v][0], a[u][0], label=str(len(w[(u, v)])))
            
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
    print("\n")
    for i in range(len(path)-1):
        for m in w[(path[i],path[i+1])]:
            break

        print(f"{actors[path[i]][0]} ===[ {movies[m[0]][0]} ({m[1]}) ]===>")
    print(actors[path[-1]][0])
    
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

def print_least_weighted_path(actors, movies, path):
    weight = 0
    print("\n")
    for i in range(len(path)-1):
        print(f"{actors[path[i][0]][0]} ===[ {movies[path[i][1][0]][0]} ({path[i][1][1]}) ]===>")
        weight += round(10 - path[i][1][1], 1)
    print(f"{actors[path[-1]][0]}")
    print(f"Total weight: {weight}")

def find_all_components(G):
    V, E, w = G
    components = []
    newV = V.copy()
    while newV:
        v = next(iter(newV))
        visited = dfs_visit_from(G, v)
        components.append(visited)
        for v in visited:
            newV.discard(v)
    return components

def count_components_length(components):
    components_n = defaultdict(int)
    for c in components:
        components_n[len(c)] += 1
    return components_n

def print_componenets_length(G):
    components = find_all_components(G)
    components_n = count_components_length(components)
    last_one = float('inf')
    print("\n")
    for _ in components_n:
        biggest = 0
        for n in components_n:
            if n < last_one and n > biggest:
                biggest = n
        last_one = biggest
        print(f"There are {components_n[biggest]} with {biggest} nodes")

def dfs_visit_from(G, s):
    V, E, w = G
    visited = set([s])
    stack = [s]
    result = []
    
    while stack:
        v = stack.pop()
        result.append(v)
        for u in E[v]:
            if u not in visited:
                visited.add(u)
                stack.append(u)

    return result
       
def main():
    lines_actors = dict()
    lines_movies = dict()
    with open('movies.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            lines_movies[line[0]] = (line[1], line[2])
    with open('actors.tsv', encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            line = [line[0], line[1], [e for e in line[2:] if e in lines_movies]]
            lines_actors[line[0]] = (line[1], line[2])
    

    movie_actors = defaultdict(set)
    for v in lines_actors:
        for m in lines_actors[v][1]:
            movie_actors[m].add(v)
    
    t1 = time()
    G = build_graph(movie_actors, lines_actors, lines_movies)
    print(f"Tid for bygging av graf: {time() - t1:.4}")
    
    V, E, w = G
    print(len(V))
    i = 0
    for v in w:
        i += len(w[v])
    print(i//2)
    # draw_graph(G, lines_actors, lines_movies)
    
    t1 = time()
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm2255973', 'nm0000460'))
    print(f"Tid for å finne kortest sti1: {time() - t1:.4}")
    t1 = time()
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0424060', 'nm8076281'))
    print(f"Tid for å finne kortest sti2: {time() - t1:.4}")
    t1 = time()
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm4689420', 'nm0000365'))
    print(f"Tid for å finne kortest sti3: {time() - t1:.4}")
    t1 = time()
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0000288', 'nm2143282'))
    print(f"Tid for å finne kortest sti4: {time() - t1:.4}")
    t1 = time()
    print_path(G, lines_actors, lines_movies, shortest_path_from_to(G, 'nm0637259', 'nm0931324'))
    print(f"Tid for å finne kortest sti5: {time() - t1:.4}")
    
    t1 = time()
    print_least_weighted_path(lines_actors, lines_movies, least_effort_path_from_to(G, 'nm2255973', 'nm0000460'))
    print(f"Tid for å finne minst vekta sti1: {time() - t1:.4}")
    t1 = time()
    print_least_weighted_path(lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0424060', 'nm8076281'))
    print(f"Tid for å finne minst vekta sti2: {time() - t1:.4}")
    t1 = time()
    print_least_weighted_path(lines_actors, lines_movies, least_effort_path_from_to(G, 'nm4689420', 'nm0000365'))
    print(f"Tid for å finne minst vekta sti3: {time() - t1:.4}")
    t1 = time()
    print_least_weighted_path(lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0000288', 'nm2143282'))
    print(f"Tid for å finne minst vekta sti4: {time() - t1:.4}")
    t1 = time()
    print_least_weighted_path(lines_actors, lines_movies, least_effort_path_from_to(G, 'nm0637259', 'nm0931324'))
    print(f"Tid for å finne minst vekta sti5: {time() - t1:.4}")
    
    t1 = time()
    print_componenets_length(G)
    print(f"Tid for å finne alle komponenter: {time() - t1:.4}")
    
main()