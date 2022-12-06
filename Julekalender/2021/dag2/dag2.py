with open("input.txt", "r") as file:
    depth_pos = 0
    horizontal_pos = 0
    for line in file:
        line = line.strip().split()
        line[1] = int(line[1])
        if line[0] == "forward":
            horizontal_pos += line[1]
        elif line[0] == "down":
            depth_pos += line[1]
        elif line[0] == "up":
            depth_pos -= line[1]
    
    print(depth_pos*horizontal_pos)

with open("input.txt", "r") as file:
    aim = 0
    horizontal_pos = 0
    depth = 0
    for line in file:
        line = line.strip().split()
        line[1] = int(line[1])
        if line[0] == "down":
            aim += line[1]
        elif line[0] == "up":
            aim -= line[1]
        elif line[0] == "forward":
            horizontal_pos += line[1]
            depth += line[1]*aim
    
    print(depth*horizontal_pos)