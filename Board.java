import java.util.Random;

public class Board {
    static int x;
    static int y;
    static int bombs;

    //Create array and fill with tiles and randomly with bombs
    public static void BoardCreation(int x, int y, int bombs){
        tile[][] tilearray = new tile[x][y];
        for (int i = 0; i < x; i++){
            for (int j = 0; j < x; j++){
                tilearray[x][y] = new tile(i, j, false);
            }
        }
        for(int i = 0; i < bombs; i++){
            Random rand = new Random();
            int rand_x = rand.nextInt(x+1);
            int rand_y = rand.nextInt(y+1);
            tilearray[rand_x][rand_y].isbomb = true;
        }
    }

    //Define if tile is within the bounds of the board
    public static boolean valid(int x, int y){
        if (x < 0 || x >= Board.x){
            return false;
        }
        if (y < 0 || y >= Board.y){
            return false;
        }
        return true;
    }

    //Counts the number of bombs around
    public static int safety(int x, int y){
        int count = 0;
        for (int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if (valid(x + i, y + j)){
                    if(tilearray[x+i][y+j].isbomb == true){
                        count+=1;
                    }
                }
            }
        }

        return count;
    }

    //Loop through tiles array and count bombs around and add to sf (safety factor)

    public static void safetyfactor(){
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (tilearray[i][j].isbomb == false){
                    tilearray[i][j].sf = safety(i,j);
                }
                else{
                    tilearray[i][j].sf = -1;
                }
            }
        }
    }

    //Define event of clicked tile 
    public static boolean clicked(int x, int y){
        //If tile clicked is a bomb
        if (tilearray[x][y].isbomb == true){
            return false;
        }

        else{
            tilearray[x][y].revealed = true;

            if (tilearray[x][y].sf == 0){
                for (int i = -1; i < 2; i++){
                    for(int j = -1; j < 2; j++){
                        if (i == 0 && j == 0){
                            //Nothing
                        }
                        if (valid(x+i,y+j) && tilearray[x+i][y+j].revealed == false){
                            clicked(x+i,y+j);
                        }
                    }
                }
            }
            return true;
        }
    }

    //Printing the Board
    public static void print(){
        for (int i = 0; i < x; i++){

            for (int j = 0; j < y; j++){
                if (tilearray[i][j].revealed == false){
                    System.out.println("[]");
                }

                //Print out a bomb if revealed and a bomb
                else if(tilearray[i][j].isbomb == true && tilearray[i][j].revealed == true){
                    System.out.println("[B]");
                }

                //Print out Flag
                else if (tilearray[i][j].flagged == true){
                    System.out.print("[F]");
                }

                else{
                    System.out.print("[" + tilearray[i][j].sf + "]");
                }
            }
        }
    }

    //Check if win
    public static boolean wincheck(){
        int count = 0;
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (tilearray[i][j].revealed == false){
                    count+=1;
                }
            }
        }
        if (count == bombs){
            return true;
        }
        else{
            return false;
        }
    }
}