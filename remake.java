import java.util.Random;
import javax.swing.*;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

//Create the board the the functions of the board
class main {
    static int x = 9;
    static int y = 9;
    static int bombs = 20;
    public static void main(String[] args) {
        Board.BoardCreation(x, y, bombs);
        Board.valid(x, y);
        Board.safety(x, y);
        Board.safetyfactor();
        Board.clicked(x, y);
        Board.print();
    }
}
