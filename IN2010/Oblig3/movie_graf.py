import graphviz

# Noder er skuespillere
# Skuespillere er en ordbok som holder står på navn, og filmer de har spilt i?
# Kantene er filmer
# Filmer er i en ordbok som holder styr på tittel og rating

def build_graph(film_name, actor_name):
    A = {} # skuespillere
    F = {} # filmer
    R = {} # ratings
    with open(actor_name, encoding='utf8') as f:
        for line in f:
            line = line.strip().split("\t")
            A[line[0]] = (line[1], [e for e in line[2:]])
    with open(film_name, encoding='utf8') as f:
        print(len(A))
        print(len(F))
        for line in f:
            line = line.strip().split("\t")
            actors = set()
            
            for a in A:
                if line[0] in A[a][1]:
                    actors.add(a)
            R[line[0]] = (line[1], float(line[2]), actors)
            
    for v in A:
        for m in A[v][1]:
            if m in R:
                for u in R[m][2]:
                    if u != v:
                        F[(A[v][0], A[u][0])] = R[m][0]
                        F[(A[u][0], A[v][0])] = R[m][0]
    print(len(A))
    print(len(R))
    print(len(F))
    return A, F, R

def draw_graph(G):
    A, F, R = G
    dot = graphviz.Graph()
    
    for v in A:
        dot.node(A[v][0])

    
    dot.render('movie_graph', format='svg')



def main():
    A, F, R = build_graph("movies.tsv", "actors.tsv")
    print(len(A))
    print(len(F))
    print(len(R))
    print("antall noder: ", len(A), "\nantall kanter: ", len(F)//2)
    
    
    
    
main()