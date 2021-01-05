import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static void printMap(int mapGame[][]){
        for (int[] is : mapGame) {
            String chaine = "";
            for (int is2 : is) {
                chaine = chaine + is2;        
            }
            System.err.println(chaine);            
        }
    }
    static void printList(int list[]){
        for (int is: list) {
                System.err.println(is);        
            }             
    }

    static int distOf0(int mapGame[][],int x,int y, String direc){
        if (mapGame[y][x] == 1)
            return 0;
        switch(direc) {
            case "LEFT" :
                return 1 + distOf0(mapGame,x - 1,y,direc);
            case "RIGHT" :
                return 1 + distOf0(mapGame,x + 1,y,direc);
            case "UP" :
                return 1 + distOf0(mapGame,x,y - 1,direc);
            case "DOWN" :
                return 1 + distOf0(mapGame,x,y + 1,direc);
        }
        return 0;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int xR = 32;
        int yD = 22;
        String direction = "LEFT";
        int[][] mapGame = new int[yD][xR];
        int[] pose = new int[2];
        for(int i=0;i< yD;i++){
            for(int j = 0;j<xR;j++){
            if (i==0 || j==0 || i==yD-1 || j==xR-1)
                mapGame[i][j] = 1;            
            else 
                mapGame[i][j] = 0;
            }
        }
        // printMap(mapGame);

        // System.err.println("Debug messages...")
        // game loop
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                
                mapGame[Y1+1][X1+1] = 1;
            
                if (i == P){
                    pose[0] = X1 + 1;
                    pose[1] = Y1 + 1;
                    printList(pose);
                }            
            }
            int nb1 = 0;
            int nb2 = 0;
            printMap(mapGame);
            switch(direction){
                case "LEFT" :
                    if (mapGame[pose[1]][pose[0] - 1] == 1){
                        if (mapGame[pose[1] - 1][pose[0]] == 1){
                            direction = "DOWN";
                        }
                        else if (mapGame[pose[1] + 1][pose[0]] == 1){
                            direction = "UP";
                        }
                        else{
                            nb1 = distOf0(mapGame,pose[0],pose[1]-1,"UP");
                            nb2 = distOf0(mapGame,pose[0],pose[1]+1,"DOWN");                        
                            if (nb1 > nb2)
                                direction = "UP";
                            else
                                direction = "DOWN";
                        }
                    }
                    break;
                case "RIGHT":
                    if (mapGame[pose[1]][pose[0] + 1] == 1){
                        if (mapGame[pose[1] - 1][pose[0]] == 1)
                            direction = "DOWN";
                        else if (mapGame[pose[1] + 1][pose[0]] == 1)
                            direction = "UP";
                        else{
                            nb1 = distOf0(mapGame,pose[0],pose[1]-1,"UP");
                            nb2 = distOf0(mapGame,pose[0],pose[1]+1,"DOWN");
                            if (nb1 > nb2)
                                direction = "UP";
                            else
                                direction = "DOWN";
                        }
                    }
                    break;
                case "UP":
                    if (mapGame[pose[1] - 1][pose[0]] == 1){
                        if (mapGame[pose[1]][pose[0] - 1] == 1)
                            direction = "RIGHT";
                        else if (mapGame[pose[1]][pose[0] + 1] == 1)
                            direction = "LEFT";
                        else{
                            nb1 = distOf0(mapGame,pose[0]+1,pose[1],"RIGHT");
                            nb2 = distOf0(mapGame,pose[0]-1,pose[1],"LEFT");
                            if (nb1 > nb2)
                                direction = "RIGHT";
                            else
                                direction = "LEFT";
                        }
                    }
                    break;
                case "DOWN":
                    if (mapGame[pose[1] + 1][pose[0]] == 1){
                        if (mapGame[pose[1]][pose[0] - 1] == 1)
                            direction = "RIGHT";
                        else if (mapGame[pose[1]][pose[0] + 1] == 1)
                            direction = "LEFT";
                        else{
                            nb1 = distOf0(mapGame,pose[0]+1,pose[1],"RIGHT");
                            nb2 = distOf0(mapGame,pose[0]-1,pose[1],"LEFT");
                            if (nb1 > nb2)
                                direction = "RIGHT";
                            else
                                direction = "LEFT";
                        }
                    }
                    break;
                // Write an action using System.out.println()
                // To debug: System.err.println("Debug messages...");                
            }
            System.out.println(direction); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
}
