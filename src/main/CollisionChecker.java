package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        int entityLeftX = entity.x +entity.solidArea.x;
        int entityRightX = entity.x +entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y +entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX/gamePanel.tileSize;
        int entityRightCol = entityRightX/gamePanel.tileSize;
        int entityTopRow = entityTopY/gamePanel.tileSize;
        int entityBottomRow = entityBottomY/gamePanel.tileSize;

        int tileNum1,tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed) /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) /gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.getTiles()[tileNum1].collision || gamePanel.tileManager.getTiles()[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
