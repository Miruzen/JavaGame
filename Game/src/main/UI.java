/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

/**
 *
 * @author miruzen
 */
public class UI  {
    
    GamePanel gp ; 
    Graphics2D g2;
    Font arial_40 , arial_80; 
    BufferedImage keyImage;
    public boolean messageOn = false ; 
    public String message = "";
    int messageCounter = 0 ; 
    public boolean gameFinished = false ; 
    public int commandNum = 0 ;
    public int titleScreenState = 0; // 0: 
    
    double playTime ; 
    DecimalFormat dFormat = new DecimalFormat("#0.00") ; 
    
    
    public UI(GamePanel gp ) {
                    
        this.gp = gp ; 
        arial_40 = new Font("Arial" , Font.PLAIN , 40 );
        arial_80 = new Font("Arial" , Font.BOLD , 80 );

    }
    
    public void showMessage(String text) {
        
        message = text; 
        messageOn = true ; 
    }
    public void draw ( Graphics2D g2 ) {
        this.g2 =g2 ;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80));
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        //TITLE STATE 
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen() ;
        }
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
            
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        //PAUSE STATE   
        }
        if ( gameFinished == true ) {
            
            g2.setFont(arial_40) ;
            g2.setColor(Color.white) ;
            
            String text ; 
            int textLength ; 
            int x ; 
            int y ; 
            
            text = "You found the treasure ! " ; 
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() ;
            x = gp.screenWidth/2 - textLength/2; 
            y = gp.screenHeight/2 - (gp.tileSize*3) ;
            g2.drawString(text, x, y);
            
            text = "You Time is :" + dFormat.format(playTime) + "!"; 
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() ;
            x = gp.screenWidth/2 - textLength/2; 
            y = gp.screenHeight/2 + (gp.tileSize*4) ;
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = "Congrats!!! " ; 
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth() ;
            x = gp.screenWidth/2 - textLength/2; 
            y = gp.screenHeight/2 + (gp.tileSize*2) ;
            g2.drawString(text, x, y);
            
            gp.gameThread = null ; 
            
            
                
            
        }
        else {
           
           
        
            //TIME 
            if(gp.gameState == gp.playState){
                
                playTime += (double)1/60 ;
                
                // TIME 
                g2.drawString("Time : "+ dFormat.format(playTime), gp.tileSize*11, 65);
                
                 g2.setFont(arial_40) ;
                 g2.setColor(Color.white) ;
                 g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 , gp.tileSize , gp.tileSize , null) ;
                 g2.drawString("x" + gp.player.hasKey, 74, 65);
                 
                 OBJ_Key key = new OBJ_Key(gp) ;
                 keyImage = key.image; 
            }
            else if ( gp.gameState == gp.pauseState){
                playTime += 0 ;
            }
            
           
            //MESSAGE 
            if( messageOn == true ) {
            
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
            
            messageCounter++ ; 
            
            if(messageCounter > 120 ) {
                messageCounter = 0 ; 
                messageOn = false ; 
            }
        }
        }
       

    }
    public void drawTitleScreen() {
        
        
        if ( titleScreenState == 0 ) {
        g2.setColor(new Color(20,20,20));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //TITLE NAME 
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Treasure Hunt" ;
        int x = getXforCenteredText(text) ;
        int y = gp.tileSize*3;
        
        //SHADOW 
        g2.setColor(Color.GRAY);
        g2.drawString(text, x+5, y+5);
        
        //MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text , x , y ) ;
        
        //PLAYER IMAGE 
        x = gp.screenWidth/2 - (gp.tileSize*2)/1 ; //CENTER POINT 
        y += gp.tileSize/2  ;
        g2.drawImage(gp.player.down1, x/2, y,gp.tileSize*2 , gp.tileSize*2, null );
        g2.drawImage(gp.player.down2, x*2, y , gp.tileSize*2,gp.tileSize*2,null);
        
        //MENU
        g2.setFont((g2.getFont().deriveFont(Font.BOLD , 48F )));
        
        text = " PLAY " ;
        x = getXforCenteredText(text);
        y += gp.tileSize * 4 ;
        g2.drawString(text, x, y);
        if ( commandNum == 0 ) {
            g2.drawString(">", x-gp.tileSize, y);
        }
        
        text = " QUIT " ;
        x = getXforCenteredText(text);
        y += gp.tileSize * 1.5 ;
        g2.drawString(text, x, y);
        if ( commandNum == 1 ) {
            g2.drawString(">", x-gp.tileSize, y);
        }
        }
        else if ( titleScreenState == 1 ) {
            
            //MAP SELECTION 
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = " Select your Character! " ;
            int x = getXforCenteredText(text) ;
            int y = gp.tileSize * 3 ;
            g2.drawString(text, x, y);
            
            text = " Mega-Man " ;
            x = getXforCenteredText(text) ;
            y += gp.tileSize*3 ; 
            
            g2.drawString(text, x, y);
            
            if(commandNum == 0 ) {
                g2.drawString(">", x-gp.tileSize, y);
                g2.drawImage(gp.player.down1, x*2 -40 , y-80 ,gp.tileSize*2 , gp.tileSize*2, null );
            }
            
            text = "She-Hulk " ;
            x = getXforCenteredText(text) ;
            y += gp.tileSize*1.5 ; 
            g2.drawString(text, x, y);
            
            if(commandNum == 1 ) {
                g2.drawString(">", x-gp.tileSize, y);
                g2.drawImage(gp.player.down2, x*2 - 90 , y - 40,gp.tileSize*2 , gp.tileSize*2, null );
                
            }
            
        }
        else if ( titleScreenState == 2 ) {
            
            //MAP SELECTION 
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));
            
            String text = " Select your Map! " ;
            int x = getXforCenteredText(text) ;
            int y = gp.tileSize * 3 ;
            g2.drawString(text, x, y);
            
            text = " Map 1 " ;
            x = getXforCenteredText(text) ;
            y += gp.tileSize*3 ; 
            g2.drawString(text, x, y);
            if(commandNum == 0 ) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = " Map 2 (Beta) " ;
            x = getXforCenteredText(text) ;
            y += gp.tileSize*1.5 ; 
            g2.drawString(text, x, y);
            if(commandNum == 1 ) {
                g2.drawString(">", x-gp.tileSize, y);
            }
            
        }
    }
    public void drawPauseScreen(){
        
        String text = "PAUSED";
        int x = getXforCenteredText(text); 
        int y = gp.screenHeight/2 ; 
        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2 ;
        return x ;
    }
}
