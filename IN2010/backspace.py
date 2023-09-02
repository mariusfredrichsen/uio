line, out_line = input().strip(), []
for i in line: 
    if i == "<": out_line.pop() 
    else: out_line.append(i)
print("".join(out_line))