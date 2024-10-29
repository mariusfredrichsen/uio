import numpy as np
from typing import List
from collections import deque


class Flow:
    def __init__(self, array: np.ndarray):
        self.array = array
        self.max_flow = 0
        self.cut = []

    def solve(self) -> int:
        # Implement your max-flow algorithm here
        # Update self.max_flow
   
        t = len(self.array)-1
        s = 0
        
        path = self.dfs(s, t)
        while path:
            min_flow = self.get_min_flow(path)
            self.update_array(path, min_flow)
            
            self.max_flow += min_flow
            
            # update path
            path = self.dfs(s, t)
        return self.max_flow

    # s is node / first index of array
    def dfs(self, s, t):
        stack = deque([(s, [s])])
        visited = set()

        while stack:
            (v, path) = stack.pop()
            if v not in visited:
                visited.add(v)
                
                if v == t:
                    return path

                for u, c in enumerate(self.array[v]):
                    if u not in visited and c > 0:
                        stack.append((u, path + [u]))

        return None

    def get_min_flow(self, path):
        min_flow = self.array[path[0]][path[1]] # first flow value
        for i in range(1, len(path)-1):
            v, u = path[i], path[i+1]
            min_flow = min(min_flow, self.array[v][u])
        return min_flow

    def update_array(self, path, min_flow):
        for i in range(len(path)-1):
            v, u = path[i], path[i+1]
            self.array[v][u] -= min_flow
            self.array[u][v] += min_flow
            


    def get_cut(self) -> List[int]:
        # Implement logic to find the cut
        # Update self.cut
        
        self.cut = self.bfs(0)
        self.cut.sort()
        # only works if the self.solve function has already been called once

        return self.cut
    
    def bfs(self, s: int):
        queue = deque([s])
        visited = set()
        
        while queue:
            v = queue.popleft()
            if v not in visited:
                visited.add(v)
                for u, c in enumerate(self.array[v]):
                    if c > 0:
                        queue.append(u)
        
        return list(visited)