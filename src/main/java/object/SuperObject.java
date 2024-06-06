package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D g, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.hero.worldX + gamePanel.hero.screenX;
        int screenY = worldY - gamePanel.hero.worldY + gamePanel.hero.screenY;
        if(worldX+ gamePanel.tileSize > gamePanel.hero.worldX-gamePanel.hero.screenX && worldX - gamePanel.tileSize <gamePanel.hero.worldX+gamePanel.hero.screenX && worldY+gamePanel.tileSize > gamePanel.hero.worldY-gamePanel.hero.screenY && worldY - gamePanel.tileSize<gamePanel.hero.worldY+gamePanel.hero.screenY){
            g.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
