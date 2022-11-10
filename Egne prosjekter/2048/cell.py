import random as r

class Cell:
    def __init__(self, pos_x, pos_y):
        self._pos_x = pos_x
        self._pos_y = pos_y
        self._value = self.create_value()

    def __repr__(self):
        return f"{self._value}"

    def create_value(self):
        if r.randint(0,9) == 0:
            return 4
        return 2

    def value(self):
        return self._value

    def check_pos_x(self):
        return self._pos_x
    
    def check_pos_y(self):
        return  self._pos_y

    def check_same_value(self, other_cell):
        if self.value() == other_cell.value():
            return True
        return False

    def at_border(self):
        if self._pos_x == 0 or self._pos_x == 3 or self._pos_y == 0 or self._pos_y == 0:
            return True
        return False

    