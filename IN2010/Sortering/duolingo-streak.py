

from random import randint

def generate_list(length: int) -> list:
    A = []
    
    while len(A) < length:
        num = randint(0, length*2)
        if num in A:
            continue
        A.append(num)
    
    return list(map(str, A))


with open("duolingoholics", "w") as f:
    for holic in ["Ulf", "Henrik", "Sandra", "Robin", "Alex"]:
        f.write(f"{holic}\t" + " ".join(generate_list(100)) + "\n")
        

with open("duolingoholics") as f:
    for line in f:
        line = line.strip().split("\t")
        print(line[0], line[1].strip().split(" "))