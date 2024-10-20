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
        array = self.array.copy()
        
        t = len(self.array)-1
        s = 0
        
        path = self.dfs(s, t)
        while path:
            min_flow = self.get_min_flow(path)
            self.update_array(array, path, min_flow)
            
            self.max_flow += min_flow
            
            # update path
            path = self.dfs(array, s, t)
        return self.max_flow

    # s is node / first index of array
    def dfs(self, array, s, t):
        stack = deque([(s, [s])])
        visited = set()

        while stack:
            (v, path) = stack.pop()
            if v not in visited:
                visited.add(v)
                
                if v == t:
                    return path

                for u, c in enumerate(array[v]):
                    if u not in visited and c > 0:
                        stack.append((u, path + [u]))

        return None

    def get_min_flow(self, array, path):
        min_flow = array[path[0]][path[1]] # first flow value
        for i in range(1, len(path)-1):
            v, u = path[i], path[i+1]
            min_flow = min(min_flow, array[v][u])
        return min_flow

    def update_array(self, array, path, min_flow):
        for i in range(len(path)-1):
            v, u = path[i], path[i+1]
            array[v][u] -= min_flow
            array[u][v] += min_flow
            


    def get_cut(self) -> List[int]:
        # Implement logic to find the cut
        # Update self.cut
        self.cut = list(self.find_min_cut(len(self.array)-1, set([0])))
        self.cut.sort()
        
        return self.cut
    
    # try out all possibilites
    def find_min_cut(self, t, S: set, cuts=[]):
        cuts.append((self.get_total_flow(S), S))
        for v in S:
            for u, c in enumerate(self.array[v]):
                if not u in S and c > 0 and u != t:
                    newS = S | {u}
                    self.find_min_cut(t, newS, cuts) # create a copy of set S with u added
        return min(cuts)
                    
    def get_total_flow(self, S: set) -> int:
        # set S including s and nodes for total outflow to rest of network
        total_flow = 0
        for v in S:
            for u, c in enumerate(self.array[v]):
                if not u in S and c > 0:
                    total_flow += c
        return total_flow