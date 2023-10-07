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

    dot.render('marvel_graph', format='svg')



def main():
    A, F, E = G = build_graph("marvel_movies.tsv", "marvel_actors.tsv")
    n_E = 0
    for v in E:
        for u in E[v]:
            n_E += len(E[v][u])
    draw_graph(G)
    print("antall noder: ", len(A), "\nantall kanter: ", n_E//2)
    
    
    
    
main()