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
        table = [[True if j == 0 else (False if i == 0 else None) for j in range(total_sum)] for i in range(len(self.sequence)+1)]
        self.table = table
        
    def check_sum(self, k: int) -> Optional[List[int]]:
        """
        Calls a recursive function that fills in the necessary parts of the table.

        Parameters:
            k (int): The target sum.

        Returns:
            Optional[List[int]]: Returns a list of integers that make up the subsequence that can achieve the target sum k. Returns None if it can't be made.
        """
        print(" ".join(["{:<5}".format(num) for num in range(sum(self.sequence))]))
        for row in self.table:
            print(" ".join(["False" if r == False else ("None " if r == None else "True ") for r in row]))
        print("\n\n\n")
        self.check_sum_rec(len(self.sequence), k)
        print(" ".join(["{:<5}".format(num) for num in range(-1, sum(self.sequence))]))

        asd = iter([0] + self.sequence)
        for row in self.table:
            print("{:<5}".format(next(asd)), end="")
            print(" ".join(["False" if r == False else ("None " if r == None else "True ") for r in row]))


    def check_sum_rec(self, i: int, j: int):
        print(i, j)
        if j < 0:
            return False

        if self.table[i][j] != None:
            print(i, j, self.table[i][j])
            return self.table[i][j]
        
        # not calculated
        else:
            if self.sequence[i-1] <= j:
                self.table[i][j] = self.check_sum_rec(i - 1, j - self.sequence[i-1]) or self.check_sum_rec(i - 1, j)
                return self.table[i][j]
            else:
                self.table[i][j] = self.check_sum_rec(i - 1, j)
                return self.table[i][j]


asd = TopDownSos([1,5,6,3,10])

asd.check_sum(11)