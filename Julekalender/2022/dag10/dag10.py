class Instruction:
    def __init__(self, value, cycles):
        self._value = value
        self._cycles = cycles
    
    def cycle(self):
        self._cycles -= 1
        increase_cycle_value()
        if self._cycles == 0:
            increase_register_value(self._value)
            return True
        return False
    
    def __str__(self):
        return f"Value: {self._value}"

register_value = 1
def increase_register_value(value):
    global register_value
    register_value += value

cycles = 0
def increase_cycle_value():
    global cycles
    cycles += 1


with open("input.txt") as file:
    instructions = []
    sum = 0
    for line in file:
        line = line.strip().split()
        if line[0] == "addx":
            instructions.append(Instruction(int(line[1]),3))
        else:
            instructions.append(Instruction(0,1))
    while instructions != []:
        if instructions[0].cycle():
            instructions.pop(0)
        if cycles/20 in [1,3,5,7,9,11]:
            sum += cycles*register_value
    print(sum)

        

