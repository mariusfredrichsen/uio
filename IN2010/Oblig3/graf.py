import graphviz

# Noder er skuespillere
# Skuespillere er en ordbok som holder står på navn, og filmer de har spilt i?
# Kantene er filmer
# Filmer er i en ordbok som holder styr på tittel og rating

def build_graph(film_name, actor_name):
    films = {} # id: (name, rating)
    actors = {}
    with open(film_name) as f:
        for line in f:
            line = line.strip().split("\t")
            films[line[0]] = (line[1], float(line[2]))
    with open(actor_name) as f:
        for line in f:
            line = line.strip().split("\t")
            actors[line[0]] = (line[1], [e for e in line[2:]])
    return films, actors

def draw_graph(G):
    dot = graphviz.Graph()

build_graph("marvel_movies.tsv", "marvel_actors.tsv") 