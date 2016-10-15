/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
public class GameOverPanel extends JPanel implements ActionListener{
    
    private ImageLoader loader;
    private JButton save;
    private JButton quit;
    private Game game;
    
    public GameOverPanel(Game game){
        this.game = game;
        this.init();
    }
    
    private void init(){
        this.setLayout(null);
        this.loader = ImageLoader.getInstance();
        this.setSize(1500, 1000);
        this.save = new JButton("Save Highscore and quit");
        this.save.setBounds(150, 500, 500, 200);
        this.save.addActionListener(this);
        this.add(save);
        this.quit = new JButton("Quit");
        this.quit.setBounds(800, 500, 500, 200);
        this.quit.addActionListener(this);
        this.add(quit);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(loader.getImage(Pictures.Normal), 0, 0, 1500, 1000, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(save)){
            
        }
        if(e.getSource().equals(quit)){
            this.game.finish();
        }
    }
}
