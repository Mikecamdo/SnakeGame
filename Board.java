import java.util.Random;

public class Board {
    private Cell[][] theBoard; //the playing surface

    //Constructor
    public Board() {
        theBoard = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
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
            int row = generator.nextInt(10);
            int column = generator.nextInt(10);
            if (theBoard[row][column].getHasSnake() == false) {
                theBoard[row][column].setHasFood(true);
                return;
            }
        }
    }

}