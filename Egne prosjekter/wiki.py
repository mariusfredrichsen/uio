import requests
from bs4 import BeautifulSoup

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

start_liste = hent_nyttige_linker(url)
print(start_liste)
sjekket = []
funnet_funn = True
teller1 = 0
while funnet_funn:
    for url in start_liste:
        teller1 += 1
        teller = len(start_liste)
        sjekket.append(sjekket)
        if url != 0:
            for url1 in hent_nyttige_linker(start_url + url):
                teller += 1
                if url1 not in sjekket:
                    start_liste.append(url1)
            if ende_url[24:] in start_liste:
                funnet_funn = False
                break
print("Funnet", teller//teller1)



