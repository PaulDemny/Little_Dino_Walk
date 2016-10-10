/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import model.structure.Names;
import model.structure.Dino;
import pictures.Pictures;
import model.Manager;
import model.structure.FigureFactory;
import pictures.ImageLoader;
import model.structure.Figures;

/**
 *
 * @author Paul
 */
public class Manager {

    private static Manager manager = new Manager();
    private FigureFactory factory;
    private ImageLoader loader;
    private Dino dino;
    private Timer managerTime;
    private Timer enemyTime;
    private Timer delayJump;
    private TimerTask task;
    private TimerTask passsive;
    private TimerTask delay;
    private int gameVelocity;
    private int intervallCreate;
    private List <Figures> enemies;
    private Rectangle backRect;
    private int level;
    private boolean kolissionFlag;
    private volatile boolean delayFlag;

    /**
     * 
     */
    private Manager(){
        init();
    }    

    /**
     * 
     */
    private void init(){
        this.factory         = FigureFactory.getInstance();
        this.loader          = ImageLoader.getInstance();
        this.dino            = (Dino) factory.factFigure(Names.Dino, gameVelocity);
        this.managerTime     = new Timer();
        this.enemyTime       = new Timer();
        this.delayJump       = new Timer();
        this.gameVelocity    = 3;
        this.intervallCreate = 5;
        this.enemies         = new ArrayList <>();
        this.level           = 1;
        this.kolissionFlag   = false;
        this.delayFlag       = false;
        this.backRect        = new Rectangle(0, 0, loader.getImage(Pictures.Desert).getWidth(null), loader.getImage(Pictures.Desert).getHeight(null));
        this.task            = new TimerTask(){
            @Override
            public void run(){
                move();
                kolission();
            }
        };
        this.passsive        = new TimerTask(){
            @Override
            public void run() {
                factEnemies();
            }
        } ;
        this.start();
    }

    /**
     * 
     * @return 
     */
    public static Manager getInstance(){
        return manager;
    }

    /**
     * 
     */
    public void start(){
      this.managerTime.scheduleAtFixedRate(task, 0, intervallCreate);
      this.enemyTime.scheduleAtFixedRate(passsive, 0, gameVelocity * 100);
    }

    /**
     * 
     */
    public void stop(){
        
    }

    /**
     * 
     */
    private void factEnemies(){
        Random rnd = new Random();
        switch(rnd.nextInt(20)){
            case 2: case 18:
                enemies.add(factory.factFigure(Names.Small, gameVelocity));
                break;
        }
    }

    /**
     * 
     */
    private void move(){
        
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).move(gameVelocity);
            if(enemies.get(i).getRect().y < 300){
                enemies.get(i).getRect().y += 5;
            }
        }
        
        if (dino.getRect().y < 800) {
            dino.getRect().y += 5;
        }
        
        if (backRect.x <= -2500){
            this.backRect.setLocation(0, 0);
        }
        
        backRect.x  -= gameVelocity;
    }

    /**
     * 
     * @return 
     */
    public Dino getDino(){
        return dino;
    }

    /**
     * 
     * @return 
     */
    public List <Figures> getEnemies(){
         return enemies;
    }

    /**
     * 
     */
    public void forward(){
        dino.move(20);
    }
    
    public void revers(){
        dino.move(-20);
    }

    /**
     * 
     */
    public void jump(){
        if(!delayFlag){
            this.delayFlag = true;
            dino.jump();
            this.delayJump.schedule(new TimerTask(){
                @Override
                public void run() {
                    delayFlag = false;
                }
            }, 500);
        }
    }

    /**
     * 
     * @return 
     */
    public Rectangle getBackRect(){
        return backRect;
    }
    
    private void kolission(){
        for (int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).getRect().intersects(dino.getRect())){
                return;
            }
        }
        
    }

    /**
     * 
     */
    public void still() {
        dino.move(0);
    }
}
