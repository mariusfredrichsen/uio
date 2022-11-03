from playboard import Playboard

playboard = Playboard()

playboard.draw()

for i in range(2):
    playboard.create_cell()

playboard.draw()

directions = ["left", "right", "up", "down"]
directions = directions * 16
for direction in directions:
    playboard.move_cells(direction)
    playboard.draw()
    playboard.create_cell()
    playboard.draw()