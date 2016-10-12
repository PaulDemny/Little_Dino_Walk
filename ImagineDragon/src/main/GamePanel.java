/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.List;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.structure.Dino;
import model.structure.Figures;
import pictures.Pictures;
import model.Manager;
import pictures.ImageLoader;

/**
 *
 * @author Paul
 */
public class GamePanel extends JPanel implements ActionListener, IObserver{
    
    private Timer gameTime;
    private ImageIcon icon;
    private Dino dino;
    private Manager manager;
    private pictures.ImageLoader loader;
    private boolean actionFlag;
    private List <Figures> enemies;
    private int level;
    private int score;
    private BufferStrategy buffer;
    
    /**
     * 
     */
    public GamePanel(){
        this.init();
    }
    
    private void init(){
        this.manager = Manager.getInstance();
        this.loader = ImageLoader.getInstance();
        this.gameTime = new Timer(30, this);
        this.gameTime.start();
        this.actionFlag = false;
        this.setSize(1500, 1000);
        //this.setDoubleBuffered(true);
        this.setIgnoreRepaint(true);
        this.manager.attach(this);
        this.update();
    }

    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D plainMdl = (Graphics2D) graph;
        this.dino = this.manager.getDino();
        this.enemies = this.manager.getEnemies();
        plainMdl.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        plainMdl.drawImage(loader.getImage(Pictures.Desert), manager.getBackRect().x, manager.getBackRect().y, null);
        icon = loader.getImageIcon(Pictures.Dino);
        icon.paintIcon(this, plainMdl, dino.getRect().x, dino.getRect().y);
        for (int i = 0; i < enemies.size(); i++){
            plainMdl.drawImage(loader.getImage(enemies.get(i).getImage()), enemies.get(i).getRect().x, enemies.get(i).getRect().y, null);
        }
        plainMdl.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        plainMdl.setColor(Color.red);
        plainMdl.drawString("Level: " + String.valueOf(this.level), 20, 50);
        plainMdl.drawString("Score: " + String.valueOf(this.score), 300, 50);
        plainMdl.dispose();
   }

    public void stopTimer(){
        if (actionFlag){
        }
    }
    
    public void startTimer(){
        if (!actionFlag){
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void update() {
        this.level = this.manager.getLevel();
        this.score = this.manager.getScore();
    }
}
