/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import pictures.ImageLoader;
import javax.swing.JButton;
import pictures.Pictures;

/**
 *
 * @author Paul
 */
public class PausePanel extends JPanel implements ActionListener{
    
    private ImageLoader loader;
    private JButton resume;
    private JButton end;
    private Game game;
    
    public PausePanel(Game game){
        this.game = game;
        this.init();
    }
    
    private void init(){
        this.setLayout(null);
        this.loader = ImageLoader.getInstance();
        this.setSize(1500, 1000);
        this.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        this.resume = new JButton("Resume Game");
        this.resume.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        this.resume.setBounds(150, 500, 500, 200);
        this.resume.addActionListener(this);
        this.add(resume);
        this.end = new JButton("Finish Game");
        this.end.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        this.end.setBounds(800, 500, 500, 200);
        this.end.addActionListener(this);
        this.add(end);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(loader.getImage(Pictures.Normal), 0, 0, 1500, 1000, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resume){
            game.killPause();
        }
    }
}
