//package monster;
//
//import entity.Character;
//import main.GamePanel;
//
//import javax.imageio.ImageIO;
//import java.io.IOException;
//
//public class Slime extends Character {
//    public Slime(GamePanel gp){
//        super(gp);
//
//        name = "Slime";
//        speed = 1;
//        maxLife=4;
//        life = maxLife;
//
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidArea.width = 42;
//        solidArea.height = 30;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
//    }
//    public void getImage() {
//        try {
//            up1 = ImageIO.read(getClass().getResourceAsStream("/tiles/greenslime_down_1.png"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/tiles/greenslime_down_2.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
