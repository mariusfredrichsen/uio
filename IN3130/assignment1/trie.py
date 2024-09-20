import typing

class Node:
    def __init__(self, letter):
        self.letter = letter
        self.next_letters = []
        self.is_last_letter = False
    
    def get_letter(self) -> str:
        return self.letter
    
    def get_next_letters(self):
        return self.next_letters
    
    def set_is_last_letter(self):
        self.is_last_letter = True
    
    def get_is_last_letter(self):
        return self.is_last_letter
        
    def __str__(self):
        return self.letter
    
    def __eq__(self, node):
        return self.letter == node.letter

    def __lt__(self, node):
        return self.letter > node.letter

class Trie:
    def __init__(self):
        """
        Initialize the Trie data structure here.
        """
        self.root = Node("") # root node is empty
        pass

    def insert(self, word: str) -> None:
        """
        Inserts a word into the Trie.

        Parameters:
            word (str): The word to insert. Assume the word is lowercase.

        Returns:
            None
        """
        
        self._insert_rec(word, self.root)
    
    def _insert_rec(self, word: str, node: Node) -> None:
        # base case, last node of word
        if word == "":
            node.set_is_last_letter()
            
        else:
            letter = word[0]
            next_letters = node.get_next_letters()
            
            for _, next_node in enumerate(next_letters):
                if next_node.get_letter() == letter:
                    self._insert_rec(word[1:], next_node)
                    break
            else: # letter isnt in the branch
                new_node = Node(letter)
                next_letters.append(new_node)
                next_letters.sort(reverse=True) # keep it in alphabetic order
                self._insert_rec(word[1:], new_node)



    def look_up(self, word: str) -> bool:
        """
        Checks if a word has been inserted into the Trie.

        Parameters:
            word (str): The word to look up. Assume it's always lowercase.

        Returns:
            bool: True if the word is found, False otherwise. If 'high' is inserted and the function receives 'hight', it should return False. Only inserted words should return True.
        """
        
        return self._look_up_rec(word, self.root)
    
    def _look_up_rec(self, word: str, node: Node) -> bool:
        if len(word) == 0:
            return node.get_is_last_letter()
        
        letter = word[0]
        next_letters = node.get_next_letters()
        
        for _, next_node in enumerate(next_letters):
            if next_node.get_letter() == letter:
                return self._look_up_rec(word[1:], next_node)
        
        return len(word) == 0 and node.get_is_last_letter()
        
        

    def print_tree(self) -> str:
        """
        Prints the Trie in alphabetical order.

        Example:
            internet: (internet)
            interview: (inter(net)(view))
            inter: (inter(net)(view))

        Returns:
            str: A string that shows the state of the Trie.
        """
        
        return "(" + self.print_tree_rec(self.root, self.root.get_letter()) + ")"
        
    
    def print_tree_rec(self, node, out: str):
        next_letters = node.get_next_letters()
        
        for next_node in next_letters:
            if len(next_letters) > 1:
                out += "("
            out += self.print_tree_rec(next_node, next_node.get_letter())
            if len(next_letters) > 1:
                out += ")"
        
        return out
        
        
