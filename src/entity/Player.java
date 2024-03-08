package entity;
import main.GamePanel;
import main.KeyHandler;
import util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        int xOffset = 9;
        topOffset = 10;
        solidArea = new Rectangle(xOffset,topOffset, gamePanel.getTileSize()-2*xOffset,gamePanel.getTileSize()-topOffset); //collisionBox of the Player

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        xExact = x;
        yExact = y;
        speed = 4;
        topOffset = 6;
        directionX = "neutral";
        directionY = "down";
        refreshRate = 15;
    }
    public void getPlayerImage(){
        neutral = ImageUtil.getImage("/player/playerFront1.png", this);
        down1 = ImageUtil.getImage("/player/playerFront01.png", this);
        down2 = ImageUtil.getImage("/player/playerFront02.png", this);
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

        // CHECK TILE COLLISION
        this.setCollisionXOn(false);
        gamePanel.getCollisionChecker().checkTile(this, directionX);
        this.setCollisionYOn(false);
        gamePanel.getCollisionChecker().checkTile(this,directionY);

        double speedExact = speed;
        if (!directionX.equals("neutral") && !directionY.equals("neutral")){ //so player wants to move vertically
            if (!collisionYOn && !collisionXOn) { //so player actually can move vertically
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
            //System.out.println("spriteNum: " + spriteNum);
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = neutral;
        if(directionX.equals("left")) {
            if (spriteNum == 1) {
                //image = left1;
            } else {
                //image = left2;
            }
        }
        if (directionX.equals("right")) {
            if (spriteNum == 1) {
                //image = right1;
            } else {
                //image = right2;
            }
        }
        if (directionY.equals("up")){
            if (spriteNum == 1){
                //image = up1;
            } else {
                //image = up2;
            }
        }
        if (directionY.equals("down")){ //fuck knows why tf this doesn't work
            if (spriteNum == 1){
                image = down1;
            } else {
                image = down2;
            }
        }
        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
