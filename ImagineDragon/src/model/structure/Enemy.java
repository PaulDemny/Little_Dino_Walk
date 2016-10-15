/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 * abstractio layer for enemies
 * @author Paul
 */
abstract class Enemy extends Figures{
  
    protected int velocity;

    /**
     * constructor of the abstract enemy
     * @param rect rectangle for kollission
     * @param velocity velocity for movement
     * @param img image flag
     */
    public Enemy(Rectangle rect, int velocity, pictures.Pictures img) {
        super(rect, img);
        this.velocity = velocity;
    }

    /**
     * overwritte move method
     * @param direction velocity of movement
     */
    @Override
    public void move(int direction){
        rect.x -= velocity;
    }
    
}
