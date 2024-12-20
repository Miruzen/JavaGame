package main;


import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author miruzen
 */
public class GamePanel extends JPanel implements Runnable 
{
    //SCREEN SETTINGS 
    final int originalTileSize = 16 ; //16x16 tile 
    final int scale = 3 ; 
    
    public final int tileSize = originalTileSize *scale; //48x48 tile 
    public final int maxScreenCol = 16 ; 
    public final int maxScreenRow = 12 ;
    public final int screenWidth = tileSize * maxScreenCol ; 
    public final int screenHeight = tileSize * maxScreenRow ; 
    
    //WORLD SETTINGS 
    public final int maxWorldCol = 50; 
    public final int maxWorldRow = 50 ; 
    
    //FPS
    int FPS = 60 ; 
    
    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this) ; 
    Sound se = new Sound() ; 
    Sound music = new Sound() ; 
    Thread gameThread ; 
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this) ;
    public AssetSetter aSetter = new AssetSetter(this);
    
    //GAME STATE 
    public int gameState ; 
    public final int titleState = 0 ; 
    public final int playState = 1 ; 
    public final int pauseState = 2 ; 
    
    //ENETITY AND OBJECT
    public Player player = new Player(this,keyH) ; 
    public SuperObject obj[] = new SuperObject[10];
    
    
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.black) ;
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void setupGame() {
        aSetter.setObject();
        
        if ( gameState == titleState) {
            playMusic(5);
        } 
        else if ( gameState == playState) {
                
        }
        
    }
    
    public void startGameThread() {
        
        gameThread = new Thread(this); 
        gameThread.start() ; 
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS ;
        double delta = 0 ; 
        long lastTime = System.nanoTime();
        long currentTime ; 
        long timer = 0 ; 
        int drawCount = 0 ; 
        
        while(gameThread != null ) {
            
            currentTime = System.nanoTime() ; 
            
            delta += (currentTime - lastTime) / drawInterval ;
            timer += (currentTime - lastTime) ; 
            lastTime = currentTime ; 
            
            if ( delta >= 1 ) 
            {
                update() ; 
                repaint() ; 
                delta--;
                drawCount++ ; 
            }
            
            if(timer >= 1000000000) {
                
                drawCount = 0 ; 
                timer = 0 ; 
            }
        }
    }
    
    public void update () {
        
        if (gameState == playState){
            player.update();
        }
        if (gameState == pauseState){
            
        }
    }
    
    @Override
    public void paintComponent (Graphics g ) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g ;
        
        //DEBUG 
        long drawStart = 0 ; 
        if ( keyH.checkDrawTime == true) {
            drawStart = System.nanoTime() ;

        }    
        //TITLE SCREEN 
        if ( gameState == titleState ) {
            ui.draw(g2);
        }
        //OTHERS 
        else {
            
            //TILE
        tileM.draw(g2);
        
        //OBJECT 
        for ( int i = 0 ; i < obj.length ; i++ ){
            if ( obj[i] != null ) {
                obj[i].draw(g2, this);
            }
        }
        
        //PLAYER
        player.draw(g2); 
        
        //UI
        ui.draw(g2);
        }
        
        
        
        //DEBUG
        if ( keyH.checkDrawTime == true ) {
            long drawEnd = System.nanoTime() ; 
            long passed = drawEnd - drawStart ;        
            g2.setColor(Color.white);
            g2.drawString("Draw Time : "+ passed , 10, 400);
            System.out.println("DrawTime : "+ passed) ; 
        }
        
        
        
        g2.dispose();
    }
    
    public void playMusic(int i ) {
        
        music.setFile(i);
        music.play();
        music.loop();
        
    }
    
    public void stopMusic() {
        
        music.stop(); 
    }
    
    public void playSE(int i ) {
        
        se.setFile(i);
        se.play(); 
        
    }
    
    
    
}
