package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNumber[][];
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.getMaxScreenCol()][gamePanel.getMaxScreenCol()];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/redSand01.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/redSand02.png")));

            //additionally water, more or less
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/waterWaves01.png")));

            //added cactus01
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/cactus01.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){

        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for (int iRow=0; iRow < gamePanel.getMaxScreenRow(); iRow++){
                String line = bufferedReader.readLine();
                for (int iCol=0; iCol < gamePanel.getMaxScreenCol(); iCol++) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[iCol]);

                    mapTileNumber[iCol][iRow] = num;
                }
            }
            bufferedReader.close();

        }catch(Exception e){

        }

    }
    public void draw(Graphics2D g2){
        int tileSize = gamePanel.getTileSize();
        for (int iCol = 0; iCol < gamePanel.getMaxScreenCol(); iCol++){
            for (int iRow = 0; iRow < gamePanel.getMaxScreenRow(); iRow++){
                int tileNum = mapTileNumber[iCol][iRow];
                g2.drawImage(tiles[tileNum].image,iCol*tileSize,iRow*tileSize,tileSize,tileSize,null);
            }
        }
    }
}
