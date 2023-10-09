import graphviz
from collections import defaultdict

# Noder er skuespillere
# Skuespillere er en ordbok som holder står på navn, og filmer de har spilt i?
# Kantene er filmer
# Filmer er i en ordbok som holder styr på tittel og rating

def build_graph(film_name, actor_name):
    A = {} # skuespillere
    F = {} # filmer
    with open(film_name, encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")[:3]
            F[line[0]] = (line[1], float(line[2]))
            
    with open(actor_name, encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            A[line[0]] = (line[1], [e for e in line[2:]])

    E = dict()
    for v in A:
        E[v] = defaultdict(list)
    for v in A:
        for i in range(len(A[v][1])): # går igjennom filmer de er med i
            film = A[v][1][i]
            for u in A:
                if film in A[u][1] and u != v: # sjekker om skuespiller u er med og at u ikke er seg selv
                    E[v][u].append(film)            
                    
    return A, F, E

def draw_graph(G):
    A, F, E = G
    dot = graphviz.Graph()
    visited_edges = set()
    
    for v in A:
        dot.node(A[v][0])

    for v in E:
        for u in E[v]:
            for f in E[v][u]:
                if (v, u, f) not in visited_edges:
                    visited_edges.add((u, v, f))
                    dot.edge(A[v][0], A[u][0], label=F[f][0] + " " + str(F[f][1]))

    dot.render('movie_graph', format='svg')

def shortest_paths_from(G, s):
    A, F, E = G
    parents = {}
    parents[s] = None
    queue = [s]
    
    while queue:
        v = queue.pop(0)
        for u in E[v]:
            if u not in parents:
                parents[u] = (v, E[v][u])
                queue.append(u)

    return parents

def shortest_path_from_to(G, s, e):
    A, F, E = G
    parents = shortest_paths_from(G, s)
    path = [A[e][0]]
    v = e
    
    # draw_parent_tree(G, parents)

    while parents[v]:
        path.insert(0, (A[parents[v][0]][0], F[parents[v][1][0]]))
        v = parents[v][0]
    return path

def draw_parent_tree(G, parents):
    A, F, E = G
    dot = graphviz.Graph()
    
    for v in parents:
        if not parents[v]:
            continue
        u = parents[v][0]
        if u:
            dot.edge(A[v][0], A[u][0], label=F[parents[v][1][0]][0])
    
    dot.render('movie_parent_graph', format='svg')
    
def print_shortest_path(shortest_path):
    # eksempel format [('Anthony Mackie', 'tt4154756'), ('Mark Ruffalo', 'tt3501632'), 'Anthony Hopkins']
    print(f"=====  Fra {shortest_path[0][0]} til {shortest_path[-1]}  =====\n")
    for e in range(len(shortest_path)):
        if e == 0:
            print(f"{shortest_path[e][0]}")
        elif e == len(shortest_path)-1:
            print(f"===[ {shortest_path[e-1][1]} ] ===> {shortest_path[e]}\n\n\n")
        else:
            print(f"===[ {shortest_path[e-1][1]} ] ===> {shortest_path[e][0]}")

def main():
    A, F, E = G = build_graph("movies.tsv", "actors.tsv")
    print_shortest_path(shortest_path_from_to(G, 'nm2255973', 'nm0000460'))
    print_shortest_path(shortest_path_from_to(G, 'nm0424060', 'nm8076281'))
    print_shortest_path(shortest_path_from_to(G, 'nm4689420', 'nm0000365'))
    print_shortest_path(shortest_path_from_to(G, 'nm0000288', 'nm2143282'))
    print_shortest_path(shortest_path_from_to(G, 'nm0637259', 'nm0931324'))
    
    n_E = 0
    for v in E:
        for u in E[v]:
            n_E += len(E[v][u])
    # draw_graph(G)
    print("antall noder: ", len(A), "\nantall kanter: ", n_E//2)
    
main()