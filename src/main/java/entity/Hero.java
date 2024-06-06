package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Hero extends Character{
    GamePanel gp;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey;
    public Hero(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
        direction="right";
    }
    public void setDefaultValues(){
        worldX= gp.tileSize*23;
        worldY= gp.tileSize*21;
        speed=4;
        maxLife=6;
        life=maxLife;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collisionOn = true;

    }

    public void getPlayerImage(){
        try{
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void move(){
        if(keyHandler.right || keyHandler.left || keyHandler.up || keyHandler.down){
            if(keyHandler.right){
                direction = "right";
            }else if(keyHandler.left){
                direction = "left";
            }else if(keyHandler.up){
                direction = "up";
            }else if(keyHandler.down){
                direction = "down";
            }

            collisionOn = false;
            gp.collisionChecker.checkCollisionTile(this);

            int index = gp.collisionChecker.checkObject(this, true);
            pickUpObject(index);
            if(!collisionOn){
                switch (direction){
                    case "up":
                        worldY-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                }
            }
            spriteCounter++;

            if (spriteCounter > 12) {
                if(spriteNum==1){
                    spriteNum=2;
                }else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }

        }
    }

    public void pickUpObject(int index){
        if(index!=999){
            String objectName = gp.obj[index].name;
            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[index]=null;
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSE(3);
                        gp.obj[index]=null;
                        hasKey--;
                    }
                    break;
                case "Boot":
                    gp.playSE(2);
                    speed+=3;
                    gp.obj[index]=null;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2d){
        BufferedImage image = null;
        switch(direction){
            case "right":
                if(spriteNum==1) image = right1;
                if(spriteNum==2) image = right2;
                break;
            case "left":
                if(spriteNum==1) image = left1;
                if(spriteNum==2) image = left2;
                break;
            case "up":
                if(spriteNum==1) image = up1;
                if(spriteNum==2) image = up2;
                break;
            case "down":
                if(spriteNum==1) image = down1;
                if(spriteNum==2) image = down2;
                break;
        }
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
