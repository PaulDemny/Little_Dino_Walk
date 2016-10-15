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
 * manger of the model 
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
    private int gameVelocity;
    private int intervallCreate;
    private List <Figures> enemies;
    private List <IObserver> observers;
    private Rectangle backRect;
    private int level;
    private volatile boolean delayFlag;
    private Random rnd;
    private int levelIncrement;
    private int score;

    /**
     * constructor of the manager
     */
    private Manager(){
    }    

    /**
     * inits the manager
     */
    public void init(){
        this.factory         = FigureFactory.getInstance();
        this.loader          = ImageLoader.getInstance();
        this.rnd             = new Random();
        this.gameVelocity    = 15;
        this.intervallCreate = 30;
        this.level           = 20;
        this.levelIncrement  = 0;
        this.score           = 0;
        this.dino            = (Dino) factory.factFigure(Names.Names.Dino, this.gameVelocity);
        this.delayJump       = new Timer();
        this.enemies         = new ArrayList <>();
        this.observers       = new ArrayList <>();
        this.delayFlag       = false;
        this.backRect        = new Rectangle(0, 0, this.loader.getImage(Pictures.Desert).getWidth(null), this.loader.getImage(Pictures.Desert).getHeight(null));
    }

    /**
     * Instance for the singleton pattern
     * @return static manager instance
     */
    public static Manager getInstance(){
        return Manager.manager;
    }

    /**
     * starts all timers
     */
    public void start(){
      this.managerTime     = new Timer();
      this.enemyTime       = new Timer();
      this.task            = new TimerTask(){
            @Override
            public void run(){
                move();
                if(kolission()){
                    notifyObservers(States.GameOver);
                    stop();
                }
            }
        };
        this.passsive = new TimerTask(){
            @Override
            public void run() {
                factEnemies();
            }
        };
      this.managerTime.scheduleAtFixedRate(task, 0, this.intervallCreate);
      this.enemyTime.scheduleAtFixedRate(passsive, 0, this.intervallCreate * 50);
      this.dino.startDino();
    }

    /**
     * stops all timers
     */
    public void stop(){
        this.managerTime.cancel();
        this.enemyTime.cancel();
        this.dino.killDino();
    }

    /**
     * products all enemies
     */
    private void factEnemies(){
        this.raiseLevelCheck();
        switch(rnd.nextInt(3)){
            case 0:
                this.addSmall();
                break;
                
            case 1:
                if(this.level >= 10){
                    this.addMiddle();
                }
                break;
             
            case 2:
                if(this.level >= 15){
                    this.addLarge();
                    this.addAntiDino();
                }
                break;
                
            default:
                break;
        }
    }
    
    /**
     * produces a small enemy
     */
    private void addSmall(){
        switch(this.rnd.nextInt(10)){
            case 0: case 2: case 4: case 6:case 8:
                enemies.add(factory.factFigure(Names.Small, this.gameVelocity));
                break;
        }
    }
    
    /**
     * produces a middle enemy
     */
    private void addMiddle(){
            switch(rnd.nextInt(20)){
                case 0: case 5: case 10: case 15: case 19:
                    this.enemies.add(this.factory.factFigure(Names.Middle, this.gameVelocity));
                    break;
            }
    }
    
    /**
     * produces a large enemy
     */
    private void addLarge(){
        switch(rnd.nextInt(30)){
            case 0: case 5: case 10: case 15: case 20: case 25:
                    this.enemies.add(this.factory.factFigure(Names.Large, this.gameVelocity));
                    break;
       }
    }
    
    private void addAntiDino(){
        switch(rnd.nextInt(30)){
            case 0: case 5: case 10: case 15: case 20: case 25:
                    this.enemies.add(this.factory.factFigure(Names.AntiDino, this.gameVelocity + 2));
                    break;
        }
    }
    
    /**
     * raises the level
     */
    private void raiseLevelCheck(){
        this.levelIncrement++;
        if (this.levelIncrement > 60) {
            this.level++;
            this.gameVelocity++;
            this.levelIncrement = 0;
        }
    }

    /**
     * calls the move method of all enemies and the dino
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
     * dino getter
     * @return dino of the model
     */
    public Dino getDino(){
        return this.dino;
    }

    /**
     * enemy list getter
     * @return enemy list
     */
    public List <Figures> getEnemies(){
         return this.enemies;
    }

    /**
     * forward method
     */
    public void forward(){
        this.dino.move(15);
    }
    
    /**
     * reverse method
     */
    public void revers(){
        this.dino.move(-15);
    }

    /**
     * inits a jump in the model
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
            }, 500);
        }
    }

    /**
     * getter for the background rectangle
     * @return rectangle of background
     */
    public Rectangle getBackRect(){
        return this.backRect;
    }
    
    /**
     * checks for kollission
     * @return true if kollission happened
     */
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
     * dino model does not move
     */
    public void still() {
        this.dino.move(0);
    }

    /**
     * getter for the level
     * @return level of the model
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * kills the nemeys that are not visible
     */
    private void killEnemies(){
        for(int i = 0; i < this.enemies.size(); i++){
            if(this.enemies.get(i).getRect().x < -100){
                this.score += 10;
                this.enemies.remove(i);
            }
        }
        this.notifyObservers(States.LevelUpdate);
    }
    
    /**
     * quits the jump of the dino
     */
    public void quitJump(){
        this.dino.resetZenit();
    }

    /**
     * getter for the score
     * @return score of the model
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * attach method of the observer pattern
     * @param observer observer to attach
     */
    public void attach(IObserver observer){
        this.observers.add(observer);
    }
    
    /**
     * notification method of the observer pattern
     * @param state state for the state machine
     */
    private void notifyObservers(States state){
        for (int i = 0; i < this.observers.size(); i++){
            this.observers.get(i).update(state);
        }
    }
}
