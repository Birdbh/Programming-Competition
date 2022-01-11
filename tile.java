public class tile {
    int xlocation;
    int ylocation;
    boolean isbomb;
    int sf;
    boolean revealed = false;
    boolean flagged = false;

    public tile(int x, int y, boolean bomb){
        int xlocation = x;
        int ylocation = y;
        boolean isbomb = bomb;
    }

    public int settiley(int y){
        int ylocation = y;
        return ylocation;
    }

    public int settilex(int x){
        int xlocation = x;
        return x;
    }

    public boolean setbomb(boolean bomb){
        boolean isbomb = bomb;
        return isbomb;
    }
}
