import java.util.LinkedList;

public class Snake {
    private Cell head;
    private LinkedList<Cell> body = new LinkedList<>();

    //Constructor
    public Snake(Cell startingPosition) {
        this.head = startingPosition;
        body.add(head);
        head.setHasFood(true);
    }

    //Getter and Setter methods
    public void setHead(Cell head) {
        this.head = head;
    }

    public Cell getHead() {
        return head;
    }

    public void setBody(LinkedList<Cell> body) {
        this.body = body;
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    //Methods
    public void expand() {
        body.add(head);
    }

    public void move(Cell next) {
        Cell end = body.removeLast();
        end.setHasSnake(false);

        head = next;
        head.setHasSnake(true);
        body.addFirst(head);
    }

}
