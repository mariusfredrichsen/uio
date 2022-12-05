with open("input.txt", "r") as file:
    index = -1
    crates = {}
    for line in file:
        line = list(line)
        for i in range(1,(len(line)+4)//4):
            crates[i] = []
        break
    print(crates)
    for line in file:
        index += 1
        if index < 7:
            line = list(line)
            for i in range(1,len(line),4):
                if line[i] not in ["", " ", "[", "]", "\n"]:
                    crates[i//4+1].append(line[i])
        elif index == 8:
            print(crates)
        elif index > 8:
            line = line.strip().split()
            index_pop = 0
            for i in range(int(line[1]),0,-1):
                try:
                    crates[int(line[5])].append(crates[int(line[3])][i])
                    index_pop += 1
                except:
                    pass
            for i in range(index_pop):
                if crates[int(line[3])] != []:
                    crates[int(line[3])].pop(0)
                    
    print(crates)