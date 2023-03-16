import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.sql.RowIdLifetime;

import javax.swing.ImageIcon;

public class Board extends JPanel {
    private Cell[][] theBoard; //the playing surface
    private Snake theSnake;
    private int numRows = 20;
    private int numColumns = 20;
    private int cellSize = 20;
    private int foodX;
    private int foodY;

    private Image head;
    private Image body;
    private Image food;

    //Constructor
    public Board() {
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

    //Methods
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
        int d = 3;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                int x = j * cellSize;
                int y = i * cellSize;
                g.drawRect(x, y, cellSize, cellSize);
            }
        }

        if(true){
            g.setColor(Color.red);
            g.fillOval(foodX, foodY,cellSize,cellSize);
            for(int i=0;i<2;i++){
                if(i==0){
                    g.setColor(Color.green);
                    g.fillRect(20,20,cellSize,cellSize);
                }
                else{
                    g.setColor(new Color(50,180,0));
                    g.fillRect(40,40,cellSize,cellSize);
                }
            }
        //    g.setColor(Color.blue);
        //    g.setFont(new Font("Ink Free",Font.BOLD,40));
        //    FontMetrics font_me=getFontMetrics(g.getFont());
           //g.drawString("Score:" + 0,(S_Width-font_me.stringWidth("Score:"+0))/2, g.getFont().getSize());

        }
    }

}