from typing import List, Optional

# Macro variables for table values
NONE = 0
FALSE = 1
TRUE = 2


class TopDownSos:
    def __init__(self, sequence: List[int]) -> None:
        """
        Initializes the TopDownSos class and fills the first column and row of the table.

        Parameters:
            sequence (List[int]): An array of positive integers.
        """
        self.sequence = sequence
        total_sum = sum(self.sequence)
        
        # creates table with first col as True and rest of 1 row as False, else None
        table = [[True if j == 0 else (False if i == 0 else None) for j in range(total_sum+1)] for i in range(len(self.sequence)+1)]
        self.table = table
        
    def check_sum(self, k: int) -> Optional[List[int]]:
        """
        Calls a recursive function that fills in the necessary parts of the table.

        Parameters:
            k (int): The target sum.

        Returns:
            Optional[List[int]]: Returns a list of integers that make up the subsequence that can achieve the target sum k. Returns None if it can't be made.
        """
        self.check_sum_rec(len(self.sequence), k)

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



    def check_sum_rec(self, i: int, j: int):
        # calculated, return bool
        if self.table[i][j] != None:
            return self.table[i][j]
        
        # not calculated
        else:
            # fill the cells based on other cells
            if self.sequence[i-1] <= j:
                self.table[i][j] = self.check_sum_rec(i - 1, j - self.sequence[i-1]) or self.check_sum_rec(i - 1, j)
                return self.table[i][j]
            else:
                self.table[i][j] = self.check_sum_rec(i - 1, j)
                return self.table[i][j]
