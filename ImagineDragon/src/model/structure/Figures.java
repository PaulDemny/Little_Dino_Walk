/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 * abstract layer for model
 * @author Paul
 */
public abstract class Figures implements IMovable{
    
    private pictures.Pictures img;
    protected Rectangle rect;

    /**
     * constructor of the abstract layer
     * @param rect rectangle for kollission
     * @param img image flag
     */
    public Figures(Rectangle rect, pictures.Pictures img){
        this.rect = rect;
        this.img = img;
    }

    /**
     * getter for the image flag
     * @return image flag
     */
    public pictures.Pictures getImage(){
        return img;
    }

    /**
     * getter for kollission rectangle
     * @return rectangle of the abstract layer
     */
    public Rectangle getRect(){
        return rect;
    }

    /**
     * abstract move method
     * @param direction direction of the movement
     */
    @Override
    public abstract void move(int direction);
}
