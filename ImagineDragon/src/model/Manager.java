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
import main.IObserver;
import model.structure.Names;
import model.structure.Dino;
import pictures.Pictures;
import model.structure.FigureFactory;
import pictures.ImageLoader;
import model.structure.Figures;

/**
 *
 * @author Paul
 */
public class Manager{

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
    private List <IObserver> observers;
    private Rectangle backRect;
    private int level;
    private boolean kolissionFlag;
    private volatile boolean delayFlag;
    private Random rnd;
    private int levelIncrement;
    private int score;

    /**
     * 
     */
    private Manager(){
        
        this.init();
    }    

    /**
     * 
     */
    private void init(){
        this.factory         = FigureFactory.getInstance();
        this.loader          = ImageLoader.getInstance();
        this.rnd             = new Random();
        this.dino            = (Dino) factory.factFigure(Names.Names.Dino, this.gameVelocity);
        this.managerTime     = new Timer();
        this.enemyTime       = new Timer();
        this.delayJump       = new Timer();
        this.gameVelocity    = 15;
        this.intervallCreate = 30;
        this.enemies         = new ArrayList <>();
        this.observers       = new ArrayList <>();
        this.level           = 1;
        this.levelIncrement  = 0;
        this.score           = 0;
        this.kolissionFlag   = false;
        this.delayFlag       = false;
        this.backRect        = new Rectangle(0, 0, this.loader.getImage(Pictures.Desert).getWidth(null), this.loader.getImage(Pictures.Desert).getHeight(null));
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
        };
        this.start();
    }

    /**
     * 
     * @return 
     */
    public static Manager getInstance(){
        return Manager.manager;
    }

    /**
     * 
     */
    public void start(){
      this.managerTime.scheduleAtFixedRate(task, 0, this.intervallCreate);
      this.enemyTime.scheduleAtFixedRate(passsive, 0, this.intervallCreate * 33);
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
        this.raiseLevelCheck();
        this.addSmall();
        if(this.level >= 10){
            this.addMiddle();
        }
        if(this.level >= 15){
            this.addLarge();
        }
    }
    
    private void addSmall(){
        switch(this.rnd.nextInt(10)){
            case 2: case 8:
                enemies.add(factory.factFigure(Names.Small, this.gameVelocity));
                break;
        }
    }
    
    private void addMiddle(){
            switch(rnd.nextInt(30)){
                case 10: case 20:
                    this.enemies.add(this.factory.factFigure(Names.Large, this.gameVelocity));
                    break;
            }
    }
    
    private void addLarge(){
        switch(rnd.nextInt(30)){
                case 10: case 20:
                    this.enemies.add(this.factory.factFigure(Names.Large, this.gameVelocity));
                    break;
        }
    }
    
    private void raiseLevelCheck(){
        this.levelIncrement++;
        if (this.levelIncrement > 60) {
            this.level++;
            this.gameVelocity++;
            this.levelIncrement = 0;
        }
    }

    /**
     * 
     */
    private void move(){
        this.killEnemies();
        for (int i = 0; i < this.enemies.size(); i++){
            this.enemies.get(i).move(this.gameVelocity);
            if(this.enemies.get(i).getRect().y < 500){
                this.enemies.get(i).getRect().y += this.gameVelocity * 2;
            }
        }
        if (this.dino.getRect().y < 780) {
            this.dino.getRect().y += this.gameVelocity * 2;
        }
        if (this.backRect.x <= -2500){
            this.backRect.setLocation(0, 0);
        }
        this.backRect.x  -= this.gameVelocity;
    }

    /**
     * 
     * @return 
     */
    public Dino getDino(){
        return this.dino;
    }

    /**
     * 
     * @return 
     */
    public List <Figures> getEnemies(){
         return this.enemies;
    }

    /**
     * 
     */
    public void forward(){
        this.dino.move(20);
    }
    
    public void revers(){
        this.dino.move(-20);
    }

    /**
     * 
     */
    public void jump(){
        if(!this.delayFlag){
            this.delayFlag = true;
            this.dino.jump();
            this.delayJump.schedule(new TimerTask(){
                @Override
                public void run() {
                    delayFlag = false;
                }
            }, 1000);
        }
    }

    /**
     * 
     * @return 
     */
    public Rectangle getBackRect(){
        return this.backRect;
    }
    
    private boolean kolission(){
        boolean kollission = false;
        for (int i = 0; i < enemies.size(); i++){
            if(this.enemies.get(i).getRect().intersects(this.dino.getRect())){
                kollission = true;
            }
        }
        return kollission;
    }

    /**
     * 
     */
    public void still() {
        this.dino.move(0);
    }

    public int getLevel() {
        return this.level;
    }

    private void killEnemies(){
        for(int i = 0; i < this.enemies.size(); i++){
            if(this.enemies.get(i).getRect().x < -100){
                this.score += 10;
                this.enemies.remove(i);
            }
        }
        this.notifyObservers();
    }
    
    public void quitJump(){
        this.dino.resetZenit();
    }

    public int getScore() {
        return this.score;
    }
    
    public void attach(IObserver observer){
        this.observers.add(observer);
    }
    
    private void notifyObservers(){
        for (int i = 0; i < this.observers.size(); i++){
            this.observers.get(i).update();
        }
    }
}
