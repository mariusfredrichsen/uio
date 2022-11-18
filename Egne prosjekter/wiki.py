import requests
from bs4 import BeautifulSoup

class Url_Link:
    def __init__(self, url, parent):
        self._url = url
        self._parent = parent
    
    def hent_parent(self):
        return self._parent
    
    def hent_url(self):
        return self._url

url = "https://no.wikipedia.org/wiki/Tobias"
ende_url = "https://no.wikipedia.org/wiki/Odin"
start_url = "https://no.wikipedia.org"

def hent_nyttige_linker(url):
    parsed = BeautifulSoup(requests.get(url).content, "html.parser")
    linker = parsed.find_all("a", href=True)

    nyttig_linker = []

    for link in linker:
        if link["href"].startswith("/wiki/"):
            nyttig_linker.append(link["href"])

    return nyttig_linker

sjekket = [Url_Link(url[24:], None)]
start_liste = []
for link in hent_nyttige_linker(url):
    start_liste.append(Url_Link(link, sjekket[0]))
print(start_liste)

yeet1 = True
while yeet1:
    sjekket.append(start_liste[0])
    for link in hent_nyttige_linker(start_url + start_liste[0].hent_url()):
        start_liste.append(Url_Link(link,start_liste[0]))
    for link in start_liste:
        if ende_url[24:] == link.hent_url():
            sjekket.append(Url_Link(ende_url[24:],start_liste[0]))
            yeet1 = False
            break
    start_liste.pop(0)

print("Funnet nettside")
slutt_liste = [sjekket[-1]]
while True:
    if slutt_liste[-1].hent_parent() != None:
        slutt_liste.append(slutt_liste[-1].hent_parent())
    else:
        break

slutt_slutt_liste = []

for elem in slutt_liste:
    slutt_slutt_liste.append(elem.hent_url())

print(slutt_slutt_liste)

# kanskje prøve denne biten med klasser