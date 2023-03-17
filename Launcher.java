import javax.swing.JFrame;
public class Launcher {
    public static void main(String[] args) {
        Board newGame = new Board();
        JFrame theWindow = new JFrame();
        // theBoard.loadImages();

        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.add(newGame);
        theWindow.pack();
        theWindow.setResizable(false);
        theWindow.setLocationRelativeTo(null);
        theWindow.setVisible(true);

        newGame.run();

    }
}
