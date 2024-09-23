from typing import List, Optional


class BottomUpSos:
    def __init__(self, sequence: List[int]) -> None:
        """
        Initializes the BottomUpSos class and calculates the table.

        Parameters:
            sequence (List[int]): An array of positive integers.
        """
        self.sequence = sequence
        total_sum = sum(self.sequence)
        
        # creates a table [|sequence| + 1] x [total_sum + 1] of False values
        # where first column is True
        table = [[j == 0 for j in range(total_sum+1)] for _ in range(len(self.sequence)+1)]
        
        col_n = len(table[0])
        row_n = len(table)
        
        for i in range(1, row_n):
            for j in range(1, col_n):
                # checks if new value is "addable"
                if self.sequence[i-1] <= j:
                    # checks if target j - new value in the above row is true
                    table[i][j] = table[i-1][j] or table[i-1][j - self.sequence[i-1]]
                else:
                    table[i][j] = table[i-1][j]
        
        self.table = table

    def check_sum(self, k: int) -> Optional[List[int]]:
        """
        Checks if a subsequence exists that sums up to the target sum k.

        Parameters:
            k (int): The target sum.

        Returns:
            Optional[List[int]]: Returns a list of integers that make up the subsequence that can achieve the target sum k. Returns None if it can't be made.
        """
        for i in range(1, len(self.sequence)+1):
            if self.table[i][k]:
                # start finding the elements
                output = []
                i = i
                j = k
                while j > 0:
                    output.append(self.sequence[i-1])
                    j -= self.sequence[i-1]
                    
                    # find the first row with True value
                    while self.table[i-1][j] and i > 0:
                        i -= 1
                        
                return output
        # for loop successfully ran and no valid selection exists
        else:
            return None