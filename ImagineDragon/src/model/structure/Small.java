/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 * class for a small enemy
 * @author Paul
 */
class Small extends Enemy{

    /**
     * constructor for a little enemy
     * @param rect rectangle for kollission
     * @param velocity velocity of the movement
     * @param img image flag
     */
    public Small(Rectangle rect, int velocity, pictures.Pictures img) {
        super(rect, velocity, img);
    }
}
