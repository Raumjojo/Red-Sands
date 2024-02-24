package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        refreshRate = 12;
    }
    public void getPlayerImage(){
        try{
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/playerFront1.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(!(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed))return;

        if (keyHandler.upPressed){
            y -= speed;
            direction = "up";
        } if (keyHandler.downPressed) {
            y += speed;
            direction = "down";
        } if (keyHandler.rightPressed) {
            x -= speed;
            direction = "right";
        } if (keyHandler.leftPressed) {
            x += speed;
            direction = "left";
        }
        //every [refreshRate] frames that the player is moving, the sprite changes to simulate movement
        spriteCounter++;
        if (spriteCounter == refreshRate){
            spriteNum = (spriteNum%2)+1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gamePanel.getTileSize(),gamePanel.getTileSize());
        BufferedImage image = down1;
        switch(direction){
            case "up":
                if (spriteNum == 1){
                    //image = up1;
                    break;
                }
                //image = up2;
                break;
        }
        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
