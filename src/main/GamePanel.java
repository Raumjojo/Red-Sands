package main;

import entity.Player;
import main.KeyHandler;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;//16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize *scale;// 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS =60;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyHandler);
    TileManager tileManager = new TileManager(this);

    //set Player´s default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //improves rendering performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
    }
    /*
    @Override
    public void run() {
        while(gameThread != null){

            double drawInterval = 1000000000/FPS; //0.01666 s
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // converting nanos into millis;

                if(remainingTime<0){
                    remainingTime =0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime +=drawInterval; // idk if this is needed, but he used it in his video

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    */
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread !=null){

            currentTime = System.nanoTime();

            delta += (currentTime -lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
    public int getTileSize(){
        return tileSize;
    }
    public int getMaxScreenCol(){
        return maxScreenCol;
    }
    public int getMaxScreenRow(){
        return maxScreenRow;
    }
}
