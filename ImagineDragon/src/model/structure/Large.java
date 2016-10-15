/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 * class for big enemy
 * @author Paul
 */
class Large extends Enemy{

    /**
     * constructor for a lage enemy
     * @param rect rectangle for kollission
     * @param velocity velocity of the movement
     * @param img image flag
     */
    public Large(Rectangle rect, int velocity, pictures.Pictures img) {
        super(rect, velocity, img);
    }  
}
