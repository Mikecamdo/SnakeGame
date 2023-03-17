import javax.swing.JFrame;
public class Game extends JFrame {
    Game() {
        this.add(new Board());
        // theBoard.loadImages();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        int p = 0;
    }
}
