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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JPanel;
import pictures.ImageLoader;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.Manager;
import pictures.Pictures;

/**
 * panel that contains the game
 * @author Paul
 */
public class GameOverPanel extends JPanel implements ActionListener{
    
    private ImageLoader loader;
    private JButton save;
    private JButton quit;
    private JTextField name;
    private Game game;
    private Manager manager;
    private Connection connect;
    private Statement order;
    private String input;
    
    /**
     * Constructor of Gameoverpanel
     * @param game instance of the game frame
     */
    public GameOverPanel(Game game){
        this.game = game;
        this.manager = Manager.getInstance();
        this.init();
    }
    
    /**
     * inits the panel
     */
    private void init(){
        this.connect = null;
        this.order = null;
        this.input = "";
        this.setLayout(null);
        this.loader = ImageLoader.getInstance();
        this.setSize(1500, 1000);
        this.save = new JButton("Save Highscore");
        this.save.setBounds(150, 700, 500, 200);
        this.save.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        this.save.setBounds(150, 700, 500, 200);
        this.save.addActionListener(this);
        this.add(save);
        this.quit = new JButton("Quit");
        this.quit.setBounds(150, 700, 500, 200);
        this.quit.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        this.quit.setBounds(800, 700, 500, 200);
        this.quit.addActionListener(this);
        this.add(quit);
        this.name = new JTextField("Username");
        this.name.setSize(500, 50);
        this.name.setLocation(500, 500);
        this.name.setBackground(Color.YELLOW);
        this.name.setForeground(Color.BLUE);
        this.name.setHorizontalAlignment(JTextField.CENTER);
        this.name.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        this.add(name);
    }

    /**
     * draws the backgound image and labels 
     * @param g Graphics obkect of panel
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(this.loader.getImage(Pictures.Normal), 0, 0, 1500, 1000, null);
        g.setColor(Color.RED);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 200));
        g.drawString("GAME OVER", 200, 200);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
        g.drawString("Level: ".concat(String.valueOf(this.manager.getLevel())).concat("   Score: ").concat(String.valueOf(this.manager.getScore())), 270, 300);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
        g.drawString("Please enter your name", 400, 450);
    }

        /**
         * KeyListener
         * @param e ActionEvent
         */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.save)){
            try{
                Class.forName("org.sqlite.JDBC");
                this.connect = DriverManager.getConnection("jdbc:sqlite:Memory.db");
                this.order = this.connect.createStatement();
                this.input = "INSERT INTO MEMORY (NAME, LEVEL, SCORE) VALUES ('" + this.name.getText() + "', " + this.manager.getLevel() + ", " + this.manager.getScore() + ")";
                this.order.executeUpdate(input);
                this.game.finish();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        if(e.getSource().equals(quit)){
            this.game.finish();
        }
    }
}
