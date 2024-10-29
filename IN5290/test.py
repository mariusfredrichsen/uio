import requests

# List of common fruits to test
with open("fruits.txt") as f:
    fruits = [line.strip().lower() for line in f]

# URL template
url_template = "http://3cpo.hackingarena.com:805/index.php?fruit={}"

# Iterate through the list of fruits and request the URL
for fruit in fruits:
    url = url_template.format(fruit)
    response = requests.get(url)
    
    # Check if the response signifies a valid fruit
    print(response.content)
