import javax.swing.*;

public class SnakeGame extends JFrame {
    SnakeGame(){
        super("SnakeGame");
        add(new Board()); //frame or board to in work
        pack(); // for frame visible and continue refresh
        setSize(500,500); // for frame shape
        setLocationRelativeTo(null); // for frame center
        //setLocation(500,300);
        setVisible(true); // for Frame
    }
    public static void main(String[] args) {
        new SnakeGame(); // main method constructor
    }
}
