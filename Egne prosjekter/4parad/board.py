from pieces import Piece

class Board:
    def __init__(self):
        self._board = self.create_board()
        self._pieces = []
        
    def create_board(self):
        board = []
        for i in range(6):
            board.append([None, None, None, None, None, None, None])
        
        return board
    
    

    def place_piece(self, pos_x, pos_y):
