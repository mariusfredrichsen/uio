input = input()

input = list(input)
for e in range(len(input)):
    input[e] = int(input[e])

pieces = [1,1,2,2,2,8]

for i in range(len(input)):
    input[i] = pieces[i] - input[i]

str = ""

for e in input:
    str += str(e) + " "

str[-1] = ""

print(str)

