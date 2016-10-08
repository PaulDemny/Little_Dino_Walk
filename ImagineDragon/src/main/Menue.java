/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pictures.Pictures;

/**
 *
 * @author Paul
 */
public class Menue extends javax.swing.JFrame implements ActionListener{

    private ImageIcon icon;
    private JLabel dino;
    private music.MP3Player player;
    private pictures.ImageLoader loader;
    
    /**
     * Creates new form Menue
     */
    public Menue() {
        loader = pictures.ImageLoader.getInstance();
        this.setResizable(false);
        this.setContentPane(new JLabel(new ImageIcon(loader.getImage(Pictures.Normal))));
        initComponents();
        this.setSize(1500, 1000);
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spielen = new javax.swing.JButton();
        anleitung = new javax.swing.JButton();
        schließen = new javax.swing.JButton();
        highscore = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        spielen.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        spielen.setText("Spielen");

        anleitung.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        anleitung.setText("Anleitung");

        schließen.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        schließen.setText("Schließen");

        highscore.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        highscore.setText("Highscore");

        jLabel1.setFont(new java.awt.Font("Wide Latin", 3, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("LITTLE DRAGON WALK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(648, 648, 648)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(highscore, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(anleitung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spielen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(schließen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(593, 593, 593))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(spielen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(anleitung, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(highscore, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(schließen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *  
     */
    public void init(){
        icon = loader.getImageIcon(Pictures.Menue);
        dino = new JLabel(icon);
        dino.setBounds(100, 500, icon.getIconWidth(), icon.getIconHeight());
        this.add(dino);
        dino = new JLabel(icon);
        dino.setBounds(this.getWidth() - icon.getIconWidth() - 100, 500, icon.getIconWidth(), icon.getIconHeight());
        this.add(dino);
        player = new music.MP3Player("music/files/Stuck.mp3");
        player.play();
        this.spielen.addActionListener(this);
        this.anleitung.addActionListener(this);
        this.highscore.addActionListener(this);
        this.schließen.addActionListener(this);
    }
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
            java.util.logging.Logger.getLogger(Menue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Menue().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anleitung;
    private javax.swing.JButton highscore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton schließen;
    private javax.swing.JButton spielen;
    // End of variables declaration//GEN-END:variables

    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == anleitung){
            Anleitung anleitung = new Anleitung();
            player.stop();
            this.setVisible(false);
            anleitung.setVisible(true);            
        }
        
        if (e.getSource() == spielen){
            player.stop();
            this.setVisible(false);
            Game game = new Game();
            game.setVisible(true);
        }
        
        if (e.getSource() == highscore){
            Highscore score = new Highscore();
            player.stop();
            this.setVisible(false);
            score.setVisible(true);        
        }
        
        if (e.getSource() == schließen){
            player.stop();
            this.setVisible(false);
            System.exit(0); 
        }
        
    }
}
