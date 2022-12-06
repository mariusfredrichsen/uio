def les(filename):
    with open(filename,'r') as infile:
        kal=[]
        sum=0
        for line in infile:
            if line=='\n':
                kal.append(sum)
                sum=0
            else:
                line=int(line)
                sum+=line
    return kal

def kal(list):
    mest=list[0]
    for i in range(len(list)):
        if mest<list[i]:
            mest=list[i]
    return mest

list=les('alveliste.txt')
print(kal(list))