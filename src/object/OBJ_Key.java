package object;

import main.GamePanel;
import util.ImageUtil;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(String name, int col, int row, GamePanel gamePanel){
        x = col * gamePanel.getTileSize();
        y = row * gamePanel.getTileSize();
        this.name = name;
        image = ImageUtil.getImage("/objects/key.png",this);
    }
}
