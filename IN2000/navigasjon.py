import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt

import sys
print(sys.getrecursionlimit())

sys.setrecursionlimit(10000000)

print(sys.getrecursionlimit())


ncfile = Dataset('norway_oceandata.nc', 'r')

lats = ncfile.variables['lat'][:]
longs = ncfile.variables['lon'][:]

elevation = ncfile.variables['elevation'][:, :]

class Node:
    def __init__(self, lat, lon, elevation):
        self.lat = lat
        self.lon = lon
        self.elevation = elevation
        self.neighbours = []
    
    def __str__(self):
        return f"{self.lat , self. lon}"
    
    def __lt__(self, other):
        return False

    def __eq__(self, other):
        return (self.lat, self.lon) == (other.lat, other.lon)
    
    def __hash__(self):
        return hash((self.lat, self.lon)) 

downfactor = 2 
    
new_lats = lats[::downfactor]
new_longs = longs[::downfactor]

matrix = []
land = set()

for i in range(0, len(lats), downfactor):
    row = []
    for j in range(0, len(longs), downfactor):
        color = 'blue'
        if elevation[i][j] > 0:
            
            color = 'green'
        row.append(Node(lats[i], longs[j], color))
    matrix.append(row)

for i in range(len(matrix)):
    for j in range(len(matrix[i])):
        for x in range(-1, 2):
            for y in range(-1, 2):
                if (abs(x) + abs(y) != 0 and (i + x >= 0 and i + x < len(matrix) and j + y >= 0 and j + y < len(matrix[i]))):
                    matrix[i][j].neighbours.append(matrix[i+x][j+y])

# print(" | ".join([str(node) for node in matrix[1][1].neighbours]))

#from (tip of the oslo fjord) 59.888526, 10.676412
lat_index = np.abs(new_lats - 59.888526).argmin()
lon_index = np.abs(new_longs - 10.676412).argmin()

start_node = matrix[lat_index][lon_index]

#to (tip of drammen fjord) 59.735175, 10.250544
lat_index = np.abs(new_lats - 59.735175).argmin()
lon_index = np.abs(new_longs - 10.250544).argmin()

end_node_one = matrix[lat_index][lon_index]

#to (right besides bergen) 60.404079, 5.282634
lat_index = np.abs(new_lats - 60.404079).argmin()
lon_index = np.abs(new_longs - 5.282634).argmin()

end_node_one = matrix[lat_index][lon_index]

flat_matrix = [x for y in matrix for x in y]
for node in flat_matrix:
    if node.elevation == "green" and node != end_node_one:
        land.add(node)

# start av bredde først
def bfs_rec(s, parents: dict, queue: list) -> list:   
    if (s == end_node_one):
        return parents
    
    for v in s.neighbours:
        if v not in parents and v not in land:
            queue.append(v)
            parents[v] = s

    if queue:
        return bfs_rec(queue.pop(0), parents, queue)
    return parents

import heapq

def heuristic(current, goal):
    "Manhattan distance heuristic for A*."
    return abs(current.lat - goal.lat) + abs(current.lon - goal.lon)

def a_star(start, goal):
    open_set = []
    heapq.heappush(open_set, (0, start))
    parents = {start: None}
    g_costs = {start: 0}
    f_costs = {start: heuristic(start, goal)}

    while open_set:
        _, current_node = heapq.heappop(open_set)
        
        if current_node == goal:
            return parents

        for neighbor in current_node.neighbours:
            if neighbor not in land:  # Assuming 'land' is a global set of blocked nodes
                tentative_g_cost = g_costs[current_node] + 1  # Replace with the actual movement cost if required
                if neighbor not in g_costs or tentative_g_cost < g_costs[neighbor]:
                    parents[neighbor] = current_node
                    g_costs[neighbor] = tentative_g_cost
                    f_costs[neighbor] = tentative_g_cost + heuristic(neighbor, goal)
                    if all(n != neighbor for _, n in open_set):
                        heapq.heappush(open_set, (f_costs[neighbor], neighbor))


parents = a_star(start_node, end_node_one)

# for node in parents:
#     print(node)

path = []
t = end_node_one
while t:
    path.append(t)
    t = parents[t]

path = path[::-1]
    
# def braeth_first_search(s: Node):
#     visited = set([s])
#     queue = [s]
#     result = []

#     while queue:
#         v = queue.pop(0)
#         result.append(v)
#         for u in s.neighbours:
#             if u not in visited:
#                 visited.add(u)
#                 queue.append(u)
#     return result
# print(start_node)
    
# print(matrix)


for node in flat_matrix:
    if node in path:
        node.elevation = "white"
        
x = [n.lon for n in flat_matrix]
y = [n.lat for n in flat_matrix]
colors = [n.elevation for n in flat_matrix]


plt.scatter(x, y, c=colors)

plt.show()