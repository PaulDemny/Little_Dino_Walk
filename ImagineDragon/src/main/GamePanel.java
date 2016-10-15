/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.structure.Dino;
import model.structure.Figures;
import pictures.Pictures;
import model.Manager;
import model.States;
import pictures.ImageLoader;

/**
 * panel contains the gameover
 * @author Paul
 */
public class GamePanel extends JPanel implements ActionListener, IObserver{
    
    private Timer gameTime;
    private ImageIcon icon;
    private Dino dino;
    private Manager manager;
    private pictures.ImageLoader loader;
    private List <Figures> enemies;
    private int level;
    private int score;
    
    /**
     * Constructor of the Gamepanel
     */
    public GamePanel(){
        this.init();
    }
    
    /**
     * inits view
     */
    private void init(){
        this.manager = Manager.getInstance();
        this.loader     = ImageLoader.getInstance();
        this.gameTime   = new Timer(30, this);
        this.gameTime.start();
        this.setSize(1500, 1000);
        this.setDoubleBuffered(true);
        this.setIgnoreRepaint(true);
        this.manager.attach(this);
        this.update(States.LevelUpdate);
    }

    /**
     * rendering method
     * @param graph Graphics object of the panel
     */
    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D plainMdl = (Graphics2D) graph;
        this.dino = this.manager.getDino();
        this.enemies = this.manager.getEnemies();
        plainMdl.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        plainMdl.drawImage(loader.getImage(Pictures.Desert), manager.getBackRect().x, manager.getBackRect().y, null);
        icon = loader.getImageIcon(Pictures.Dino);
        icon.paintIcon(this, plainMdl, dino.getRect().x - 60, dino.getRect().y);
        for (int i = 0; i < enemies.size(); i++){
            plainMdl.drawImage(loader.getImage(enemies.get(i).getImage()), enemies.get(i).getRect().x, enemies.get(i).getRect().y, null);
        }
        plainMdl.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        plainMdl.setColor(Color.red);
        plainMdl.drawString("Level: ".concat(String.valueOf(this.level)).concat("   Score: ").concat(String.valueOf(this.score)), 20, 50);
        plainMdl.dispose();
   }

    /**
     * stops the rendering loop
     */
    public void stopTimer(){
        if(gameTime.isRunning()){
            gameTime.stop();
        }
    }
    
    /**
     * starts the rendering loop
     */
    public void startTimer(){
        if (!gameTime.isRunning()){
            gameTime.start();
        }
    }
    
    /**
     * event call of the rendering loop
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
     * Overide the update method, background will not be deleted
     * @param g Graphics object of the panel
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * update methode of the observer pattern
     * @param state state for state machine
     */
    @Override
    public void update(States state) {
        if(state.equals(States.LevelUpdate)){
            this.level = this.manager.getLevel();
            this.score = this.manager.getScore();
        }
        else if(state.equals(States.GameOver)){
            this.gameTime.stop();
            this.manager.stop();
        }
    }
}
