import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private Board theBoard = new Board();

    public void run() {
        JFrame theWindow = new JFrame();
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.add(theBoard);
        theWindow.pack();
        theWindow.setLocationRelativeTo(null);
        theWindow.setVisible(true);
    }
}
