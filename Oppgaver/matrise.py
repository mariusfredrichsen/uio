listeNosted = [[1,2,3],[4,5,6],[7,8,9]]
tall = [1,6,8]

for i in range(len(listeNosted)):
    for l in range(len(listeNosted)):
        if listeNosted[i][l] in tall:
            print(listeNosted[i][l])

print(listeNosted[0][0]+listeNosted[1][0]+listeNosted[2][0])

print(sum(listeNosted[0]))
