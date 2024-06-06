package main;

import entity.Hero;
import object.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize=16;
    final int scale = 3;

    public final int tileSize=originalTileSize*scale;
    public final int widthCol=16;
    public final int heightCol=12;
    public final int screenWidth=widthCol*tileSize;
    public final int screenHeight=heightCol*tileSize;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    final int fps = 60;
    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;

    public Hero hero = new Hero(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];

    Sound sound = new Sound();

    UI ui = new UI(this);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setAssets();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /fps; //how many times we want to draw the screen in one second
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta = 0;
            }
        }
    }

    public void update(){
        if(gameState == playState){
            hero.move();
        }
        if(gameState == pauseState){
            //nothing
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);
        for(SuperObject obj: obj){
            if(obj != null){
                obj.draw(g2d, this);
            }
        }
        hero.draw(g2d);
        ui.draw(g2d);
        g2d.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
