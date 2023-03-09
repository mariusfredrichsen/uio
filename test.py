input_string = input().strip().split()

for i in range(len(input_string)):
    input_string[i] = int(input_string[i])

print(abs(input_string[0]-input_string[1]))