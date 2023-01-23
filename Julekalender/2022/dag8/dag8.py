"""DEL 1

def taller(matrix, row, col):
    counter = 0
    for temp_row in range(0, row):
        if matrix[temp_row][col] >= matrix[row][col]:
            counter += 1
            break
    for temp_row in range(row+1,len(matrix)):
        if matrix[temp_row][col] >= matrix[row][col]:
            counter += 1
            break
    
    for temp_col in range(0, col):
        if matrix[row][temp_col] >= matrix[row][col]:
            counter += 1
            break
    for temp_col in range(col+1,len(matrix[0])):
        if matrix[row][temp_col] >= matrix[row][col]:
            counter += 1
            break
    if counter == 4:
        return True
    return False

with open("input.txt") as file:
    forest = []
    for line in file:
        forest.append([int(i) for i in list(line.strip())])
    
    visable = 0
    for row in range(len(forest)):
        for col in range(len(forest)):
            if row != 0 or row != len(forest)-1 or col != 0 or len(forest[row])-1:
                if not taller(forest, row, col):
                    visable += 1
            else:
                visable += 1
    print(visable)"""

def taller(matrix, row, col):
    up = 0
    down = 0
    left = 0
    right = 0
    for temp_row in range(row-1, -1, -1):
        up += 1
        if matrix[temp_row][col] >= matrix[row][col]:
            break
    for temp_row in range(row+1,len(matrix)):
        down += 1
        if matrix[temp_row][col] >= matrix[row][col]:
            break
    
    for temp_col in range(col-1, -1, -1):
        left += 1
        if matrix[row][temp_col] >= matrix[row][col]:
            break
    for temp_col in range(col+1,len(matrix[0])):
        right += 1
        if matrix[row][temp_col] >= matrix[row][col]:
            break
    return up * down * left * right

with open("input.txt") as file:
    forest = []
    for line in file:
        forest.append([int(i) for i in list(line.strip())])
    
    scenic_scores = []
    for row in range(len(forest)):
        for col in range(len(forest)):
            scenic_scores.append(taller(forest, row, col))
    
    print(max(scenic_scores))
