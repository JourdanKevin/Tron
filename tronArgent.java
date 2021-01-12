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
            direc = "";
        switch(direc) {
            case "LEFT" :
                return 1 + distOf0(mapGame,x - 1,y,direc);
            case "RIGHT" :
                return 1 + distOf0(mapGame,x + 1,y,direc);
            case "UP" :
                return 1 + distOf0(mapGame,x,y - 1,direc);
            case "DOWN" :
                return 1 + distOf0(mapGame,x,y + 1,direc);
            default :
                return 0;
        }
    }
    static String chooseDir(int mapGame[][],int x,int y, String direc){
        int nb0;
        int nb1;
        switch (direc){
            case "RIGHT":
            case "LEFT" :
                nb0 = distOf0(mapGame,x,y-1,"UP");
                nb1 = distOf0(mapGame,x,y+1,"DOWN");
                System.err.println(nb0);
                System.err.println(nb1);
                if (nb0 == 0 && nb1 == 0){
                    return direc;
                }                     
                else if (nb0 > nb1)
                    return "UP";
                else
                    return "DOWN";
            case "UP":
            case "DOWN":
                nb0 = distOf0(mapGame,x+1,y,"RIGHT");
                nb1 = distOf0(mapGame,x-1,y,"LEFT");
                System.err.println(nb0);
                System.err.println(nb1);
                if (nb0 == 0 && nb1 == 0){
                    return direc;
                }     
                if ( nb0 > nb1 )
                    return "RIGHT";
                else
                    return "LEFT";
            default :
                return "";
        }
    }
    static String evalDir(int mapGame[][],int x,int y, String direc){
        System.err.println(direc);
        switch (direc){
            case "RIGHT":
            case "LEFT" :   
                    return chooseDir(mapGame,x,y,direc);
            case "UP":
            case "DOWN":
                    return chooseDir(mapGame,x,y,direc);
            default :
                return "";
        }
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
            printMap(mapGame);
            switch(direction){
                case "LEFT" :
                    if (mapGame[pose[1]][pose[0] - 1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    else if (mapGame[pose[1] -1 ][pose[0] - 1] == 1 && mapGame[pose[1] + 1][pose[0] -1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    break;
                case "RIGHT":
                    if (mapGame[pose[1]][pose[0] + 1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    else if (mapGame[pose[1] -1 ][pose[0] + 1] == 1 && mapGame[pose[1] + 1][pose[0] + 1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    break;
                case "UP":
                    if (mapGame[pose[1] - 1][pose[0]] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    else if (mapGame[pose[1] -1 ][pose[0] - 1] == 1 && mapGame[pose[1] -1 ][pose[0] + 1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    break;
                case "DOWN":
                    if (mapGame[pose[1] + 1][pose[0]] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                     else if (mapGame[pose[1] + 1 ][pose[0] - 1] == 1 && mapGame[pose[1] + 1 ][pose[0] + 1] == 1){
                        direction =evalDir(mapGame,pose[0],pose[1],direction);
                    }
                    break;
                // Write an action using System.out.println()
                // To debug: System.err.println("Debug messages...");                
            }
            System.out.println(direction); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
}
