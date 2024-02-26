package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity, String direction){
        int entityLeftX = entity.x +entity.solidArea.x;
        int entityRightX = entity.x +entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y +entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gamePanel.tileSize;
        int entityRightCol = entityRightX/gamePanel.tileSize;
        int entityTopRow = entityTopY/gamePanel.tileSize;
        int entityBottomRow = entityBottomY/gamePanel.tileSize;

        int tileNum1,tileNum2;
        int newPosition;
        int tileSize = gamePanel.tileSize;

        switch (direction){
            //if entity would leave map bounds, set collision to on and return
            case "up":
                newPosition = entityTopY - entity.speed
                    -entity.topOffset;//to avoid the head going out of the screen
                if (newPosition < 0){
                    entity.setCollisionYOn(true);
                    return;
                }
                entityTopRow = newPosition /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.setCollisionYOn(true);
                }
                break;
            case "down":
                newPosition = entityBottomY + entity.speed;
                if (newPosition >= gamePanel.maxScreenRow * tileSize){
                    entity.setCollisionYOn(true);
                    return;
                }
                entityBottomRow = newPosition /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.setCollisionYOn(true);
                }
                break;
            case "left":
                newPosition = entityLeftX - entity.speed;
                if (newPosition < 0){
                    entity.setCollisionXOn(true);
                    return;
                }
                entityLeftCol = newPosition /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.setCollisionXOn(true);
                }
                break;
            case "right":
                newPosition = entityRightX + entity.speed;
                if (newPosition >= gamePanel.getMaxScreenCol() * tileSize){
                    entity.setCollisionXOn(true);
                    return;
                }
                entityRightCol = newPosition /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.setCollisionXOn(true);
                }
                break;
        }
    }
}
