/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 *
 * @author Paul
 */
public class Game extends javax.swing.JFrame {

    private model.Manager manager;
    private music.MP3Player player;
    private GamePanel painting;
    
    /**
     * Creates new form Game
     */
    public Game() {
        this.setResizable(false);
        initComponents();
        this.setSize(1500, 1000);
        painting =  new GamePanel();
        this.add(painting);
        manager = model.Manager.getInstance();
        player = new music.MP3Player("music/files/Miles.mp3");
        painting = new GamePanel();
        player.play();
        this.addKeyListener(new KeyAdapter(){
            
            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    player.stop();
//                    setVisible(false);
//                    Menue menue = new Menue();
//                    menue.setVisible(true);
             
                        
             
                }
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    manager.revers();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    manager.forward();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    manager.jump();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_P){
                    painting.stopTimer();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_R){
                    painting.startTimer();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
                    manager.still();
                }
            }
            
            
        });
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
