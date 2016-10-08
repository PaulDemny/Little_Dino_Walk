/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 *
 * @author Paul
 */
public abstract class Figures implements IMovable{
    
    private pictures.Pictures img;
    
    protected Rectangle rect;

    /**
     * 
     * @param rect
     * @param img 
     */
    public Figures(Rectangle rect, pictures.Pictures img){
        this.rect = rect;
        this.img = img;
    }

    /**
     * 
     * @return 
     */
    public pictures.Pictures getImage(){
        return img;
    }

    /**
     * 
     * @return 
     */
    public Rectangle getRect(){
        return rect;
    }

    /**
     * 
     * @param direction 
     */
    @Override
    public abstract void move(int direction);
}
