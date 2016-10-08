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
 *
 * @author Paul
 */
public class ImageLoader {
    
    private static ImageLoader imageLoaderInstance = new ImageLoader();
    
    private BufferedImage background    = null;
    private BufferedImage large         = null;
    private BufferedImage middle        = null;
    private BufferedImage small         = null;
    private BufferedImage normal        = null;
    
    private ImageIcon antiDino          = null;
    private ImageIcon roar              = null;
    private ImageIcon dino              = null;
    
    
    private ImageLoader(){
        initLoader();
    }
    
    private void initLoader(){
        try{
            this.background = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/panel.png"));
            this.large      = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/large.png"));
            this.middle     = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/middle.gif"));
            this.small      = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/small.png"));
            this.normal     = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/desert.png"));
            this.background = ImageIO.read(getClass().getClassLoader().getResource("pictures/files/panel.png"));
            
            this.dino       = new ImageIcon(getClass().getClassLoader().getResource("pictures/files/char.gif"));
            this.roar       = new ImageIcon(getClass().getClassLoader().getResource("pictures/files/Dino.gif"));
            this.antiDino   = new ImageIcon(getClass().getClassLoader().getResource("pictures/files/bsp.gif"));
        }   
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static ImageLoader getInstance(){
        return imageLoaderInstance;
    }
    
    public Image getImage(pictures.Pictures imageName){
        Image imageInstance = null;
        
        switch(imageName){

            case Desert:
                imageInstance = background;
                break;
            
            case Large:
                imageInstance = large;
                break;
             
            case Middle:
                imageInstance = middle;
                break;
              
            case Small:
                imageInstance = small;
                break;
                
            case Normal:
                imageInstance = normal;
                break;
        }
        
        return imageInstance;
    }
    
    public ImageIcon getImageIcon(pictures.Pictures iconName){
        
        ImageIcon iconInstance = null;
        
        switch(iconName){
            
            case Menue:
                iconInstance = roar;
                break;
                
            case Dino:
                iconInstance = dino;
                break;
                
            case AntiDino:
                iconInstance = antiDino;
                break;
        }
        
        return iconInstance;
    }
}
    

