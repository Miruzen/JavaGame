/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Table;

/**
 *
 * @author miruzen
 */
public class AssetSetter {
    
    GamePanel gp ; 
    
    public AssetSetter(GamePanel gp) 
    {
        this.gp = gp ; 
    }
    
    public void setObject() {
        
        //TOP KEY 
        gp.obj[0] = new OBJ_Key(gp) ; 
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize ;
        
        //BOTTOM KEY 
        gp.obj[1] = new OBJ_Key(gp) ;
        gp.obj[1].worldX = 23 * gp.tileSize ;
        gp.obj[1].worldY = 40 * gp.tileSize ;
        
        //TOP RIGHT KEY 
        gp.obj[2] = new OBJ_Key(gp) ;
        gp.obj[2].worldX = 38 * gp.tileSize ;
        gp.obj[2].worldY = 8 * gp.tileSize ;
        
        gp.obj[4] = new OBJ_Door(gp) ;
        gp.obj[4].worldX = 10 * gp.tileSize ;
        gp.obj[4].worldY = 12 * gp.tileSize ;
        
        gp.obj[5] = new OBJ_Door(gp) ;
        gp.obj[5].worldX = 8 * gp.tileSize ;
        gp.obj[5].worldY = 28 * gp.tileSize ;
        
        gp.obj[6] = new OBJ_Door(gp) ;
        gp.obj[6].worldX = 12 * gp.tileSize ;
        gp.obj[6].worldY = 23 * gp.tileSize ;
        
        gp.obj[7] = new OBJ_Chest(gp) ;
        gp.obj[7].worldX = 10 * gp.tileSize ;
        gp.obj[7].worldY = 8 * gp.tileSize ;
        
        gp.obj[8] = new OBJ_Boots(gp) ;
        gp.obj[8].worldX = 37 * gp.tileSize ;
        gp.obj[8].worldY = 42 * gp.tileSize ;
        
        gp.obj[9] = new OBJ_Table(gp) ;
        gp.obj[9].worldX = 36 * gp.tileSize ;
        gp.obj[9].worldY = 25 * gp.tileSize ;
    }
    
}
