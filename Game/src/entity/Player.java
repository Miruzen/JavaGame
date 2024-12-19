
package entity;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler ;
import main.UtilityTool;



public final class Player extends Entity {
    
    GamePanel gp ; 
    KeyHandler keyH;
    
    public final int screenX ;
    public final int screenY ;
    public int hasKey = 0 ; 
    
    public Player(GamePanel gp , KeyHandler keyH ) {
        
        this.gp = gp ; 
        this.keyH = keyH ; 
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle() ;
        solidArea.x = 8 ; 
        solidArea.y = 16 ;
        solidAreaDefaultX = solidArea.x ; 
        solidAreaDefaultY = solidArea.y ; 
        solidArea.width = 32 ; 
        solidArea.height = 32 ; 
        
        setDefaultValues() ;
        getPlayerImage(1);
        down1 = setup("boy_down_1") ;
        down2 = setup("boy_down") ;
        
    }
    
    //CHANGE X Y FOR SPAWN POINT
    public void setDefaultValues() {  
        worldX=gp.tileSize*23 ; 
        worldY=gp.tileSize*21 ; 
        speed = 4 ; 
        direction = "down" ;           
    }
    
    public void getPlayerImage(int a) {
        
        if ( a == 1 ){
        up1 = setup("boy_up_1") ;
        up2 = setup("boy_up_2") ;
        down1 = setup("boy_down_1") ;
        down2 = setup("boy_down_2") ;
        left1 = setup("boy_left_1") ;
        left2 = setup("boy_left_2") ;
        right1 = setup("boy_right_1") ;
        right2 = setup("boy_right_2") ;
        }
        else if ( a == 2){
        up1 = setup("boy_up") ;
        up2 = setup("boy_up_") ;
        down1 = setup("boy_down") ;
        down2 = setup("boy_down_") ;
        left1 = setup("boy_left") ;
        left2 = setup("boy_left_") ;
        right1 = setup("boy_right") ;
        right2 = setup("boy_right_") ;
        }

        
    }
    public BufferedImage setup(String imageName) {
        
        UtilityTool uTool = new UtilityTool() ;
        BufferedImage image = null ;
        
        try{
            image =ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize) ;
            
        }catch(IOException e) {
            
        }
        return image ; 
    }
    
    public void update() {
        
        if (gp.gameState == gp.playState){
            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || 
                keyH.rightPressed == true ) {
            
            if (keyH.upPressed == true ) {
            direction = "up";    
        }
        else if ( keyH.downPressed == true ) {
            direction ="down" ; 
        }
        else if ( keyH.leftPressed == true ) {
            direction = "left" ;  
        }
        else if (keyH.rightPressed == true ) {
            direction = "right" ; 
        }
            //CHECK TILE COLLISION
            collisionOn = false ; 
            gp.cChecker.checkTile(this) ;
            
            //CHECK OBJECT COLLISION 
            int objIndex = gp.cChecker.checkObject(this, true) ;
            pickUpObject(objIndex) ;
            
            // IF COLLISION IS FALSE , PLAYER CAN MOVE 
            if(collisionOn == false ) 
            {
                switch ( direction ) 
                {
                    case"up" -> worldY -= speed ;
                    case"down" -> worldY += speed ;
                    case"left" -> worldX -= speed ;
                    case "right" -> worldX += speed ; 
                }
            }
        
        spriteCounter++ ;
        if(spriteCounter > 15) {
            if(spriteNum == 1) {
                spriteNum = 2 ; 
            }
            else if (spriteNum ==2 ) {
                spriteNum = 1 ; 
            }
            spriteCounter = 0 ; 
          }
            
        }
            
        }
        else if (gp.gameState == gp.pauseState){
            
        }
        
        
    }
    public void pickUpObject(int i) {
        
        if ( i != 999 ) {
            
            String objectName = gp.obj[i].name;
            
            switch ( objectName) {
                case "Key" :
                    hasKey++ ; 
                    gp.obj[i] = null ;
                    gp.playSE(1);
                    gp.ui.showMessage("You got a Key") ; 
                    break ; 
                case"Door":
                    if(hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null ; 
                        hasKey-- ; 
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case"Boots" :
                    gp.playSE(2);
                    speed += 2 ; 
                    gp.obj[i] = null ; 
                    if(speed > 4){
                        gp.ui.showMessage("Speed up!!!");
                    }
                    else {
                        gp.ui.showMessage("Back to normal");
                    }
                    
                    break ; 
                case "Chest" :
                    gp.ui.gameFinished = true ; 
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
                case"Table" :
                    gp.playSE(2);
                    speed -= 2;
                    gp.obj[i] = null ;
                    
                    if(speed < 4 ){
                    gp.ui.showMessage("Slowww!") ;
                    }
                    else {
                        gp.ui.showMessage("Back to normal");
                    }
                    break ;
            
        }
        }
    }
    
    public void draw(Graphics2D g2) {
          
        //g2.drawRect(worldX, worldY, gp.tileSize, gp.tileSize);
        BufferedImage image = null ; 
        switch (direction) {
            case "up" -> {
                if(spriteNum == 1 ) {
                    image = up1 ;
                }
                if(spriteNum ==2 ) {
                    image = up2 ; 
                }
            }
            case"down" -> {
                if ( spriteNum ==1 ) {
                    image = down1; 
                }
                if (spriteNum ==2) {
                    image = down2 ; 
                }
            }
            case"left" -> {
                if (spriteNum ==1) {
                    image = left1;
                }
                if (spriteNum ==2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum ==1) {
                    image = right1 ;
                }
                if (spriteNum ==2) {
                    image = right2;
                }
            } 
        }
        g2.drawImage(image, screenX,screenY ,  null);
    }
}
            
        
        
    
    

   