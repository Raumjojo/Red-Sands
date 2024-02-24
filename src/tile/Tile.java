package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public Tile setImage(BufferedImage image){
        this.image = image;
        return this;
    }

}
