first_input = int(input())

for i in range(first_input):
    liste = []
    for i in range(int(input())):
        liste.append(input())
    print(len(set(liste)))