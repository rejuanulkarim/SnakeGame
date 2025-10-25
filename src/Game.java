import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {


    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 20;
    //snake
    Tile snakeHead;
    // SnakeBody
    ArrayList<Tile> snakeBody;

    //food
    Tile food;
    Random random;
    //
    Timer gameLoop;
    int  velocityX ;
    int velocityY ;
    boolean gameOver= false;

    //wall collision detection flag
    boolean wallCollied ;

    Game(int boardWidth, int boardHeight, int delay,boolean wallCollied) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.black);
        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();
        for(int i=0; i<3;i++){
            snakeBody.add(new Tile(snakeHead.x,snakeHead.y));
        }
        food = new Tile(1, 1);
        random = new Random();
        placeFood();
        velocityX =1;
        velocityY =0;
        gameLoop = new Timer(100,this);
        gameLoop.setDelay(delay);
        gameLoop.start();
        addKeyListener(this);
        setFocusable(true);
        //wall collision detection flag
        this.wallCollied = wallCollied;
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //grid line
        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }


        //food
        g.setColor(Color.red);
        g.fillOval(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //snake Head
        g.setColor(Color.green);
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize,true);


        //snake body
        for (int i=0; i<snakeBody.size();i++){
            g.setColor(Color.yellow);
            g.fill3DRect(snakeBody.get(i).x*tileSize, snakeBody.get(i).y*tileSize,tileSize,tileSize,true);
        }

    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
        }
    }


    public void move(){
        //eat food
        if(collision(food,snakeHead)){
            snakeBody.add(new Tile(snakeHead.x,snakeHead.y));
            placeFood();
        }

//        Snake Body
        for(int i=snakeBody.size()-1;i>=0;i--){
            Tile snakePart= snakeBody.get(i);

            if(i==0){
                snakePart.x=snakeHead.x;
                snakePart.y=snakeHead.y;
            }else{
                Tile preSnakePart= snakeBody.get(i-1);
                snakePart.x=preSnakePart.x;
                snakePart.y= preSnakePart.y;
            }
        }



        //snake Head move;

//        snakeHead.x+=velocityX;
//        snakeHead.y+=velocityY;}

        if(snakeHead.x<0){
            snakeHead.x=boardWidth/tileSize-1;
        }else{
        snakeHead.x=((snakeHead.x+velocityX)%(boardWidth/tileSize));}
        if(snakeHead.y<0){
            snakeHead.y=boardWidth/tileSize-1;
        }else{
        snakeHead.y= ((snakeHead.y+velocityY)%(boardWidth/tileSize));}


        //Game over condition;
        for(int i=0; i<snakeBody.size();i++){

            //collide with the snake head
            if(collision(snakeHead,snakeBody.get(i))){
                gameOver=true;
            }
            // collide with wall
            if(wallCollied){
                if(snakeHead.x*tileSize<0||snakeHead.x*tileSize>=boardWidth||
                        snakeHead.y*tileSize<0||snakeHead.y*tileSize>=boardHeight){
                    gameOver=true;
                }
            }
        }

        // game over
        if(gameOver){
            System.out.println("Game Over");
        }
    }



    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
     if((e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W) && velocityY!=1){
         velocityX=0;
         velocityY=-1;
     } else if((e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S) && velocityY!= -1){
         velocityX=0;
         velocityY=1;
     } else if((e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A) && velocityX!= 1){
            velocityX=-1;
            velocityY=0;
        }else if((e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D) && velocityX!= -1){
            velocityX=1;
            velocityY=0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}