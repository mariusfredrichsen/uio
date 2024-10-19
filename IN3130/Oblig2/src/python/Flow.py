import numpy as np
from typing import List


class Flow:
    def __init__(self, array: np.ndarray):
        self.array = array
        self.max_flow = 0
        self.cut = []

    def solve(self) -> int:
        # Implement your max-flow algorithm here
        # Update self.max_flow
        return self.max_flow

    def get_cut(self) -> List[int]:
        # Implement logic to find the cut
        # Update self.cut
        return self.cut
