with open("input.txt", "r") as file:
    depths = []
    for line in file:
        depths.append(int(line.strip()))
    
    increases = 0
    for i in range(len(depths)-1):
        if depths[i] < depths[i+1]:
            increases += 1
    
    print(increases)
    
    increases = 0
    grouped_depths = []
    for i in range(len(depths)-2):
        grouped_depths.append(depths[i]+depths[i+1]+depths[i+2])
        
    for i in range(len(grouped_depths)-1):
        if grouped_depths[i] < grouped_depths[i+1]:
            increases += 1
    print(grouped_depths)
            
    print(increases)