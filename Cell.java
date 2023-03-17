public class Cell {
    private int column; //stores column position
    private int row; //stores row position
    private boolean hasFood = false; //if cell has food on it
    private boolean hasSnake = false; //if the snake is on the cell

    //Constructor
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //Getter and Setter methods
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    public boolean getHasFood() {
        return hasFood;
    }

    public void setHasSnake(boolean hasSnake) {
        this.hasSnake = hasSnake;
    }

    public boolean getHasSnake() {
        return hasSnake;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
