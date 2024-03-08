package main;

import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gp){
        this.gamePanel = gp;
    }

    //set any objects that should exist at the start of the game
    public void setObject(){
        gamePanel.objects.add(new OBJ_Key("Key",4,5,gamePanel));
    }

    //doesn't really make sense to use a map here, since we'll want to add or remove objects throughout the game, not tested
    public void addObject(SuperObject object){}

    //basic idea on how to remove objects, not tested
    public void removeObject(String name){
        gamePanel.objects.stream().filter(object -> object.name.equals(name)).forEach(object -> gamePanel.objects.remove(object));
    }
    public void draw(Graphics2D g2){
        for (SuperObject obj : gamePanel.objects){
            obj.draw(g2, gamePanel);
        }
    }
}
