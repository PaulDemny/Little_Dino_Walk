/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pictures;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * class for imageloader 
 * @author Paul
 */
public class ImageLoader {
    
    private static ImageLoader imageLoaderInstance = new ImageLoader();
    
    private BufferedImage background    = null;
    private BufferedImage large         = null;
    private BufferedImage middle        = null;
    private BufferedImage small         = null;
    private BufferedImage normal        = null;
    private BufferedImage antiDino      = null;
    
    private ImageIcon roar              = null;
    private ImageIcon dino              = null;
    
    /**
     * constructor for imageloader
     */
    private ImageLoader(){
        initLoader();
    }
    
    /**
     * inits the loader
     */
    private void initLoader(){
        try{
            this.background = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/panel.png"));
            this.large      = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/large.png"));
            this.middle     = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/middle.gif"));
            this.small      = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/small.png"));
            this.normal     = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/desert.png"));
            this.background = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/panel.png"));
            this.antiDino   = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/enemy.png"));
                    
            this.dino       = new ImageIcon(getClass().getClassLoader().getResource("pictures/files/char.gif"));
            this.roar       = new ImageIcon(getClass().getClassLoader().getResource("pictures/files/Dino.gif"));
        }   
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    /**
     * getInstance for the singleton pattern
     * @return instance of the loader 
     */
    public static ImageLoader getInstance(){
        return imageLoaderInstance;
    }
    
    /**
     * factory method
     * @param imageName flag for state machine
     * @return returns image
     */
    public Image getImage(pictures.Pictures imageName){
        Image imageInstance = null;
        
        switch(imageName){

            case Desert:
                imageInstance = this.background;
                break;
            
            case Large:
                imageInstance = this.large;
                break;
             
            case Middle:
                imageInstance = this.middle;
                break;
              
            case Small:
                imageInstance = this.small;
                break;
                
            case Normal:
                imageInstance = this.normal;
                break;
                
            case AntiDino:
                imageInstance = this.antiDino;
                break;
        }
        
        return imageInstance;
    }
    
    /**
     * second factory method
     * @param iconName flag for the state machine
     * @return return animated pictures
     */
    public ImageIcon getImageIcon(Pictures iconName){
        
        ImageIcon iconInstance = null;
        
        switch(iconName){
            
            case Menue:
                iconInstance = this.roar;
                break;
                
            case Dino:
                iconInstance = this.dino;
                break;
                
        }
        
        return iconInstance;
    }
}
    

