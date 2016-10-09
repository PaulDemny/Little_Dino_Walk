/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import pictures.Pictures;

/**
 *
 * @author Paul
 */
public class GamePanel extends JPanel implements ActionListener{
    
    private Timer gameTime;
    private TimerTask action;
    private ImageIcon icon;
    private model.structure.Dino dino;
    private model.Manager manager;
    private pictures.ImageLoader loader;
    private boolean actionFlag;

    /**
     * 
     */
    public GamePanel(){
        manager = model.Manager.getInstance();
        loader = pictures.ImageLoader.getInstance();
        gameTime = new Timer(30, this);
        gameTime.start();
        actionFlag = false;
        this.setSize(1500, 1000);
        this.setDoubleBuffered(true);
        this.setIgnoreRepaint(true);
    }

    /**
     * 
     * @param graph 
     */
    @Override
    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D plainMdl = (Graphics2D) graph;
        plainMdl.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dino = this.manager.getDino();
        List <model.structure.Figures> enemies = this.manager.getEnemies();
        plainMdl.drawImage(loader.getImage(Pictures.Desert), manager.getBackRect().x, manager.getBackRect().y, null);
        icon = loader.getImageIcon(Pictures.Dino);
        icon.paintIcon(this, plainMdl, dino.getRect().x, dino.getRect().y);
        for (int i = 0; i < enemies.size(); i++){
            plainMdl.drawImage(loader.getImage(enemies.get(i).getImage()), enemies.get(i).getRect().x, enemies.get(i).getRect().y, null);
        }
        plainMdl.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        plainMdl.drawString("Level: " + String.valueOf(manager.getLevel()), 30, 30);
   }

    /**
     * 
     */
    public void stopTimer(){
        if (actionFlag){
        }
    }

    /**
     * 
     */
    public void startTimer(){
        if (!actionFlag){
            
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    /**
     * 
     * @param g 
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
