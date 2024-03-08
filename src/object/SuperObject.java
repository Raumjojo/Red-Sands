package object;

import main.GamePanel;
import util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public SuperObject(){};
    public SuperObject(String name, int x, int y, String path, GamePanel gamePanel){
        this.name = name;
        this.x = x * gamePanel.getTileSize();
        this.y = y * gamePanel.getTileSize();
        this.image = ImageUtil.getImage(path,this);
    }
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public void draw(Graphics2D g2, GamePanel gamePanel){
        g2.drawImage(image,x,y,gamePanel.getTileSize(),gamePanel.getTileSize(),null);
    }
}
