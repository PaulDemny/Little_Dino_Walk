/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Graphics;
import javax.swing.JPanel;
import pictures.ImageLoader;
import javax.swing.JButton;
import pictures.Pictures;

/**
 *
 * @author Paul
 */
public class GameOverPanel extends JPanel{
    
    private ImageLoader loader;
    private JButton resume;
    private JButton end;
    
    public GameOverPanel(){
        this.init();
    }
    
    private void init(){
        this.setLayout(null);
        this.loader = ImageLoader.getInstance();
        this.setSize(1500, 1000);
        this.resume = new JButton("Resume Game");
        this.resume.setBounds(150, 500, 500, 200);
        this.add(resume);
        this.end = new JButton("Resume Game");
        this.end.setBounds(800, 500, 500, 200);
        this.add(end);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(loader.getImage(Pictures.Normal), 0, 0, 1500, 1000, null);
    }
}
