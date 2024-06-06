package main;

import object.Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    BufferedImage heart_full, heart_blank, heart_half;
    GamePanel gp;
    Graphics2D g2;
    public UI(GamePanel gamePanel) {
        this.gp=gamePanel;
        SuperObject heart = new Heart(gp);
        heart_full=heart.image;
        heart_blank=heart.image3;
        heart_half=heart.image2;
    }
    public void draw(Graphics2D g) {
        this.g2 = g;
        if(gp.gameState== gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState== gp.pauseState){
            drawPlayerLife();
        }
    }
    public void drawPlayerLife() {
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i = 0;
        while (i< gp.hero.maxLife/2) {
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;
        while (i< gp.hero.life) {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i<gp.hero.life) {
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }
    }
}
