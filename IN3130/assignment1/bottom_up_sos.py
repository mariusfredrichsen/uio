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
        pass

    def check_sum(self, k: int) -> Optional[List[int]]:
        """
        Checks if a subsequence exists that sums up to the target sum k.

        Parameters:
            k (int): The target sum.

        Returns:
            Optional[List[int]]: Returns a list of integers that make up the subsequence that can achieve the target sum k. Returns None if it can't be made.
        """
        pass