/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;
import pictures.ImageLoader;
import pictures.Pictures;

/**
 * factory of factory method pattern
 * @author Paul
 */
public class FigureFactory {

    private static FigureFactory figureFactory = new FigureFactory();
    private Figures newfig;
    private ImageLoader loader;

    /**
     * constructor of the figure factory
     */
    private FigureFactory(){
        this.loader = pictures.ImageLoader.getInstance();
    }

    /**
     * Singleton pattern instance method
     * @return static instance of figure factory
     */
    public static FigureFactory getInstance(){
        return figureFactory;
    }

    /**
     * method creates a new figure
     * @param objectName flag for the stata machine 
     * @param velocity move velocity of the new figure
     * @return new figure
     */
    public Figures factFigure(Names objectName, int velocity){
        
        newfig = null;
        
        switch(objectName){
            case AntiDino:
                newfig = new AntiDino(new Rectangle(1500, 800 , this.loader.getImage(Pictures.AntiDino).getWidth(null), this.loader.getImage(Pictures.AntiDino).getWidth(null)), velocity, Pictures.AntiDino);
                break;
                
            case Dino:
                newfig = new Dino(new Rectangle(70, 100, this.loader.getImageIcon(Pictures.Dino).getIconWidth() - 140, this.loader.getImageIcon(Pictures.Dino).getIconHeight()), Pictures.Dino);
                break;
             
            case Large:
                newfig = new Large(new Rectangle(1500, 650 , this.loader.getImage(Pictures.Large).getWidth(null), this.loader.getImage(Pictures.Large).getHeight(null)), velocity, Pictures.Large);
                break;
            
            case Middle:
                newfig = new Middle(new Rectangle(1500, 650, this.loader.getImage(Pictures.Middle).getWidth(null), this.loader.getImage(Pictures.Middle).getHeight(null)), velocity, Pictures.Middle);
                break;
                
            case Small:
                newfig = new Small(new Rectangle(1500, 750, this.loader.getImage(Pictures.Small).getWidth(null), this.loader.getImage(Pictures.Small).getHeight(null)), velocity, Pictures.Small);
                break;
        }
        return newfig;
    }
}
