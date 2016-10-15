/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structure;

import java.awt.Rectangle;

/**
 * class for AntiDino
 * @author Paul
 */
class AntiDino extends Enemy{

    /**
     * constructor of the AntiDino
     * @param rect ractangle for kollission
     * @param velocity velocity of the movement
     * @param img image flag
     */
    public AntiDino(Rectangle rect, int velocity, pictures.Pictures img){
        super(rect, velocity, img);
    }
}
