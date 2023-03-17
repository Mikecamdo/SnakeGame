import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Board extends JPanel implements ActionListener{
    private Cell[][] theBoard; //the playing surface
    private Snake theSnake;
    private Timer theTimer;
    private int numRows = 20;
    private int numColumns = 20;
    private int cellSize = 20;
    private int foodX;
    private int foodY;
    private String[] directions = {"left", "right", "up", "down"};
    private String currentDirection = "right";
    private boolean gameOver = false;
    private boolean isExpanding = false;

    //Constructor
    public Board() {
        this.setFocusable(true);
        this.addKeyListener(new Input());
        theBoard = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                theBoard[i][j] = new Cell(i, j);
            }
        }
        run();
    }

    //Getter and Setter methods
    public void setTheBoard(Cell[][] theBoard) {
        this.theBoard = theBoard;
    }

    public Cell[][] getTheBoard() {
        return theBoard;
    }

    public void setTheSnake(Snake theSnake) {
        this.theSnake = theSnake;
    }

    public Snake getTheSnake() {
        return theSnake;
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
    public void run() {  
        currentDirection = "right";
        Cell start = new Cell(0 ,0);
        theSnake = new Snake(start);      
        addFood();
        theTimer = new Timer(200, this);    
        theTimer.start();
    }

    public void addFood() { //randomly selects a cell to add food to
        Random generator = new Random();
        while (true) {
            int row = generator.nextInt(numRows);
            int column = generator.nextInt(numColumns);
            if (theBoard[row][column].getHasSnake() == false) {
                theBoard[row][column].setHasFood(true);
                foodX = row;
                foodY = column;
                return;
            }
        }
    }

    public void eatFood() {
        if ((theSnake.getHead().getRow() == foodY) && (theSnake.getHead().getColumn() == foodX)) {
            theSnake.expand();
            addFood();
            isExpanding = true;
        }
    }

    public void outOfBounds() {
        if (((theSnake.getHead().getRow() == 19) && (currentDirection == "down"))
         || ((theSnake.getHead().getColumn() == 19) && (currentDirection == "right"))
         || ((theSnake.getHead().getRow() == 0) && (currentDirection == "up"))
         || ((theSnake.getHead().getColumn() == 0) && (currentDirection == "left"))) {
            gameOver = true;
            theTimer.stop();
        } else if (!isExpanding) {
            LinkedList<Cell> body = theSnake.getBody();
            int length = theSnake.getBody().size();
            
            for(int i = 1; i < length; i++) {
                if ((body.get(i).getRow() == body.get(0).getRow()) && (body.get(i).getColumn() == body.get(0).getColumn())) {
                    gameOver = true;
                    theTimer.stop();
                }
            }    
        }
    }

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

        Cell next = theBoard[row][column];
        return next;
    }

    public void move() {
        theSnake.move(getNext(theSnake.getHead(), currentDirection));
        isExpanding = false;
    }

    //For displaying the cells on the GUI
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(numColumns * cellSize, numRows * cellSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int x = i * cellSize;
                int y = j * cellSize;
                g.drawRect(x, y, cellSize, cellSize);
            }
        }

        if (!gameOver) {
            g.setColor(Color.red);
            g.fillOval(foodX * cellSize, foodY * cellSize, cellSize, cellSize);

            Cell head = theSnake.getHead();
            LinkedList<Cell> body = theSnake.getBody();
            int length = theSnake.getBody().size();
            
            for(int i = 0; i < length; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(head.getColumn() * cellSize, head.getRow() * cellSize, cellSize, cellSize);    
                } else if (i % 2 == 0) {
                    g.setColor(new Color(50,180,0));
                    g.fillRect(body.get(i).getColumn() * cellSize, body.get(i).getRow() * cellSize, cellSize, cellSize);    
                } else {
                    g.setColor(new Color(50,220,0));
                    g.fillRect(body.get(i).getColumn() * cellSize, body.get(i).getRow() * cellSize, cellSize, cellSize);    
                }
            }
            g.setColor(Color.darkGray);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            g.drawString("Score: " + (length - 1), 0, 20);
        } else {
            g.setColor(Color.red);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 60));
            g.drawString("Game Over", 60, 80);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 40));
            g.drawString("Press Enter to Replay", 20, 140);

            int length = theSnake.getBody().size() - 1;
            if (length < 10) {
                g.drawString("Score: " + length, 130, 180);
            } else {
                g.drawString("Score: " + length, 120, 180);
            }
            try {
                File file = new File("highScore.txt");
                Scanner input = new Scanner(file);  
                String theHighScore = input.nextLine();
                int highScore = Integer.parseInt(theHighScore);
                
                if (length > highScore) {
                    try {
                        FileWriter output = new FileWriter(file, false);
                        output.write(Integer.toString(length));
                        output.close();
                    } catch (IOException e) {
                        System.out.println("Could not save new high score");
                    }
                    highScore = length;
                }

                g.drawString("High Score: " + highScore, 80, 220);
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("Failed to retrieve the high score");
            }
        }
    }

    public class Input extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if ((keyCode == KeyEvent.VK_LEFT) && (currentDirection != "right")) {
                currentDirection = "left";
            } else if ((keyCode == KeyEvent.VK_DOWN) && (currentDirection != "up")) {
                currentDirection = "down";
            } else if ((keyCode == KeyEvent.VK_RIGHT) && (currentDirection != "left")) {
                currentDirection = "right";
            } else if ((keyCode == KeyEvent.VK_UP) && (currentDirection != "down")) {
                currentDirection = "up";
            } else if ((keyCode == KeyEvent.VK_ENTER) && (gameOver)) {
                gameOver = false;
                run();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg) {
        outOfBounds();
        if (!gameOver) {
            move();
            eatFood();
        }
        repaint();
    }
}