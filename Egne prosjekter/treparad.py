Brett = [[1,2,3],[4,5,6],[7,8,9]]

game = True
Player = 0

def replace(x, Player):
    x = int(x)
    for i in range(0, len(Brett)):
        for l in range(0, len(Brett[0])):
            if x == Brett[i][l]:
                print(Brett[i][l])
                if Player%2 == 0:
                    Brett[i][l] = "X"
                    return
                elif Player%2 == 1:
                    Brett[i][l] = "O"
                    return
                else:
                    pass
            else:
                pass
    print("Ikke tilgjengelig")
    return

def checkList(game):
    for i in range(0, len(Brett)):
        if Brett[i][0] == Brett[i][0+1] and Brett[i][0] == Brett[i][0+2]:
            return True
        else:
            pass
    for i in range(0, len(Brett[0])):
        if Brett[0][i] == Brett[0+1][i] and Brett[0][i] == Brett[0+2][i]:
            return True
        else:
            pass
    if Brett[0][0] == Brett[1][1] and Brett[0][0] == Brett[2][2]:
        return True
    elif Brett[0][2] == Brett[1][1] and Brett[0][2] == Brett[2][0]:
        return True
    else:
        pass

while game:
    for i in Brett:
        print(i)
    x = input("\nVelg rute: ")
    replace(x, Player)
    if checkList(Brett):
        for i in Brett:
            print(i)
        break
    Player+=1
print(f"Spiller {Player%2+1} er vinneren!")
