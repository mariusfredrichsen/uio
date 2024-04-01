import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt
import matplotlib.colors
from time import time

import sys
sys.setrecursionlimit(10000000)


start = time()
print("fetching data from .nc file at:", time() - start)
ncfile = Dataset('norway_oceandata.nc', 'r')

lats = ncfile.variables['lat'][:]
longs = ncfile.variables['lon'][:]

elevation = ncfile.variables['elevation'][:, :]

class Node:
    def __init__(self, lat, lon, elevation, color):
        self.lat = lat
        self.lon = lon
        self.elevation = elevation
        self.color = color
        self.neighbours = []
    
    def __str__(self):
        return f"{self.lat , self. lon}"
    
    def __lt__(self, other):
        return False

    def __eq__(self, other):
        return (self.lat, self.lon) == (other.lat, other.lon)
    
    def __hash__(self):
        return hash((self.lat, self.lon)) 

downfactor = 5
    
new_lats = lats[::downfactor]
new_longs = longs[::downfactor]

matrix = []
land = set()
# elevation above ground 0 to 3000 -> 50 to 255
# elevation under ground 0 to -1000 -> 255 to 50
cp1 = time()
print("done! segment took: ", cp1 - start, "s")
print("creating graph at:", time() - start, "s")
def map_value(v, A, B, a, b):
    return a + (v - A) * (b - a) / (B - A)

for i in range(0, len(lats), downfactor):
    row = []
    for j in range(0, len(longs), downfactor):
        color = [0, 0, map_value(elevation[i][j], 0, -9000, 1, 0.2)]
        if elevation[i][j] > 0:
            color = [0, map_value(elevation[i][j], 0, 3000, 0.2, 1), 0]
        row.append(Node(lats[i], longs[j], elevation[i][j], color))
    matrix.append(row)

for i in range(len(matrix)):
    for j in range(len(matrix[i])):
        for x in range(-1, 2):
            for y in range(-1, 2):
                if (abs(x) + abs(y) != 0 and (i + x >= 0 and i + x < len(matrix) and j + y >= 0 and j + y < len(matrix[i]))):
                    matrix[i][j].neighbours.append(matrix[i+x][j+y])

cp2 = time()
print("done! segment took: ", cp2 - cp1, "s")
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
# lat_index = np.abs(new_lats - 60.404079).argmin()
# lon_index = np.abs(new_longs - 5.282634).argmin()

# end_node_one = matrix[lat_index][lon_index]

#to (alta) 69.952094, 23.184178
# lat_index = np.abs(new_lats - 69.952094).argmin()
# lon_index = np.abs(new_longs - 23.184178).argmin()

# end_node_one = matrix[lat_index][lon_index]


flat_matrix = [x for y in matrix for x in y]
for node in flat_matrix:
    if node.elevation > -5 and node != end_node_one: # 5 meters below sea level
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
    return abs(current.lat - goal.lat) + abs(current.lon - goal.lon)

def calculate_priority(current, other):
    value = 2
    if current.lat == other.lat or current.lon == other.lon:
        value = 1
    
    # adjust to stay near areas with 20m deep waters
    if other.elevation < -30 or other.elevation > -10:
        value += 1
    
    return value

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
            if neighbor not in land:
                tentative_g_cost = g_costs[current_node] + calculate_priority(current_node, neighbor)
                
                if neighbor not in g_costs or tentative_g_cost < g_costs[neighbor]:
                    parents[neighbor] = current_node
                    g_costs[neighbor] = tentative_g_cost
                    f_costs[neighbor] = tentative_g_cost + heuristic(neighbor, goal)
                    if all(n != neighbor for _, n in open_set):
                        heapq.heappush(open_set, (f_costs[neighbor], neighbor))

cp3 = time()
print("segment took: ", cp3 - cp2, "s")
print("creating path at:", time() - start, "s")
parents = a_star(start_node, end_node_one)

# for node in parents:
#     print(node)

path = []
t = end_node_one
while t:
    path.append(t)
    t = parents[t]

path = path[::-1]

cp4 = time()
print("segment took: ", cp4 - cp3, "s")
print("done! path created at:", time() - start)

file = open("path.txt", "w")
for node in path:
    file.write(f"Point.fromLngLat({node.lon}, {node.lat}),\n")
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


# for node in flat_matrix:
#     if node in path:
#         node.color = [1,1,1]
        
# x = [n.lon for n in flat_matrix]
# y = [n.lat for n in flat_matrix]
# colors = [n.color for n in flat_matrix]

# for color in colors:
#     if any([0 < i > 1 for i in color]):
#         print(color)
# plt.scatter(x, y, c=colors)

# plt.show()