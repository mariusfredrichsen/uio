dancefloor = 3
a, b = 1, 2
c, d = 1, 2

def dance(x, y, a, b, c, d, dancefloor, visited):
    queue = [(x, y)]
    while queue:
        x, y = queue.pop(0)
        if (x, y) not in visited:
            visited.add((x, y)) 
            print((x, y))
            visited.add((x, y))
            next_positions = [(x+a, y+b), (x+a, y-b), (x-a, y+b), (x-a, y-b), (x+c, y+d), (x+c, y-d), (x-c, y+d), (x-c, y-d)]
            for new_x, new_y in next_positions:
                if new_x >= 0 and new_x < dancefloor and new_y >= 0 and new_y < dancefloor:
                    queue.append((new_x, new_y))

visited = set()
dance(0, 0, a, b, c, d, dancefloor, visited)
print(len(visited))