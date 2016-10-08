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
class AntiDino extends Enemy{

    /**
     * 
     * @param rect
     * @param velocity
     * @param img 
     */
    public AntiDino(Rectangle rect, int velocity, pictures.Pictures img){
        super(rect, velocity, img);
    }
}
