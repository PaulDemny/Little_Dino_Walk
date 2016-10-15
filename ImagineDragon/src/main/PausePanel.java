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
import model.Manager;
import pictures.Pictures;

/**
 * panel that contains the pause 
 * @author Paul
 */
public class PausePanel extends JPanel implements ActionListener{
    
    private ImageLoader loader;
    private JButton resume;
    private JButton end;
    private Game game;
    private Manager manager;
    
    /**
     * Constructor of the Pausepanel
     * @param game instance of game form
     */
    public PausePanel(Game game){
        this.game = game;
        this.manager = Manager.getInstance();
        this.init();
    }
    
    /**
     * inits the panel
     */
    private void init(){
        this.setLayout(null);
        this.loader = ImageLoader.getInstance();
        this.setSize(1500, 1000);
        this.resume = new JButton("Resume Game");
        this.resume.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        this.resume.setBounds(150, 700, 500, 200);
        this.resume.addActionListener(this);
        this.add(this.resume);
        this.end = new JButton("Finish Game");
        this.end.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        this.end.setBounds(800, 700, 500, 200);
        this.end.addActionListener(this);
        this.add(this.end);
    }

    /**
     * paints the background and images
     * @param g Graphics object of the panel
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.loader.getImage(Pictures.Normal), 0, 0, 1500, 1000, null);
        g.setColor(Color.RED);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 200));
        g.drawString("PAUSE", 470, 200);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
        g.drawString("Level: ".concat(String.valueOf(this.manager.getLevel())).concat("   Score: ").concat(String.valueOf(this.manager.getScore())), 270, 500);
    }

    /**
     * KeyListener
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.resume)){
            this.setFocusable(false);
            this.game.killPause();
        }
        
        if(e.getSource().equals(this.end)){
            this.game.finish();
        }
    }
}
