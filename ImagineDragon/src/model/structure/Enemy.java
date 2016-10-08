/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Paul
 */
abstract class Enemy extends Figures{
  
    protected int velocity;
    
    public Enemy(Rectangle rect, int velocity, pictures.Pictures img) {
        super(rect, img);
        this.velocity = velocity;
    }
    
    @Override
    public void move(int direction){
        rect.x -= velocity;
    }
    
}
