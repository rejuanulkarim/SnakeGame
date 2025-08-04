import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private int dots;
    private Image head;
    private Image dot;
    private Image frog;

    Board(){
        this.setBackground(Color.CYAN);
        this.setFocusable(true); // for frame focus
        this.loadImages();
        this.initGame();

    }
    public void loadImages(){
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/head.png"));
        head= i1.getImage();
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icons/frog.png"));
        frog = i3.getImage();
    }

    public void initGame(){
        dots=3;
        for(int i=0; i<dots; i++){

        }
    }

}
