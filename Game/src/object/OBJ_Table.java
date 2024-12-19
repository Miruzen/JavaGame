/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author miruzen
 */
    public class OBJ_Table extends SuperObject {

        GamePanel gp ;

        public OBJ_Table(GamePanel gp){

            this.gp = gp ;
            name ="Table" ; 
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/objects/table01.png")) ;
                uTool.scaleImage(image, gp.tileSize, gp.tileSize) ;

            }catch(IOException e){

            }
            collision = true ; 
        }

        }


