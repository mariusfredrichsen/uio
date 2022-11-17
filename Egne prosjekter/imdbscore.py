from bs4 import BeautifulSoup
import requests
import json

url = "https://contribute.imdb.com/czone/hall_of_fame#alltime2021"

parsed = BeautifulSoup(requests.get(url).content, "html.parser")
div = parsed.find(id = "alltime2021")
parent = div.parent
table = parent.find("table")
td = table.find_all("td")

index = 0
liste = []

for linje in td:
    if index%3 == 0:
        ordbok = {}
    if linje.text == "RANK" or linje.text == "TOTAL" or linje.text == "NICKNAME":
        pass
    else:
        if index%3 == 0:
            ordbok["rank"] = linje.text
        if index%3 == 1:
            ordbok["score"] = int(linje.text.replace(",", "")) # bruk .replace(",", "") for å fjerne komma
        if index%3 == 2:
            ordbok["username"] = linje.text
            ordbok["id"] = linje.find("a")["href"][-10:-1]
    if index%3 == 0:
        liste.append(ordbok)        
    index += 1
liste.pop(0)

with open('leaderboard.json', 'w', encoding='utf-8') as file:
  file.write(json.dumps(liste))

for dict in liste:
    for elem in dict:
        print(f"{elem}: {dict[elem]}")
    print()