package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    public int worldX, worldY;
    public int speed;
    public BufferedImage down1, down2, up1, up2, right1, left1, right2, left2;
    public BufferedImage down_idle_1, down_idle_2, up_idle_1, up_idle_2, right_idle_1, left_idle_1, right_idle_2, left_idle_2;
    public String direction;
    public int spriteCounter;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int maxLife;
    public int life;
}