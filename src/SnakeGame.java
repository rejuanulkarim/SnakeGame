import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    SnakeGame() {
        super("SnakeGame");

        int boardWidth = 500;
        int boardHeight = boardWidth;

        // Set up frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Add the game panel (assuming Game extends JPanel and handles the game)
        add(new Game(boardWidth, boardHeight));
        pack();  // Resize frame to fit the preferred size of its components

        setLocationRelativeTo(null); // Center the window
        setVisible(true);            // Make frame visible (AFTER adding components)

    }
    public static void main(String[] args) {
        new SnakeGame();                        // main method constructor


//        JFrame frame = new JFrame(" SnakeGame");
//
//        frame.setSize(500,500);
//        frame.setLocationRelativeTo(null);
//        //frame.add(new Game());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        JPanel panel= new JPanel();
////        panel.setBackground(Color.CYAN);
////        frame.add(panel);
//        frame.setVisible(true);
    }
}
