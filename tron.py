import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

def distOf0(mapGame,x,y,direc):
    # print(x, file=sys.stderr, flush=True)
    # print(y, file=sys.stderr, flush=True)
    # print(mapGame[y], file=sys.stderr, flush=True)
    # print(direc, file=sys.stderr, flush=True)
    if (mapGame[y][x] == 1) :
        return 0
    elif direc == "L" :
        return 1 + distOf0(mapGame,x - 1,y,direc)
    elif direc == "R" :
        return 1 + distOf0(mapGame,x + 1,y,direc)
    elif direc == "U" :
        return 1 + distOf0(mapGame,x,y - 1,direc)
    elif direc == "D" :
        return 1 + distOf0(mapGame,x,y + 1,direc)


D = {"R" : "RIGHT", "L" : "LEFT", "U" : "UP", "D" : "DOWN"}
xR =  30
yD = 20
k = 0
PlayerPose = {}
mapGame = []
direction = "L"
for i in range(yD + 2):
    temp = [1]    
    for j in range(xR):
        if (k == 0 or k == 21) :
            temp.append(1)
        else :
            temp.append(0)        
    k += 1
    temp.append(1)
    mapGame.append(temp)
# game loop
while True:
    # n: total number of players (2 to 4).
    # p: your player number (0 to 3).
    
    n, p = [int(i) for i in input().split()]
    
    for i in range(n):
        # x0: starting X coordinate of lightcycle (or -1)
        # y0: starting Y coordinate of lightcycle (or -1)
        # x1: starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
        # y1: starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)        
        x0, y0, x1, y1 = [int(j) for j in input().split()]
        mapGame[y1 + 1][x1 + 1] = 1
        if (i == p):
            i = "Me"
            # if (x0 + 1 > 15) :
            #     direction = "L"
            # else : direction = "R"
        PlayerPose[i] = (x1 + 1,y1 + 1,x0 + 1, y0 + 1)        
    # for ligne in mapGame:
    #     print(ligne, file=sys.stderr, flush=True)

    if direction == "U" :
        if mapGame[PlayerPose["Me"][1] - 1][PlayerPose["Me"][0]] == 1:
            if mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] - 1] == 1:
                direction = "R"
            elif mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] + 1] == 1:
                direction = "L"
            else :
                nbR = distOf0(mapGame,PlayerPose["Me"][0]+1,PlayerPose["Me"][1],"R")
                nbL = distOf0(mapGame,PlayerPose["Me"][0]-1,PlayerPose["Me"][1],"L")
                print(PlayerPose, file=sys.stderr, flush=True)
                print(str(nbR) + "," + str(nbL), file=sys.stderr, flush=True)
                if (nbL > nbR) :
                    direction = "L"
                else :
                    direction = "R"

    elif direction == "L":
        if mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] - 1] == 1:
            if mapGame[PlayerPose["Me"][1] - 1][PlayerPose["Me"][0]] == 1:
                direction = "D"
            elif mapGame[PlayerPose["Me"][1] + 1][PlayerPose["Me"][0]] == 1:
                direction = "U"
            else :
                nbU = distOf0(mapGame,PlayerPose["Me"][0],PlayerPose["Me"][1]-1,"U")
                nbD = distOf0(mapGame,PlayerPose["Me"][0],PlayerPose["Me"][1]+1,"D")
                print(PlayerPose, file=sys.stderr, flush=True)
                print(str(nbU) + "," +str(nbD), file=sys.stderr, flush=True)
                if (nbU > nbD) :
                    direction = "U"
                else :
                    direction = "D"
        
    elif direction == "R":
        if mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] + 1] == 1:
            if mapGame[PlayerPose["Me"][1] - 1][PlayerPose["Me"][0]] == 1:
                direction = "D"
            elif mapGame[PlayerPose["Me"][1] + 1][PlayerPose["Me"][0]] == 1:
                direction = "U"
            else :
                nbU = distOf0(mapGame,PlayerPose["Me"][0],PlayerPose["Me"][1]-1,"U")
                nbD = distOf0(mapGame,PlayerPose["Me"][0],PlayerPose["Me"][1]+1,"D")
                print(PlayerPose, file=sys.stderr, flush=True)
                print(str(nbU) + "," + str(nbD), file=sys.stderr, flush=True)
                if (nbU > nbD) :
                    direction = "U"
                else :
                    direction = "D"

    elif direction == "D" :
        if mapGame[PlayerPose["Me"][1] + 1][PlayerPose["Me"][0]] == 1:
            if mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] - 1] == 1:
                direction = "R"
            elif mapGame[PlayerPose["Me"][1]][PlayerPose["Me"][0] + 1] == 1:
                direction = "L"
            else :
                nbR = distOf0(mapGame,PlayerPose["Me"][0]+1,PlayerPose["Me"][1],"R")
                nbL = distOf0(mapGame,PlayerPose["Me"][0]-1,PlayerPose["Me"][1],"L")
                print(PlayerPose, file=sys.stderr, flush=True)
                print(str(nbR) + "," + str(nbL), file=sys.stderr, flush=True)
                if (nbL > nbR) :
                    direction = "L"
                else :
                    direction = "R"
    

    
    print(D[direction])
    # Write an action using print
    # To debug: print("Debug messages...", file=sys.stderr, flush=True)
    
    
    # A single line with UP, DOWN, LEFT or RIGHT
   