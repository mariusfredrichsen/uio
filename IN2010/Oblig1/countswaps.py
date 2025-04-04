class CountSwaps(list):
    swaps = 0

    def swap(self, i, j):
        self.swaps += 1
        self[i], self[j] = self[j], self[i]

    def swap_merge(self):
        self.swaps += 1
    
    def swap_merge_final(self, x):
        self.swaps += x
    
    def get(self):
        return self.swaps
