package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNumber;
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumber = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResource("/tiles/Grass/tile067.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResource("/tiles/Wall/wall.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResource("/tiles/Water/water02.png"));
            tiles[2].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResource("/tiles/Tree/treefinal.png"));
            tiles[4].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResource("/tiles/sand.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResource("/tiles/earth.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String path){
        try{
            InputStream stream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            int col = 0;
            int row = 0;
            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldCol){
                String line = reader.readLine();

                while(col < gamePanel.maxWorldCol){
                    String numbers[]=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col==gamePanel.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol<gamePanel.maxWorldCol && worldRow<gamePanel.maxWorldRow){
            int number = mapTileNumber[worldCol][worldRow];
            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX;
            int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
            if(worldX+ gamePanel.tileSize > gamePanel.hero.worldX-gamePanel.hero.screenX && worldX - gamePanel.tileSize <gamePanel.hero.worldX+gamePanel.hero.screenX && worldY+gamePanel.tileSize > gamePanel.hero.worldY-gamePanel.hero.screenY && worldY - gamePanel.tileSize<gamePanel.hero.worldY+gamePanel.hero.screenY){
                g.drawImage(tiles[number].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldCol++;
            if(worldCol==gamePanel.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
