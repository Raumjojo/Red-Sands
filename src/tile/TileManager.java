package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNumber;
    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.getMaxScreenCol()][gamePanel.getMaxScreenRow()];
        getTileImage();
        loadMap("map02");
    }
    public void getTileImage(){
        //only expand if possible: "background","redSand01","redSand02","waterWaves01","cactus01"
        String[] tileNames = {"background","redSand01","redSand02","waterWaves01","cactus01"};
        //only expand if possible:"cactus01"
        String[] collisionTileNames = {"cactus01"};  // tiles that can be collided with, here with cactus
        int i = 0;
        //for every tile in tileNames, put according image in tiles[], in ascending order of position in tileNames
        try{
            for (String tileName : tileNames){
                String tilePath = "/tiles/"+tileName+".png";
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tilePath)));
                tiles[i] = new Tile().setImage(image);

                boolean tileInCollisionTileNames = Arrays.stream(collisionTileNames).anyMatch(tileName::contains); //interprets the array as a stream of strings, which looks if tileName is in any way contained
                if(tileInCollisionTileNames) //if the tile is indeed contained, its collision gets activated
                {
                    tiles[i].collision = true;
                }

                i++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String mapName){
        String mapPath = "/maps/"+mapName+".txt";
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for (int iRow=0; iRow < gamePanel.getMaxScreenRow(); iRow++){
                String line = bufferedReader.readLine();
                for (int iCol=0; iCol < gamePanel.getMaxScreenCol(); iCol++) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[iCol]);

                    mapTileNumber[iCol][iRow] = num;
                }
            }
            bufferedReader.close();

        }catch(Exception e){
            e.printStackTrace();
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

    public int[][] getMapTileNumber() {return mapTileNumber;}

    public Tile[] getTiles() {return tiles;}
}
