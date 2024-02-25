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

        solidArea = new Rectangle(9,0, gamePanel.getTileSize()-18,gamePanel.getTileSize()); //collisionBox of the Player

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        xExact = 100;
        yExact = 100;
        speed = 4;
        directionX = "neutral";
        directionY = "down";
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
            directionY = "up";
        }else if (keyHandler.downPressed) {
            directionY = "down";
        } else { directionY = "neutral";}
        if (keyHandler.rightPressed) {
            directionX = "right";
        } else if (keyHandler.leftPressed) {
            directionX = "left";
        } else{ directionX = "neutral";}

        // CHECK TILE COLLSION
        this.setCollisionXOn(false);
        gamePanel.getCollisionChecker().checkTile(this, directionX);
        this.setCollisionYOn(false);
        gamePanel.getCollisionChecker().checkTile(this,directionY);

        double speedExact = speed;
        if (!directionX.equals("neutral") && !directionY.equals("neutral")){ //so player wants to move vertically
            if (!collisionYOn || !collisionXOn) { //so player actually can move vertically
                speedExact = speedExact / Math.sqrt(2);
            }
        }
        //IF COLLISION IS FALSE PLAYER CAN MOVE
        if(!collisionYOn) {
            if (directionY.equals("up")) { yExact -= speedExact; }
            else if (directionY.equals("down")) { yExact += speedExact; }
        }
        if(!collisionXOn){
            if (directionX.equals("left")) { xExact -= speedExact; }
            else if (directionX.equals("right")) { xExact += speedExact; }
        }

        //cast position as int, still saving the exact location
        x = (int) xExact;
        y = (int) yExact;

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
        switch(directionX){
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
