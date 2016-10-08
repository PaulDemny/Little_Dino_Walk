/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;
import java.util.Random;
import pictures.Pictures;

/**
 *
 * @author Paul
 */
public class FigureFactory {

    private static FigureFactory figureFactory = new FigureFactory();
    private Figures newfig;
    private pictures.ImageLoader loader;
    private Random rnd;

    /**
     * 
     */
    private FigureFactory(){
        loader = pictures.ImageLoader.getInstance();
        rnd = new Random();
    }

    /**
     * 
     * @return 
     */
    public static FigureFactory getInstance(){
        return figureFactory;
    }

    /**
     * 
     * @param objectName
     * @param velocity
     * @return 
     */
    public Figures factFigure(Names objectName, int velocity){
        
        newfig = null;
        
        switch(objectName){
            case AntiDino:
                newfig = new AntiDino(new Rectangle(1500, 750 , loader.getImage(Pictures.AntiDino).getWidth(null), loader.getImage(Pictures.AntiDino).getHeight(null)), velocity, Pictures.AntiDino);
                break;
                
            case Dino:
                newfig = new Dino(new Rectangle(0, 750, loader.getImageIcon(Pictures.Dino).getIconWidth(), loader.getImageIcon(Pictures.Dino).getIconHeight()), Pictures.Dino);
                break;
             
            case Large:
                newfig = new Large(new Rectangle(1500, 650 /*+ rnd.nextInt(200)*/ , loader.getImage(Pictures.Large).getWidth(null), loader.getImage(Pictures.Large).getHeight(null)), velocity, Pictures.Large);
                break;
            
            case Middle:
                newfig = new Middle(new Rectangle(1500, 650 /*+ rnd.nextInt(50)*/, loader.getImage(Pictures.Middle).getWidth(null), loader.getImage(Pictures.Middle).getHeight(null)), velocity, Pictures.Middle);
                break;
                
            case Small:
                newfig = new Small(new Rectangle(1500, 750/* + rnd.nextInt(100) */, loader.getImage(Pictures.Small).getWidth(null), loader.getImage(Pictures.Small).getHeight(null)), velocity, Pictures.Small);
                break;
        }
        return newfig;
    }
}
