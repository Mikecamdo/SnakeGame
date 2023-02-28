import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private Cell[][] theBoard; //the playing surface
    private int numRows = 20;
    private int numColumns = 20;
    private int cellSize = 20;

    //Constructor
    public Board() {
        theBoard = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                theBoard[i][j] = new Cell(i, j);
            }
        }
    }

    //Getter and Setter methods
    public void setTheBoard(Cell[][] theBoard) {
        this.theBoard = theBoard;
    }

    public Cell[][] getTheBoard() {
        return theBoard;
    }

    //Methods
    public void addFood() { //randomly selects a cell to add food to
        Random generator = new Random();
        while (true) {
            int row = generator.nextInt(numRows);
            int column = generator.nextInt(numColumns);
            if (theBoard[row][column].getHasSnake() == false) {
                theBoard[row][column].setHasFood(true);
                return;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(numColumns * cellSize, numRows * cellSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int x = j * cellSize;
                int y = i * cellSize;
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

}