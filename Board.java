import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.sql.RowIdLifetime;
import javax.swing.ImageIcon;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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

    private Image head;
    private Image body;
    private Image food;

    //Constructor
    public Board() {
        this.addKeyListener(new Input());
        theBoard = new Cell[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                theBoard[i][j] = new Cell(i, j);
            }
        }
        Cell start = new Cell(0 ,0);
        theSnake = new Snake(start);
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
                System.out.printf("Food generated at: %d, %d\n", foodX, foodY);
                return;
            }
        }
    }

    public Cell getNext(Cell current, String direction) {
        int row = current.getRow();
        int column = current.getColumn();
        //
        // System.out.printf("Row: %d\n", row);
        // System.out.printf("Column: %d\n", column);
        System.out.printf("Direction: %s\n", direction);
        System.out.printf("Before: %d, %d\n", row, column);

        if (direction == "left") {
            column -= 1;
        } else if (direction == "down") {
            row += 1;
        } else if (direction == "right") {
            column += 1;
        } else if (direction == "up") {
            row -= 1;
        }
        System.out.printf("After: %d, %d\n", row, column);

        Cell next = theBoard[row][column];
        System.out.printf("After After: %d, %d\n", next.getRow(), next.getColumn());
        return next;
    }

    public void move() {
        // System.out.println("MOVING");
        theSnake.move(getNext(theSnake.getHead(), currentDirection));
    }

    // public void loadImages() {
    //     ImageIcon theHead = new ImageIcon("C:\\Users\\mikec_g1kgiu8\\OneDrive\\Desktop\\Personal Projects\\SnakeGame\\Sprites\\Head.png");
    //     ImageIcon theBody = new ImageIcon("C:\\Users\\mikec_g1kgiu8\\OneDrive\\Desktop\\Personal Projects\\SnakeGame\\Sprites\\Body.png");
    //     ImageIcon theFood = new ImageIcon("C:\\Users\\mikec_g1kgiu8\\OneDrive\\Desktop\\Personal Projects\\SnakeGame\\Sprites\\Food.png");

    //     head = theHead.getImage();
    //     body = theBody.getImage();
    //     food = theFood.getImage();
    // }

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
            // foodX = 10;
            // foodY = 10;
            //System.out.printf("Printing food at: %d, %d\n", foodX, foodY);
            g.fillOval(foodX * cellSize, foodY * cellSize, cellSize, cellSize);

            Cell head = theSnake.getHead();
            LinkedList<Cell> body = theSnake.getBody();
            int length = theSnake.getBody().size();

            g.setColor(Color.green);
            g.fillRect(head.getColumn() * cellSize, head.getRow() * cellSize, cellSize, cellSize);
            
            for(int i = 0; i < length; i++) {
                g.setColor(new Color(50,180,0));
                g.fillRect(body.get(i).getColumn() * cellSize, body.get(i).getRow() * cellSize, cellSize, cellSize);
            }
        //    g.setColor(Color.blue);
        //    g.setFont(new Font("Ink Free",Font.BOLD,40));
        //    FontMetrics font_me=getFontMetrics(g.getFont());
           //g.drawString("Score:" + 0,(S_Width-font_me.stringWidth("Score:"+0))/2, g.getFont().getSize());

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

    @Override
    public void actionPerformed(ActionEvent arg) {
        if (!gameOver) {
            move();
        }
        System.out.printf("Snake at: %d, %d\n", theSnake.getHead().getRow(), theSnake.getHead().getColumn());
        repaint();
    }

}