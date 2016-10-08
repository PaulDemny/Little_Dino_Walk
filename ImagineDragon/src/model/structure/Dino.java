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
public class Dino extends Figures{
   
    private volatile int dinoVelocity;
    private Thread jump;
    private Thread walking;
    private boolean zenit;
    private boolean movement;
 
    public Dino(Rectangle rect, pictures.Pictures img) {
        super(rect, img);
        this.dinoVelocity = 0;
        this.walking = new Thread(){
            @Override
            public void run() {
                while (movement) {                    
                    if (dinoVelocity < 0){
                        if (rect.x > 0){
                            rect.x += dinoVelocity;
                        }
                    }
                    if (dinoVelocity > 0){
                        if (rect.x < 1200){
                            rect.x += dinoVelocity;
                        }
                    }
                    try {
                        Thread.sleep(25);
                    } 
                    catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        };
        movement = true;
        walking.start();
        zenit = false;
    }
    
    public void killDino(){
        this.movement = false;
    }
    
    private void task(){
        if(rect.y > 200){
            rect.y -= 10;
            zenit = false;
        }
        else{
            zenit = true;
        }
    }
        
    @Override
    public void move(int direction) {
        dinoVelocity = direction;
    }
    
    public void jump(){
        
        this.jump = new Thread(){
            @Override
            public void run() {
                while(!zenit){
                    task();
                    try {
                        Thread.sleep(3);
                    } 
                    catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
            
        };
        jump.start();
        zenit = false;
    }
}