import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game {
    private Board theBoard;
    private boolean gameOver = false;
    private String[] directions = {"left", "right", "up", "down"};
    private String currentDirection = "right";

    //Constructor
    public Game() {
        theBoard = new Board(); 
    }

    //Getter and Setter methods
    public void setTheBoard(Board theBoard) {
        this.theBoard = theBoard;
    }

    public Board getTheBoard() {
        return theBoard;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    //Methods
    public Cell getNext(Cell current, String direction) {
        int row = current.getRow();
        int column = current.getColumn();

        if (direction == "left") {
            column -= 1;
        } else if (direction == "down") {
            row += 1;
        } else if (direction == "right") {
            column += 1;
        } else if (direction == "up") {
            row -= 1;
        }

        Cell next = theBoard.getTheBoard()[row][column];
        return next;
    }

    public void run() {
        JFrame theWindow = new JFrame();
        // theBoard.loadImages();

        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.add(theBoard);
        theWindow.pack();
        theWindow.setResizable(false);
        theWindow.setLocationRelativeTo(null);
        theWindow.setVisible(true);
        theWindow.addKeyListener(new Input());
        int p = 98;
        
        while (gameOver == false) {
            
        }
    }

    public class Input extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if ((keyCode == KeyEvent.VK_LEFT) && (currentDirection != "right")) {
                currentDirection = "left";
                System.out.println("Left");
            } else if ((keyCode == KeyEvent.VK_DOWN) && (currentDirection != "up")) {
                currentDirection = "down";
                System.out.println("Down");
            } else if ((keyCode == KeyEvent.VK_RIGHT) && (currentDirection != "left")) {
                currentDirection = "right";
                System.out.println("Right");
            } else if ((keyCode == KeyEvent.VK_UP) && (currentDirection != "down")) {
                currentDirection = "up";
                System.out.println("Up");
            }
        }
    }
}
