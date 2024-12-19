package main;

import javax.swing.JFrame;


/**
 *
 * @author miruzen
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame window = new JFrame () ; 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false ) ; 
        window.setTitle("2d game");
        
        GamePanel Gamepan = new GamePanel() ;  
        window.add(Gamepan) ;
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true); 
        
        Gamepan.setupGame();
        Gamepan.startGameThread();
    }
    
}
