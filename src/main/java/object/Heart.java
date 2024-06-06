package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends SuperObject{
    GamePanel gp;
    public Heart(GamePanel gp){
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/tiles/heart_blank.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
