"""with open("input.txt", "r") as file:
    crates = {}
    index = 0
    for line in file:
        if index == 0:
            line = list(line)
            for i in range(len(line)):
                crates[i//4+1] = []
        if index < 8:
            line = list(line)
            for i in range(1,len(line),4):
                if i//4+1 in crates and line[i] not in [",", "", " "]:
                    crates[i//4+1].append(line[i])
        if index > 9:
            line = line.strip().split()
            for i in range(int(line[1])):
                if crates[int(line[3])] != []:
                    crates[int(line[5])].insert(0,crates[int(line[3])].pop(0))
        index += 1
    
    for k in crates:
        print(crates[k][0], end = "")"""



with open("input.txt", "r") as file:
    crates = {}
    index = 0
    for line in file:
        if index == 0:
            line = list(line)
            for i in range(len(line)):
                crates[i//4+1] = []
        if index < 8:
            line = list(line)
            for i in range(1,len(line),4):
                if i//4+1 in crates and line[i] not in [",", "", " "]:
                    crates[i//4+1].append(line[i])
        if index > 9:
            line = line.strip().split()
            