package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTileImage();
    }
    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/redSand01.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/redSand02.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int tileSize = gamePanel.getTileSize();
        for (int i = 0; i < gamePanel.getMaxScreenCol(); i++){
            for (int j = 0; j < gamePanel.getMaxScreenRow(); j++){
                g2.drawImage(tiles[1].image,i*tileSize,j*tileSize,tileSize,tileSize,null);
            }
        }

    }
}
