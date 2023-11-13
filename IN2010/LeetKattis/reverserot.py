alf = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ_.'

inn = input().strip().split()

n = int(inn[0])
inn = inn[1]

while n != 0:
    if n%2:
        inn = inn[::-1]
    
    out = ""
    for l in inn:
        out += alf[(alf.index(l) + n) % len(alf)]
    
    if not n%2:
        out = out[::-1]
    
    print(out)
    
    inn = input().strip().split()

    n = int(inn[0])
    inn = inn[1]