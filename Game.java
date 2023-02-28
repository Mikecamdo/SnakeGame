import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {
    private Board theBoard = new Board();

    class BoardPanel extends JPanel {
        private static final int ROWS = 20;
        private static final int COLUMNS = 20;
        private int sqSize = 20;

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(COLUMNS * sqSize, ROWS * sqSize);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    int x = j * sqSize;
                    int y = i * sqSize;
                    g.drawRect(x, y, sqSize, sqSize);

                }
            }

        }
    }

    public void run() {
        JFrame theWindow = new JFrame();
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.add(theBoard);
        theWindow.pack();
        theWindow.setLocationRelativeTo(null);
        theWindow.setVisible(true);
    }
}
