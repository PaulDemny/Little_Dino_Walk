/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import model.Manager;
import model.States;
import music.MP3Player;


/**
 * form and controller
 * @author Paul
 */
public class Game extends JFrame implements IObserver{

    private Manager manager;
    private MP3Player player;
    private GamePanel painting;
    private PausePanel pause;
    private GameOverPanel gameOver;
    
//    static {
//    System.setProperty("sun.java2d.transaccel", "True");
//    System.setProperty("sun.java2d.trace", "timestamp,log,count");
//    System.setProperty("sun.java2d.opengl", "True");
//    System.setProperty("sun.java2d.d3d", "True");
//    System.setProperty("sun.java2d.ddforcevram", "True");
//    }
    
    /**
     * Creates new form Game
     */
    public Game() {
        this.manager = Manager.getInstance();
        this.manager.init();
        this.manager.start();
        this.manager.attach(this);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setIgnoreRepaint(true);
        this.setFocusable(true);
        this.initComponents();
        this.setSize(1500, 1000);
        this.setFocusable(true);
        this.painting = new GamePanel();
        this.pause = new PausePanel(this);
        this.gameOver = new GameOverPanel(this);
        this.getContentPane().add(this.painting, BorderLayout.CENTER);
        this.player = new MP3Player("music/files/Miles.mp3");
        this.player.play();
        this.addKeyListener(new KeyAdapter(){
            
            private int key;
            
            /**
             * 
             * @param e 
             */
            @Override
            public void keyPressed(KeyEvent e) {
                
                key = e.getKeyCode();
                
                if (key == KeyEvent.VK_ESCAPE){
                    player.stop();
                    manager.stop();
                    painting.stopTimer();
                    getContentPane().removeAll();
                    getContentPane().add(pause, BorderLayout.CENTER);
                    player = new MP3Player("music/files/Walk.mp3");
                    player.play();
                    repaint();
                }
                
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
                    manager.revers();
                }
                
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
                    manager.forward();
                }
                
                if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                    manager.jump();
                }
                
                if (key == KeyEvent.VK_P){
                    painting.stopTimer();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_R){
                    painting.startTimer();
                }
            }
            
            /**
             * 
             * @param e 
             */
            @Override
            public void keyReleased(KeyEvent e) {
                
                key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_A || key == KeyEvent.VK_D){
                    manager.still();
                }
                if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
                    manager.quitJump();
                }
            }
        });
        
    }
    
    /**
     * Kills the Pausepanel
     */
    public void killPause(){
        this.setFocusable(true);
        this.player.stop();
        this.player = new MP3Player("music/files/Miles.mp3");
        this.player.play();
        this.getContentPane().removeAll();
        this.getContentPane().add(this.painting, BorderLayout.CENTER);
        this.setFocusable(true);
        this.manager.start();
        this.painting.startTimer();
        repaint();
    }
    
    /**
     * 
     * @return the player instance
     */
    public MP3Player getPlayer(){
        return this.player;
    }
    
    /**
     * finishes the the game and open the menue
     */
    public void finish(){
        this.player.stop();
        this.painting.stopTimer();
        this.manager.stop();
        this.setVisible(false);
        this.dispose();
        Menue menue = new Menue();
        menue.setVisible(true);
    }
    
    /**
     * show the Gameoverpanel
     */
    public void showGameOver(){
        player.stop();
        painting.stopTimer();
        this.getContentPane().removeAll();
        this.getContentPane().add(gameOver, BorderLayout.CENTER);
        this.player = new MP3Player("music/files/Watchmen.mp3");
        this.player.play();
        this.repaint();
    }

    /**
     * 
     * @param state this parameter signals and modifys the update method
     */
    @Override
    public void update(States state) {
        if(state.equals(States.GameOver)){
            this.showGameOver();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
