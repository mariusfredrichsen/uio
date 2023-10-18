liste = [[1,2,3], [4,5,6], [7,8,9]]

for v in [i for j in liste for i in j]:
    print(v)