package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public double xExact, yExact;
    public int speed;
    public BufferedImage neutral, down1, down2;
    public String directionX;
    public String directionY;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int refreshRate;
    public int topOffset;
    public Rectangle solidArea;
    public boolean collisionXOn = false;
    public boolean collisionYOn = false;
    public void setCollisionXOn(boolean collisionXOn){
        this.collisionXOn = collisionXOn;
    }
    public void setCollisionYOn(boolean collisionYOn){
        this.collisionYOn = collisionYOn;
    }


}
