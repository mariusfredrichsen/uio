with open("input.txt", "r") as file:
    common_bin = {}
    for line in file:
        line = list(line.strip())
        for i in range(len(line)):
            if i not in common_bin:
                common_bin[i] = [line[i]]
            else:
                common_bin[i].append(line[i])
    
    for elem in common_bin:
        print(elem)
        print(common_bin[elem])
    
    gamma = ""
    epsilon = ""
    for elem in common_bin:
        gamma += max(common_bin[elem], key = common_bin[elem].count)
        epsilon += min(common_bin[elem], key = common_bin[elem].count)
    
    gamma_int = 0
    gamma = list(gamma)
    gamma.reverse()
    index = 1
    for bin in gamma:
        if bin == "1":
            gamma_int += index
        index *= 2
    print(gamma_int)
    
    epsilon_int = 0
    epsilon = list(epsilon)
    epsilon.reverse()
    index = 1
    for bin in epsilon:
        if bin == "1":
            epsilon_int += index
        index *= 2
    print(epsilon_int)

    
    print(gamma_int*epsilon_int)