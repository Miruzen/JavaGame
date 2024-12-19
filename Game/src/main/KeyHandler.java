package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author miruzen
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed , downPressed , leftPressed , rightPressed ;
    boolean checkDrawTime ; 
    
    GamePanel gp ; 
    
    public KeyHandler (GamePanel gp){
        this.gp = gp ;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //TITLE STATE 
        int code = e.getKeyCode();

        if ( gp.gameState == gp.titleState){
            if (gp.ui.titleScreenState == 0 ) {
                
            if (code == KeyEvent.VK_W) {
            gp.ui.commandNum -- ; 
            if ( gp.ui.commandNum < 0 ) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++ ;
            if ( gp.ui.commandNum > 2 ) {
                gp.ui.commandNum = 0 ;
            }
        }
        if ( code == KeyEvent.VK_ENTER){
            if ( gp.ui.commandNum == 0 ) {
                gp.ui.titleScreenState = 1 ; 
            }
            if ( gp.ui.commandNum == 1 ) {
                System.exit(0);
            }
        }
            }
            
           
        else if (gp.ui.titleScreenState == 1 ) {
                
            if (code == KeyEvent.VK_W) {
            gp.ui.commandNum -- ; 
            if ( gp.ui.commandNum < 0 ) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++ ;
            if ( gp.ui.commandNum > 1 ) {
                gp.ui.commandNum = 0 ;
            }
        }
        if ( code == KeyEvent.VK_ENTER){
            if ( gp.ui.commandNum == 0 ) {
                gp.player.getPlayerImage(1);
                gp.ui.titleScreenState = 2;
                
            }
            else if ( gp.ui.commandNum == 1 ) {
                gp.player.getPlayerImage(2);
                gp.ui.titleScreenState = 2;
                
            }
        }
        }
        else if (gp.ui.titleScreenState == 2 ) {
                
            if (code == KeyEvent.VK_W) {
            gp.ui.commandNum -- ; 
            if ( gp.ui.commandNum < 0 ) {
                gp.ui.commandNum = 1;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++ ;
            if ( gp.ui.commandNum > 1 ) {
                gp.ui.commandNum = 0 ;
            }
        }
        if ( code == KeyEvent.VK_ENTER){
            if ( gp.ui.commandNum == 0 ) {
                gp.stopMusic();
                gp.gameState = gp.playState ;
                gp.playMusic(0);
                gp.tileM.loadMap("/maps/World02.txt");
                
            }
            else if ( gp.ui.commandNum == 1 ) {
                gp.stopMusic();
                gp.gameState = gp.playState;
                gp.playMusic(0);
                gp.tileM.loadMap("/maps/Map.txt");                
            }
        }
        }
            
            
        }   
        
        
        //PLAY STATE
        if ( gp.gameState == gp.playState){
        if (code == KeyEvent.VK_W) {
            upPressed = true ; 
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true ; 
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true ; 
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true ; 
        }
        }
        //PAUSE 
        if ( code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState ; 
            }
            else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
    
        
        //DEBUG
        if ( code == KeyEvent.VK_T){
            if(checkDrawTime == false ) {
                checkDrawTime = true  ;
            }
            else if ( checkDrawTime == true ) {
                checkDrawTime = false ; 
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = false ; 
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false ; 
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false ; 
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false ; 
        }
    }
    
    
}
